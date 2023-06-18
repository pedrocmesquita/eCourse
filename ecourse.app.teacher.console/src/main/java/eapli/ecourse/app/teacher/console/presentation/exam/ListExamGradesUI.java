package eapli.ecourse.app.teacher.console.presentation.exam;

import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
import eapli.ecourse.exammanagement.application.ListTeacherExamGradesController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.visitor.Visitor;

public class ListExamGradesUI extends AbstractUI {
   private final ListTeacherExamGradesController controller = new ListTeacherExamGradesController();

    @Override
    protected boolean doShow() {
        controller.findAllTeacherExams();
        return true;
    }

    @Override
    public String headline() {
        return "List exam grades of my courses";
    }
}