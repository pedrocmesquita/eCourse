package eapli.ecourse.boardmanagement.domain;

import eapli.framework.time.util.CurrentTimeCalendars;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Class used for storing updates on boards
 */
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @Embedded
    String info;
    @Temporal(TemporalType.DATE)
    Calendar date;
    
    public Log(String info)
    {
        this.info = info;
        date = CurrentTimeCalendars.now();
    }

    public Log() {

    }

    @Override
    public String toString()
    {
        return date + " - " + info;
    }
}
