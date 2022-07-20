package controllers.base;

import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;

public class WebServiceController extends Controller {

  private final WSClient ws;

  @Inject
  public WebServiceController(WSClient ws) {
    this.ws = ws;
  }

  public CompletionStage<Result> echo() {
    return ws.url("http://www.mocky.io/v2/53c7ec8426e0e3fd14326b0d")
        .get()
        .thenApply(response -> ok("Feed response: " + response.getBody()));
  }
}
