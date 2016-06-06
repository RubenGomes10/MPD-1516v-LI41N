package series.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

/**
 * Created by lfalcao on 27/05/16.
 */
public class HttpUtils {
    static public <T> T  getFromUri(String urlStr, Function<String, T> converter) {

        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return converter.apply(reader.lines().collect(joining()));

        } catch (IOException e) {
            return null;
        }

    }



}
