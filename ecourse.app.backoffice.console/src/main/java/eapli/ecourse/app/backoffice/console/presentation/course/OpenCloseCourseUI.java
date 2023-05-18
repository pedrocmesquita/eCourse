package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.OpenCloseCourseController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenCloseCourseUI extends AbstractUI {

    private final OpenCloseCourseController controller = new OpenCloseCourseController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allNotEnrollCourses());
    private final Logger LOGGER = LogManager.getLogger(OpenCloseCourseUI.class);

    @Override
    protected boolean doShow() {
        try {
            controller.toggleOpenCloseCourse(courseWidget.selectCourse());
        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
            System.out.println(
                    "WARNING: It is not possible to change the course state because it was changed by another user");
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Open / Close Course";
    }
}
