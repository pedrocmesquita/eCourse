package eapli.ecourse.app.teacher.console.presentation.classes;

import eapli.ecourse.Application;
import eapli.ecourse.classmanagement.application.ScheduleClassController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleClassUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleClassUI.class);
    private final ScheduleClassController controller = new ScheduleClassController();

    @Override
    protected boolean doShow() {
        String name = Console.readLine("Course");
        Date openDate = Console.readDate("Insert the date of the class.", "dd/mm/yyyy");
        Date openHour = Console.readDate("Insert the starting hour of the class.", "hh:mm");
        Date closeHour = Console.readDate("Insert the ending hour of the class.", "hh:mm");
        int instructorID = Console.readInteger("Insert your instructor ID.");

        Calendar classDateTime = Calendar.getInstance();
        Calendar classEndTime = Calendar.getInstance();
        classDateTime.set(openDate.getYear(), openDate.getMonth(), openDate.getDay(), openHour.getHours(), openHour.getMinutes(), openHour.getSeconds());
        classEndTime.set(openDate.getYear(), openDate.getMonth(), openDate.getDay(), closeHour.getHours(), closeHour.getMinutes(), closeHour.getSeconds());

        try {
            System.out.println("1!");
            controller.scheduleClass(name, classDateTime, classEndTime);
            System.out.println("\nClass scheduled with success!");
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println("Unfortunately there was an unexpected error in the application. " +
                    "Please try again and if the problem persists, contact your system administrator.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Schedule a Class";
    }
}
