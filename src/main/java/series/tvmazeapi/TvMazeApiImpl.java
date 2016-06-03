package series.tvmazeapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import series.tvmazeapi.dto.ShowDto;
import series.utils.HttpUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * All the accesses to TvMaze Web API are done in this class.
 */
public class TvMazeApiImpl implements ShowsApi {
    @Override
    public List<ShowDto> getShows() {

        return HttpUtils.getFromUri(TvMazeUri.shows(), str -> fromJson(str, new TypeToken<List<ShowDto>>() {}.getType()));
    }

    @Override
    public ShowDto getShow(int id) {
        return HttpUtils.getFromUri(TvMazeUri.show(id), str -> fromJson(str, ShowDto.class));
    }

    private <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);

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

//    public static List<CastDto> getCast(int serieId) {
//
//    }
}
