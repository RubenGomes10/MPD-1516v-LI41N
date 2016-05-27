package series.tvmazeapi.dto;

import com.google.gson.Gson;
import series.utils.HttpUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * All the accesses to TvMaze Web API are done in this class.
 */
public class TvMazeApi {

    public static List<SerieDto> getSeries() {

    }

    public static SerieDto getSerie(int id) {
        return HttpUtils.getFromUri("", str -> fromJson(str, SerieDto.class));
    }

    private static <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);

    }

//    public static List<CastDto> getCast(int serieId) {
//
//    }

    }

}
