package eapli.ecourse.app.teacher.console.presentation.Exam;

import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
import eapli.ecourse.exammanagement.application.ListTeacherExamGradesController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListExamGradesUI extends AbstractListUI<Exam>
{
   private final ListTeacherExamGradesController controller = new ListTeacherExamGradesController();

    @Override
    protected Iterable<Exam> elements() {
        return controller.findAllTeacherExams();
    }

    @Override
    protected Visitor<Exam> elementPrinter() {
        return new ExamPrinter();
    }

    @Override
    protected String elementName() {
        return "Exams";
    }

    @Override
    protected String listHeader() {
        return ExamPrinter.HEADER;
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "Exams";
    }
}