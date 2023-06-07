package eapli.ecourse.app.student.console.presentation.exam;

import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
import eapli.ecourse.exammanagement.application.ListStudentExamsController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListExamsUI extends AbstractListUI<Exam>
{
   private final ListStudentExamsController controller = new ListStudentExamsController();

    @Override
    protected Iterable<Exam> elements() {
        return controller.findAllStudentExams();
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
        return "Future Exams";
    }
}