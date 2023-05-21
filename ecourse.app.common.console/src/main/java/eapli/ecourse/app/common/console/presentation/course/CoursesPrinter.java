package eapli.ecourse.app.common.console.presentation.course;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.visitor.Visitor;

public class CoursesPrinter implements Visitor<Course> {
    //Application.settings().getCourseDescriptionCharacterLimit()

    public final static String SPACING = "%-20s%-" + 50 + "s%-12s%-12s%-8s";
    public final static String HEADER =  String.format("#  " + SPACING, "NAME", "DESCRIPTION", "MIN. ENROLL", "MAX. ENROLL", "STATE");

    @Override
    public void visit(final Course visitee) {
        System.out.printf(SPACING, visitee.identity(), visitee.description(),
                (visitee.enrollLimit() == null ? "none" : visitee.enrollLimit().getMinEnroll()),
                (visitee.enrollLimit() == null ? "none" : visitee.enrollLimit().getMaxEnroll()),
                visitee.state());
    }

}
