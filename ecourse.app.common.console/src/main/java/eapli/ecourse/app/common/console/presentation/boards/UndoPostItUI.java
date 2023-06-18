package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.application.UndoPostItController;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.presentation.console.AbstractUI;

public class UndoPostItUI extends AbstractUI {
    private final UndoPostItController theController = new UndoPostItController();
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser()));
@Override
    protected boolean doShow() {
        System.out.println("Select a Board to undo a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final SelectPostItWidget postItWidget = new SelectPostItWidget(theController.findPostItsByOwner(selectedBoard, theController.getUser()));
        System.out.println("Select a PostIt to undo");
        final PostIt selectedPostIt = postItWidget.selectPostIts();
        if (selectedPostIt == null)
            return false;
        theController.rollbackPost(selectedBoard, selectedPostIt);
        return false;
    }

    @Override
    public String headline() {
        return "Undo changes to PostIt";
    }
}
