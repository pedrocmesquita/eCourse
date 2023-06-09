package eapli.ecourse.app.manager.console.presentation.classes;

import eapli.ecourse.classmanagement.application.ScheduleClassController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class ScheduleClassUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleClassUI.class);
    private final ScheduleClassController controller = new ScheduleClassController();

    @Override
    protected boolean doShow() {
        String name = Console.readLine("What's the course you want to schedule a class for?");
        Date openDate = Console.readDate("\nWhat's the date of the class? (DD/MM/YYYY)", "dd/MM/yyyy");
        Date openHour = Console.readDate("\nWhat's the starting hour of the class? (HH:MM)", "hh:mm");
        Date closeHour = Console.readDate("\nWhat's the ending hour of the class? (HH:MM)", "hh:mm");

        Calendar classDateTime = Calendar.getInstance();
        Calendar classEndTime = Calendar.getInstance();
        classDateTime.setTime(openDate);
        classDateTime.set(Calendar.HOUR_OF_DAY, openHour.getHours());
        classDateTime.set(Calendar.MINUTE, openHour.getMinutes());

        classEndTime.setTime(openDate);
        classEndTime.set(Calendar.HOUR_OF_DAY, closeHour.getHours());
        classEndTime.set(Calendar.MINUTE, closeHour.getMinutes());

        if (classDateTime.before(Calendar.getInstance())) {
            System.out.println("\nThe date of the class has already passed. Try again with a future date.\n");
            return false;
        }

        if (classDateTime.after(classEndTime)) {
            System.out.println("\nThe starting hour must be before the ending hour. Try again with valid hours.\n");
            return false;
        }

        try {
            controller.scheduleClass(name, classDateTime, classEndTime);
            System.out.println("\nClass scheduled with success!\n");
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println("Unfortunately, there was an unexpected error in the application. " +
                    "Please try again, and if the problem persists, contact your system administrator.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Schedule a Class";
    }
}
