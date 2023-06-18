package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.app.common.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.boardmanagement.application.ShareBoardController;
import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Share board ui.
 */
public class ShareBoardUI extends AbstractUI {
    private final ShareBoardController controller = new ShareBoardController();
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(controller.findBoardsByOwner(controller.getUser()));
    private final UserRepository repository = PersistenceContext.repositories().users();
    @Override
    protected boolean doShow() {

        System.out.println("Select a Board to share");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;

        try{


            SystemUser shareUser = null;
            while (shareUser == null) {
                String username = Console.readLine("Enter the username of the user to share the board with: ");
                for (SystemUser user : repository.findByActive(true)) {
                    if (user.username().toString().equals(username)) {
                        shareUser = user;
                        break;
                    }
                }
                if (shareUser == null) {
                    System.out.println("User not found. Please try again.");
                }
            }

            String option = Console.readLine("Do you want to give them read or write permissions? (R/W)");
            if (option.equalsIgnoreCase("R")){
                controller.shareBoard(selectedBoard, shareUser, false);
                selectedBoard.addPermission(new BoardPermission(controller.getUser(), AccessLevel.READ));
            System.out.println("Board sucessfully shared with " + shareUser.username() + " with " + AccessLevel.READ + " permissions."); }
            else if (option.equalsIgnoreCase("W")){
                controller.shareBoard(selectedBoard, shareUser, true);
                selectedBoard.addPermission(new BoardPermission(controller.getUser(), AccessLevel.WRITE));
            System.out.println("Board sucessfully shared with " + shareUser.username().toString() + " with " + AccessLevel.WRITE + " permissions."); }
            else {
                throw new IllegalArgumentException("Invalid option");
            }

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (IntegrityViolationException e){
            System.out.println("That user already has access to this board");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Share a board";
    }
}
