package eapli.ecourse.app.manager.console.presentation.teacheruser;

import eapli.ecourse.app.manager.console.presentation.course.CreateCourseUI;
import eapli.ecourse.app.manager.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.coursemanagement.application.AssignTeacherToCourseController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssignTeacherToCourseUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCourseUI.class);
    private final AssignTeacherToCourseController controller = new AssignTeacherToCourseController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allNotClosedCourses());

    @Override
    protected boolean doShow() {
        final Course selectedCourse = courseWidget.selectCourse();

        final SelectWidget<TeacherUser> selector = new SelectWidget<>(TeacherUserPrinter.HEADER,
                controller.allTeachersJoinWithSystemUser(), new TeacherUserPrinter());
        selector.show();
        try {
            controller.assignTeacherToCourse(selector.selectedElement(), selectedCourse);
        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
            System.out.println(
                    "WARNING: It is not possible to change the course state because it was changed by another user");
        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }
        System.out.println("Teacher assigned with success!");
        return true;
    }

    @Override
    public String headline() {
        return "Assign teacher to course";
    }
}
