package controllers;

import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class LargeResponseController extends Controller {


    private final Materializer materializer;

    private final WSClient ws;

    @Inject
    public LargeResponseController(Materializer materializer, WSClient ws) {
        this.materializer = materializer;
        this.ws = ws;
    }

    public CompletionStage<Result> processLargeResponse() {
        CompletionStage<WSResponse> futureResponse =
                ws.url("http://www.mocky.io/v2/5e08df833000005b0081a159")
                        .setMethod("GET").stream();

        CompletionStage<Long> bytesReturned = futureResponse
                .thenCompose(
                        res -> {
                            Source<ByteString, ?> responseBody = res.getBodyAsSource();
                            // Count the number of bytes returned
                            Sink<ByteString, CompletionStage<Long>> bytesSum =
                                    Sink.fold(0L, (total, bytes) -> total + bytes.length());
                            return responseBody.runWith(bytesSum, materializer);
                        });

        return bytesReturned.thenApply(
                res -> ok(res.toString()));

    }
}
