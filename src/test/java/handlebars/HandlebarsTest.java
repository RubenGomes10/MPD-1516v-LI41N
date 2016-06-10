package handlebars;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by lfalcao on 06/06/16.
 */
public class HandlebarsTest {

    @Test
    public void shouldExecuteAnHandlebarsTemplateInline() throws Exception {
        Handlebars handlebars = new Handlebars();

        Template template = handlebars.compileInline("Hello {{this}}!");

        //assertEquals("Hello SLB", template.apply("SLB1"));

        assertThat("SLB", equalTo(template.apply("SLB")));
    }


    @Test
    public void shouldExecuteAnHandlebarsTemplateFromFile() throws Exception {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");

        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("hello");

        assertThat(template.apply("SLB"), containsString("SLB"));
    }

    @Test
    public void shouldExecuteAnHandlebarsTemplateFromFileWithCustomInstance() throws Exception {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");

        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("player");

        String templateStr = template.apply(new Player("Jonas", 32, Arrays.asList(new Team("Benfica"), new Team("Valencia"))));
        //assertThat(templateStr, containsString("Jonas"));

        System.out.println(templateStr);
    }

    private class Player {
        private final String name;
        private final int age;
        private List<Team> teams;

        public Player(String name, int age, List<Team> teams) {
            this.name = name;

            this.age = age;
            this.teams = teams;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public List<Team> getTeams() {
            return teams;
        }
    }

    public class Team {
        private String name;

        public Team(String name) {

            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
