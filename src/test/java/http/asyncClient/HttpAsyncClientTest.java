package http.asyncClient;

import org.asynchttpclient.*;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.join;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lfalcao on 06/06/16.
 */
public class HttpAsyncClientTest {
    AsyncHttpClient httpClient = new DefaultAsyncHttpClient();

    @Test
    public void shouldMakeAnAsynchronousHttpGetRequestWithAsyncCompletionHandler() throws Exception {

        System.out.println("Begin test");
        ListenableFuture<Response> responseFuture = httpClient.prepareGet("http://api.tvmaze.com/shows/890")
                .execute(new AsyncCompletionHandler<Response>() {
                    @Override
                    public Response onCompleted(Response response) throws Exception {
                        String respStr = response.getResponseBody();
                        assertThat(respStr, notNullValue());
                        assertThat(respStr, containsString("Young Dracula"));
                        System.out.println("repStr: " + respStr);
                        return response;
                    }
                });

        Response response = responseFuture.get();
    }

    @Test
    public void shouldMakeAnAsynchronousHttpGetRequestWithCompletableFuture() throws InterruptedException {
        httpClient.prepareGet("http://api.tvmaze.com/shows/890")
                .execute(new AsyncCompletionHandler<Response>() {
                    @Override
                    public Response onCompleted(Response response) throws Exception {
                        System.out.println("Thread id on onCompleted:" + Thread.currentThread().getId());
                        return response;
                    }
                })
                .toCompletableFuture()
                .thenApplyAsync(this::getResponse)
                .thenAcceptAsync(this::print)
                .join();


        //Thread.sleep(5000);
    }

    @Test
    public void shouldMakeTwoSimultaneousAsynchronousHttpGetRequestWithCompletableFuture() throws InterruptedException {

        CompletableFuture<String> response1 = getStringCompletableFuture("http://api.tvmaze.com/shows/890");;

        CompletableFuture<String> response2 = httpClient.prepareGet("http://api.tvmaze.com/shows/891")
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody);

        response1.thenAcceptBoth(response2, this::concatAndShowStrings)
                .join();

    }

    @Test
    public void shouldMakeTwoSequentialAsynchronousHttpGetRequestWithCompletableFuture() throws InterruptedException {
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();


        CompletableFuture<String> response1 =
                getStringCompletableFuture("http://api.tvmaze.com/shows/890");



        response1.thenCompose(this.makeAnotherRequest("http://api.tvmaze.com/shows/891"))
        .thenAccept(this::print)
        .join();


    }

    private Function<String, CompletionStage<String>> makeAnotherRequest(String uri) {
        return (str) -> {
            print(str);
            return getStringCompletableFuture(uri);
        };
    }

    private CompletableFuture<String> getStringCompletableFuture(String uri) {
        return httpClient
        .prepareGet(uri)
        .execute()
        .toCompletableFuture()
        .thenApply(Response::getResponseBody);
    }

    private void concatAndShowStrings(String str1, String str2) {
        assertThat(str1, notNullValue());
        assertThat(str2, notNullValue());

        assertThat(str1, containsString("Young Dracula"));
        assertThat(str2, containsString("Hard Knocks"));

        print(str1);
        print(str2);

    }


    private void print(String s) {
        System.out.println("Thread id on print function:" + Thread.currentThread().getId());
        System.out.println("Timestamp:" + System.currentTimeMillis());
        System.out.println(s);
    }

    private String getResponse(Response response) {
        System.out.println("Thread id on thenApply function:" + Thread.currentThread().getId());
        return response.getResponseBody();
    }
}
