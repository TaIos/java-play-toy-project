package controllers;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class JsonRequestBodyController extends Controller {

    /**
     * Returns 415 (unsupported media type code) if the request body is not json, e.g. form-data
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result explicitJsonRequestBody(Http.Request request) {
        return ok("JSON content: " + request.body().asJson());
    }

    /**
     * Return NULL with code 200 if the request body is not json, e.g. form-data
     */
    public Result implicitJsonRequestBody(Http.Request request) {
        return ok("JSON content: " + request.body().asJson());
    }
}
