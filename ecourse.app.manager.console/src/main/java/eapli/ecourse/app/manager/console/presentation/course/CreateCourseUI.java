package eapli.ecourse.app.manager.console.presentation.course;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCourseUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCourseUI.class);
    private static final Integer DESCRIPTION_CARACTER_LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
    private final CreateCourseController controller = new CreateCourseController();

    @Override
    protected boolean doShow() {
        String name = Console.readLine("Name");
        String description = Console.readLine("Description (max " + DESCRIPTION_CARACTER_LIMIT + " characters)");
        Integer minEnroll = null;
        Integer maxEnroll = null;
        System.out.println("\nAdd enrollment limits?\n");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int option = Console.readOption(1, 1, 2);
        try {
            if (option == 1) {
                minEnroll = Console.readInteger("Minimum enrollment limit");
                maxEnroll = Console.readInteger("Maximum enrollment limit");
            }
            controller.createCourse(name, description, minEnroll, maxEnroll);
            System.out.println("\nCourse created with success!");
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println("Unfortunately there was an unexpected error in the application. " +
                    "Please try again and if the problem persists, contact your system administrator.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Create Course";
    }
}
