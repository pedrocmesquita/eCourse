package eapli.ecourse.boardmanagement.domain;

import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;

/**
 * Class used for storing updates on boards
 */
public class Log
{
    String info;
    Calendar date;
    
    public Log(String info)
    {
        this.info = info;
        date = CurrentTimeCalendars.now();
    }
    
    @Override
    public String toString()
    {
        return date + " - " + info;
    }
}
