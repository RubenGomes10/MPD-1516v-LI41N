package series.tvmazeapi.dto;

/**
 * Created by lfalcao on 27/05/16.
 */
public class ShowDto {

    public int id;
    public String name;
    public String language;
    public String[] genres;
    public NetworkDto network;
    public String premiered;

    public  class NetworkDto {
        public int id;
        public String name;
        public CountryDto country;

    }

    public class CountryDto {
        public String name;
        public String code;
        public String timezone;

    }
}
