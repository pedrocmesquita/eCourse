package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.application.CreatePostItController;
import eapli.ecourse.boardmanagement.newdomain.AccessLevel;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CreatePostItsUI extends AbstractUI {

    private final CreatePostItController theController = new CreatePostItController();

    private final AccessLevel accessLevel = AccessLevel.WRITE;
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser(), accessLevel));

    @Override
    protected boolean doShow() {
        System.out.println("Select a Board to create a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final int col = Console.readInteger("Column of postit:");
        final int row = Console.readInteger("Row of postit:");
        final String option = Console.readLine("Is the content text or image? (t/i)");
        if(option.equals("t")){
            final String text = Console.readLine("Text of postit:");
            System.out.println(theController.attemptCreatePost(selectedBoard, row, col, text, null).toString());
        }
        else if(option.equals("i")){
            final String image = Console.readLine("Image of postit:");
            System.out.println(theController.attemptCreatePost(selectedBoard, row, col, null, image).toString());
        }
        else{
            System.out.println("Invalid option");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Create PostIt";
    }

}
