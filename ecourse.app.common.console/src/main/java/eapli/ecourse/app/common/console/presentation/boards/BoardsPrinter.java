package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.visitor.Visitor;

public class BoardsPrinter implements Visitor<Board> {
    public final static String SPACING = "%-20s%-50s%-12s%-12s";
    public final static String HEADER = String.format("#  " + SPACING, "TITLE", "NUM. COLUMNS", "NUM. ROWS", "STATE");

    @Override
    public void visit(final Board visitee) {
        System.out.printf(SPACING, visitee.identity(), visitee.getNumberColumns(), visitee.getNumberRows(), visitee.state());
    }
}

