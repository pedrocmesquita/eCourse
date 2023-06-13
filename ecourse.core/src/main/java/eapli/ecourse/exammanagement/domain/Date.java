package eapli.ecourse.exammanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Objects;

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
        Preconditions.noneNull(openDate, closeDate);
        checkDates(openDate, closeDate);
        setOpenDate(openDate);
        setCloseDate(closeDate);
    }

    //todo

    /**
     * Checks if openDate is before closeDate
     */
    private void checkDates(Calendar openDate, Calendar closeDate) {
        Calendar today = Calendar.getInstance();
        if (closeDate.before(today) || openDate.before(today))
            throw new IllegalArgumentException("Dates must be in the future");
        if (openDate.after(closeDate))
            throw new IllegalArgumentException("Close date must be after open date");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(openDate, date.openDate) && Objects.equals(closeDate, date.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openDate, closeDate);
    }

    protected void setOpenDate(Calendar openDate) {
        this.openDate = openDate;
    }

    protected void setCloseDate(Calendar closeDate) {
        this.closeDate = closeDate;
    }
}
