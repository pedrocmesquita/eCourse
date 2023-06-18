package eapli.ecourse.app.student.console.presentation.exam;

import eapli.ecourse.app.common.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.application.ListStudentExamsController;
import eapli.ecourse.exammanagement.application.TakeExamController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.Grade;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.validations.Preconditions;

public class TakeExamUI extends AbstractUI {

    private final TakeExamController controller = new TakeExamController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allCoursesStudentIsAssigned(controller.getUser()));
    @Override
    protected boolean doShow() {

        try {
            System.out.println("Select the course you want to take the exam of: ");
            final Course selectedCourse = courseWidget.selectCourse();
            if (selectedCourse == null)
                return false;

            System.out.println("Select the exam you want to take: ");
            SelectExamWidget examWidget = new SelectExamWidget(controller.listCourseExams(selectedCourse));
            final Exam selectedExam = examWidget.selectExam();
            if (selectedExam == null)
                return false;

            Double grade = Console.readDouble("Grade: ");
            Preconditions.ensure(grade >= 0 && grade <= 20, "Grade must be between 0 and 20");
            Grade gradeObj = new Grade(grade);
            controller.takeExam(selectedExam, gradeObj);

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Take an Exam";
    }
}
