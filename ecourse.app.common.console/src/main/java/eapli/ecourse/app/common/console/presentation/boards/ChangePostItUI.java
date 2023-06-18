package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.application.ChangePostItController;
import eapli.ecourse.boardmanagement.newdomain.AccessLevel;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.Content;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangePostItUI extends AbstractUI {
    private final ChangePostItController theController = new ChangePostItController();

    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser()));


    @Override
    protected boolean doShow() {
        System.out.println("Select a Board to change a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final SelectPostItWidget postItWidget = new SelectPostItWidget(theController.findPostIts(selectedBoard));
        System.out.println("Select a PostIt to change");
        final PostIt selectedPostIt = postItWidget.selectPostIts();
        if (selectedPostIt == null)
            return false;
        final String option = Console.readLine("Do you want to change position or content? (p/c)");
        if(option.equals("p")){
            final int col = Console.readInteger("New rolumn of postit:");
            final int row = Console.readInteger("New row of postit:");
            theController.changePostPosition(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), row, col,selectedPostIt);
        }
        else if(option.equals("c")){
            final String option1 = Console.readLine("Is the new content text or image? (t/i)");
            final Content content = new Content();
            if(option1.equals("t")){
                final String text = Console.readLine("Text of postit:");
                theController.changePostContent(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), content.createContentText(text),selectedPostIt);
            }
            else if(option1.equals("i")){
                final String image = Console.readLine("Image of postit:");
                theController.changePostContent(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), content.createContentText(image),selectedPostIt);
            }
            else{
                System.out.println("Invalid option");
            }
        }
        else{
            System.out.println("Invalid option");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Change PostIt";
    }
}
