package eapli.ecourse.app.manager.console.presentation.teacheruser;

import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.visitor.Visitor;

public class TeacherUserPrinter implements Visitor<TeacherUser> {

    public final static String SPACING = "%-20s%-20s%-8s";
    public final static String HEADER =  String.format("#  " + SPACING, "First Name", "Last Name", "Acronym");

    @Override
    public void visit(final TeacherUser visitee) {
        System.out.printf(SPACING, visitee.user().name().firstName(),
                visitee.user().name().lastName(), visitee.acronym());
    }
}
