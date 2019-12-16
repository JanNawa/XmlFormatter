package assignment_5.datamodel;

import java.time.*;

/**
 * This class is a based on Summary report as every report need to specify the period of time.
 * @author Jan
 */
public abstract class Summary {
    // period of time in the report
    private LocalDate startDate;
    private LocalDate endDate;

    // constructor to define period of time (every child class need to specify date)
    public Summary(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter for startDate
    public LocalDate getStartDate() {
        return startDate;
    }

    // Getter for endDate
    public LocalDate getEndDate() {
        return endDate;
    }
}