package eapli.ecourse.app.student.console.presentation.exam;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.application.ListStudentExamsController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.AbstractUI;

public class ListStudentExamsUI extends AbstractUI {

    private final ListStudentExamsController controller = new ListStudentExamsController();

    @Override
    protected boolean doShow() {

        try {
            Iterable<Course> userCourses = controller.userCourses();
            Iterable<Exam> exams = controller.listExams(userCourses);

            for (Exam exam : exams) {
                System.out.println(exam);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "List my future exams";
    }
}