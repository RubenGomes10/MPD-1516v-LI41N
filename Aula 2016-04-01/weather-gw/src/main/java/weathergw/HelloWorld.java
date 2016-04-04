package weathergw;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lfalcao on 07/03/16.
 */
public class HelloWorld {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("foo/text.txt");
        Path path = null;
        try {
            path = Paths.get(url.toURI());

            System.out.println("Em primeiro lugar, boa noite. Em 2º o Sporting.");

            System.out.println(Files.readAllLines(path));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
