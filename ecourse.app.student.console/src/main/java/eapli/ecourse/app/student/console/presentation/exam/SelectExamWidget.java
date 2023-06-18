package eapli.ecourse.app.student.console.presentation.exam;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.SelectWidget;

/**
 * Course widget to show and select a course
 */
public class SelectExamWidget {

    private final Iterable<Exam> exams;

    public SelectExamWidget(Iterable<Exam> exams) {
        this.exams = exams;
    }

    /**
     * shows courses passed as parameter in constructor and ask to select one
     *
     * @return selected course
     */
    public Exam selectExam() {
        if (!exams.iterator().hasNext()) {
            System.out.println("There are no exams available.");
            return null;
        }
        final SelectWidget<Exam> selector = new SelectWidget<>(ExamsPrinter.HEADER, exams, new ExamsPrinter());
        selector.show();
        return selector.selectedElement();
    }
}
