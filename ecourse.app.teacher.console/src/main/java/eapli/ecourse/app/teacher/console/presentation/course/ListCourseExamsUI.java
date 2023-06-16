package eapli.ecourse.app.teacher.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.application.ListCourseExamsController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.AbstractUI;

public class ListCourseExamsUI extends AbstractUI {
    private final ListCourseExamsController controller = new ListCourseExamsController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allCoursesTeacherIsAssigned(controller.getUserAcronym()));

    @Override
    protected boolean doShow() {

        try {

            System.out.println("Select a Course to view exams of:");
            final Course selectedCourse = courseWidget.selectCourse();

            Iterable<Exam> exams = controller.listCourseExams(selectedCourse);

            if (exams.iterator().hasNext()){
                for (Exam exam : exams) {
                    System.out.println(exam.toString());
                }
            } else {
                System.out.println("There are no exams for this course");
            }

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        return true;
    }

    @Override
    public String headline () {
        return "List All Exams in a Course";
    }
}