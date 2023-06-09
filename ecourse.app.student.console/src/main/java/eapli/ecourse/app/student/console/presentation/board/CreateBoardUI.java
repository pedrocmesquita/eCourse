package eapli.ecourse.app.student.console.presentation.board;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.BoardEntry;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardUI extends AbstractUI {
    private final CreateBoardController theController = new CreateBoardController();

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

    /**
     * User want to create a new Board.
     * Ask User fields.
     * BoardTitle, BoardNRow, BoardNCol,
     * @return false
     */
    @Override
    protected boolean doShow() {
        final String boardTitle = Console.readLine("Board Title:");
        final String boardNCols = Console.readLine("Board Number of Columns:");
        final String boardNRows = Console.readLine("Board Number of Rows:");

        List<BoardEntry> allBoardEntrys = new ArrayList<>();


        try{
            //Board Entrys for columns
            System.out.println("----COLUMNS ENTRYS----");
            int nCols = Integer.parseInt(boardNCols);
            for(int i = 1; i <= Integer.parseInt(boardNCols); i++){
                System.out.println("Board Row position -> " + MIN_ROWS_COLS);
                System.out.println("Board Column position -> " + i);

                final String entryTitle = Console.readLine("Entry Title:");

                BoardEntry boardEntry = theController.createBoardEntry(
                        String.valueOf(i),
                        MIN_ROWS_COLS,
                        String.valueOf(i),
                        entryTitle,
                        boardNRows,
                        boardNCols
                );

                allBoardEntrys.add(boardEntry);
            }

            //Board Entrys for rows
            System.out.println("----ROWS ENTRYS----");
            System.out.println("----ROWS ENTRYS----");
            for(int i = 2; i <= Integer.parseInt(boardNRows); i++) {
                String entryTitle = null;
                for (int j = 1; j <= nCols; j++) {
                    System.out.println("Board Row position -> " + i);
                    System.out.println("Board Column position -> " + j);
                    entryTitle = Console.readLine("Entry Title:");
                }
                BoardEntry boardEntry = theController.createBoardEntry(
                        String.valueOf(i),
                        String.valueOf(i),
                        MIN_ROWS_COLS,
                        entryTitle,
                        boardNRows,
                        boardNCols
                );

                allBoardEntrys.add(boardEntry);
            }

            theController.createBoard(boardTitle, boardNRows, boardNCols, allBoardEntrys);

            System.out.println("Board Successfully created!");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (IntegrityViolationException e){
            System.out.println("A board with that name already exists");
        }

        return true;
    }

    /**
     * @return String to headline
     */
    @Override
    public String headline() {
        return "Create Board";
    }
}
