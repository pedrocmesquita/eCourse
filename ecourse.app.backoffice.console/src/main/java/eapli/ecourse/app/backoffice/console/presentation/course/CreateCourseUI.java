package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.framework.presentation.console.AbstractUI;

public class CreateCourseUI extends AbstractUI {
    private final CreateCourseController controller = new CreateCourseController();

    @Override
    protected boolean doShow() {
        //todo
        return false;
    }

    @Override
    public String headline() {
        return "Create Course";
    }
}
