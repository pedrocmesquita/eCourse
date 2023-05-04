package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.OpenCloseEnrollmentController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//todo remove code duplication
public class OpenCloseEnrollmentUI extends AbstractUI {

    OpenCloseEnrollmentController controller = new OpenCloseEnrollmentController();
    private static final Logger LOGGER = LogManager.getLogger(OpenCloseCourseUI.class);

    @Override
    protected boolean doShow() {
        Iterable<Course> courses = controller.allCoursesEnrollOrProgress();
        if (!courses.iterator().hasNext()) {
            System.out.println("There are no courses available");
            return true;
        }
        final SelectWidget<Course> selector = new SelectWidget<>(CoursesPrinter.HEADER, courses, new CoursesPrinter());
        selector.show();
        final Course selectedCourse = selector.selectedElement();
        try {
            controller.toggleOpenCloseEnroll(selectedCourse);
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
        return "Open / Close Enrollment";
    }
}
