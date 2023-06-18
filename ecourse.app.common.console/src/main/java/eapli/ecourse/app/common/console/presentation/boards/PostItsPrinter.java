package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.visitor.Visitor;

public class PostItsPrinter implements Visitor<PostIt> {
    /**
     * The constant SPACING.
     */
    public final static String SPACING = "%-20s%-50s%-12s";
    /**
     * The constant HEADER.
     */
    public final static String HEADER = String.format("#  " + SPACING, "Board", "Column", "Row");

    @Override
    public void visit(final PostIt visitee) {
        System.out.printf(SPACING, visitee.getBoard().identity(), visitee.getColumn().value(), visitee.getRow().value());
    }
}
