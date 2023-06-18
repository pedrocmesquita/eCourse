package eapli.ecourse.app.student.console.presentation.exam;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.visitor.Visitor;

public class ExamsPrinter implements Visitor<Exam> {
    //Application.settings().getCourseDescriptionCharacterLimit()

    public final static String SPACING = "%-20s%-" + 50 + "s%-12s";
    public final static String HEADER =  String.format("#  " + SPACING, "NAME", "DESCRIPTION", "DATE");

    @Override
    public void visit(final Exam visitee) {
        System.out.printf(SPACING, visitee.identity(), visitee.getDescription(), visitee.getDate());
    }

}
