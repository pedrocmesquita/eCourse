package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.OpenCloseEnrollmentController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class OpenCloseEnrollmentUI extends AbstractUI {

    private final OpenCloseEnrollmentController controller = new OpenCloseEnrollmentController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allCoursesOpenOrEnroll());
    private final Logger LOGGER = LogManager.getLogger(OpenCloseCourseUI.class);

    @Override
    protected boolean doShow() {
        Course course = null;
        try {
            course = controller.toggleOpenCloseEnroll(courseWidget.selectCourse());
        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
            System.out.println(
                    "WARNING: It is not possible to change the course state because it was changed by another user");
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }
        System.out.println("\nCourse enrollment " + (Objects.requireNonNull(course).getState() == State.ENROLL ? "opened" : "closed") + " with success!");
        return true;
    }

    @Override
    public String headline() {
        return "Open / Close Enrollment";
    }
}
