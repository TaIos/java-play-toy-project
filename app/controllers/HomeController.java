package controllers;

import com.typesafe.config.Config;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.AkkaSchedulingSerivce;
import services.ConcurrencyService;

import javax.inject.Inject;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private Config config;

    @Inject
    private ConcurrencyService concurrencyTestingService;

    @Inject
    private AkkaSchedulingSerivce akkaSchedulingSerivce;

    /*
    @Inject
    public HomeController(Config config) {
        this.config = config;
    }
     */

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getPort() {
        return ok(toJson(config.getInt("http.port")));
    }

    public Result redirect() {
        return redirect("http://www.seznam.cz");
    }

    public Result optionalParam(Integer id, Integer idd) {
        return ok(toJson(id + idd));
    }

    public Result fixedParam(String param1, String param2, String param3, String test) {
        return ok(toJson(String.join(",", param1, param2, param3, test)));
    }

    public Result sessionScopeCookies(Http.Request request) {
        String cookie = "Cookie from: " + LocalTime.now().format(DateTimeFormatter.ofPattern("H:m:s"));
        String cookiePrev = request.session().get("my_cookie").orElse("NOT PRESENT");
        return ok(String.format("Added [%s] to session scope\nPrevious value [%s]", cookie, cookiePrev))
                .addingToSession(request, "my_cookie", cookie);
    }

    public Result flashScopeCookies(Http.Request request) {
        String cookie = "Cookie2 from: " + LocalTime.now().format(DateTimeFormatter.ofPattern("H:m:s"));
        String cookiePrev = request.flash().get("my_cookie2").orElse("NOT PRESENT");
        return ok(String.format("Added [%s] to flash scope\nPrevious value [%s]", cookie, cookiePrev))
                .flashing("my_cookie2", cookie);
    }

    public Result akkaSchedule() {
        akkaSchedulingSerivce.scheduleTask();
        return ok("Task scheduled");
    }
}
