package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.visitor.Visitor;

public class CoursesPrinter implements Visitor<Course> {

    public final static String SPACING = "%-10s%-50s%-12s%-12s%-8s";
    public final static String HEADER =  String.format("#  " + SPACING, "NAME", "DESCRIPTION", "MAX. ENROLL", "MIN. ENROLL", "STATE");

    @Override
    public void visit(final Course visitee) {
        System.out.printf(SPACING, visitee.identity(), visitee.getDescription(), visitee.getEnrollLimit().getMinEnroll(), visitee.getEnrollLimit().getMaxEnroll(), visitee.getState());
    }

    public void test(Object ... args) {

    }
}
