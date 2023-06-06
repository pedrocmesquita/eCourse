package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.visitor.Visitor;

public class ExamPrinter implements Visitor<Exam>
{
    public final static String SPACING = "%-20s%-" + 50 + "s%-12s";
    public final static String HEADER = String.format("#  " + SPACING, "TITLE", "DESCRIPTION", "DATE");

    @Override
    public void visit(final Exam visitee)
    {
        System.out.printf(SPACING, visitee.identity(), visitee.getDescription(), visitee.getDate());
    }
}
