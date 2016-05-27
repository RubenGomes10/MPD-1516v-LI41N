package gson.meppingTypes;

/**
 * Created by lfalcao on 27/05/16.
 */
public class Actor {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
