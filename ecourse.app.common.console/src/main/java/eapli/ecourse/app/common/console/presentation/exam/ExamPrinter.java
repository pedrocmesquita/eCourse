package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Exam;
import eapli.framework.visitor.Visitor;

public class ExamPrinter implements Visitor<Exam>
{
    public final static String SPACING = "%-20s%-" + 50 + "s%-12s%-12s%-8s";
    public final static String HEADER = String.format("#  " + SPACING, "TITLE", "DESCRIPTION", "OPENING DATE", "CLOSING DATE", "STATE");
    
    @Override
    public void visit(final Exam visitee)
    {
        System.out.printf(SPACING, visitee.identity(), visitee.getDescription(),
                visitee.getDateOpen(), visitee.getDateClose(), visitee.getState());
    }
    
}
