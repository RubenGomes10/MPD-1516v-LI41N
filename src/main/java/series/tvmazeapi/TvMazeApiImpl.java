package series.tvmazeapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import series.tvmazeapi.dto.ShowDto;
import series.utils.HttpUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * All the accesses to TvMaze Web API are done in this class.
 */
public class TvMazeApiImpl implements ShowsApi {
    AsyncHttpClient httpClient = new DefaultAsyncHttpClient();

    private <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);

    }

    @Override
    public CompletableFuture<List<ShowDto>> getShows() {
        return prepareRequest(TvMazeUri.shows(), str -> fromJson(str, new TypeToken<List<ShowDto>>() {}.getType()));
    }


    @Override
    public CompletableFuture<ShowDto> getShow(int id) {
        return prepareRequest(TvMazeUri.show(id), str -> fromJson(str, ShowDto.class));
    }


    private <T> CompletableFuture<T> prepareRequest(String uri, Function<String, T> mapper) {
        return httpClient.prepareGet(uri)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .thenApply(mapper);

    }

    private static class TvMazeUri {
        private static final String BASE_URI = "http://api.tvmaze.com/";

        private static String uriGenerator(String path) {
            return BASE_URI + path;
        }

        private static String uriGenerator(String path, int id) {
            return uriGenerator(path) + "/" + id;
        }
        public static String shows() {
            return uriGenerator("shows");
        }

        public static String show(int id) {
            return uriGenerator("shows", id);
        }
    }

}
