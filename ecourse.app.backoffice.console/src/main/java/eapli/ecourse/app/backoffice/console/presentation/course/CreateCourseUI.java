package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CreateCourseUI extends AbstractUI {
    private final CreateCourseController controller = new CreateCourseController();

    @Override
    protected boolean doShow() {
        String name = Console.readLine("Name");
        String description = Console.readLine("Description");
        Integer minEnroll = null;
        Integer maxEnroll = null;
        boolean limit = Console.readBoolean("Define enrollment limits?");
        if(limit) {
            minEnroll = Console.readInteger("Mininum enrollement limit");
            maxEnroll = Console.readInteger("Maximum enrollement limit");
        }
        controller.createCourse(name, description, minEnroll, maxEnroll);
        return false;
    }

    @Override
    public String headline() {
        return "Create Course";
    }
}
