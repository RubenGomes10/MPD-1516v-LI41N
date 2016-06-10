package apps.http;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.function.Function;

/**
 * @author Miguel Gamboa
 *         created on 03-06-2016
 */
public class HttpServerServerAppV3 {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        new HttpServer()
                    //.addController(controller)
                    .addHandler("/timev2", controller::time)
                    .addHandler("/ola", controller::hello)
                    .addHandler("/aluno/*", controller::student)
                    .run();
    }

    static class Controller {

        //@Path("/timev2")
        public String time(HttpServletRequest req) {
            return String.format("Time is %s\nVersion:0.4.1", LocalTime.now().toString());
        }

        //@Path("/ola")
        public String hello(HttpServletRequest req) {
            return "isel -- " + Math.random();
        }


        //@Path("/aluno/*")
        public String student(HttpServletRequest req) {
            return "aluno nr: --- " + req.getPathInfo();
        }
    }


}