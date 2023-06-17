package eapli.ecourse.boardmanagement.newdomain;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.time.util.CurrentTimeCalendars;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class Log implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    private Board id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdOn;

    protected Log() {
        //ORM
    }

    protected Log(String description) {
        this.description = description;
        this.createdOn = CurrentTimeCalendars.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(logId, log.logId) && Objects.equals(description, log.description) && Objects.equals(createdOn, log.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, description, createdOn);
    }

    @Override
    public String toString() {
        return
                "LogID: " + logId +
                "Description: " + description +
                "Created on:" + createdOn;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Log)) {
            return false;
        }

        final Log that = (Log) other;
        if (this == that) {
            return true;
        }

        return logId.equals(that.logId) && description.equals(that.description)
                && createdOn.equals(that.createdOn);
    }

    @Override
    public Long identity() {
        return this.logId;
    }
}
