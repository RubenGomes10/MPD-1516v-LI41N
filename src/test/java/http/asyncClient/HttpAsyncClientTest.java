package http.asyncClient;

import org.asynchttpclient.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lfalcao on 06/06/16.
 */
public class HttpAsyncClientTest {
    @Test
    public void shouldMakeAnAsynchronousHttpGetRequest() throws Exception {
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();

        ListenableFuture<Response> responseFuture = httpClient.prepareGet("http://api.tvmaze.com/shows/890")
                .execute(new AsyncCompletionHandler<Response>() {
                    @Override
                    public Response onCompleted(Response response) throws Exception {
                        String respStr = response.getResponseBody();
                        assertThat(respStr, notNullValue());
                        assertThat(respStr, containsString("Young Dracula"));

                        return response;
                    }
                });

        Response response = responseFuture.get();


    }
}
