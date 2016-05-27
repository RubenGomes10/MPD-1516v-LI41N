package gson.meppingTypes;


import gson.meppingTypes.Actor;

import java.util.List;

/**
 * Created by lfalcao on 27/05/16.
 */
public class Movie
{
    private String name;
    private int yearRelease;

    private List<Actor> actors;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", yearRelease=" + yearRelease +
                ", actors=" + actors +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
