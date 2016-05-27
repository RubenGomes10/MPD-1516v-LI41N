package series.domain;

import java.time.LocalDate;

/**
 * Created by lfalcao on 23/05/16.
 */
public class Show {
    private String title;
    private String description;
    private LocalDate startDate;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


}
