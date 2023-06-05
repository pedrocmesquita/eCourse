package eapli.ecourse.exammanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class Date {

    @Temporal(TemporalType.DATE)
    private Calendar openDate;

    @Temporal(TemporalType.DATE)
    private Calendar closeDate;

    protected Date() {
        //ORM only
    }

    protected Date(Calendar openDate, Calendar closeDate) {
        checkDates();
        setOpenDate(openDate);
        setCloseDate(closeDate);
    }

    //todo
    /**
     * Checks if openDate is before closeDate
     *
     */
    private void checkDates(){

    }

    protected void setOpenDate(Calendar openDate) {
        this.openDate = openDate;
    }

    protected void setCloseDate(Calendar closeDate) {
        this.closeDate = closeDate;
    }
}
