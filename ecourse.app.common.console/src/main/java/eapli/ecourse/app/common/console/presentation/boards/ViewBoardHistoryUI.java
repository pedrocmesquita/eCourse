package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.application.ViewBoardHistoryController;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.presentation.console.AbstractUI;

public class ViewBoardHistoryUI extends AbstractUI
{
    private final ViewBoardHistoryController controller = new ViewBoardHistoryController();
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(controller.findBoardsByOwner(controller.getUser()));
    private final UserRepository repository = PersistenceContext.repositories().users();
    
    @Override
    protected boolean doShow()
    {
        System.out.println("Choose a Board to view the history of:");
        
        final Board selectedBoard = boardWidget.selectBoard();
        
        if (selectedBoard == null)
        {
            return false;
        }
        
        try
        {
            Iterable<Log> logs = controller.listBoardLogs(selectedBoard);
            
            if (logs.iterator().hasNext())
            {
                for (Log log : logs)
                {
                    System.out.println(log.toString());
                }
            }
            else
            {
                System.out.println("There are no logs on this Board.");  //shouldnt happen, theres a log for creation
            }
            
        } catch (IllegalArgumentException iae)
        {
            System.out.println(iae.getMessage());
        }
        
        return true;
    }
    
    @Override
    public String headline()
    {
        return "View The History of Changes in a Board";
    }
    
}
