package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardCol;
import eapli.ecourse.boardmanagement.newdomain.BoardRow;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateBoardUI extends AbstractUI {
    private final CreateBoardController theController = new CreateBoardController();


    private static final String MIN_ROWS_COLS = "1";

    @Override
    protected boolean doShow() {
        final String boardTitle = Console.readLine("Board Title:");
        final int boardNCols = Console.readInteger("Board Number of Columns:");
        final int boardNRows = Console.readInteger("Board Number of Rows:");

        Set<BoardCell> allBoardEntrys = new HashSet<>();


        try{
            //Board Entrys for columns
            System.out.println("----COLUMNS ENTRYS----");
            int nCols = Integer.parseInt(boardNCols);
            for(int i = 1; i <= nCols ; i++){
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

            Board board = theController.createBoard(boardTitle, boardNRows, boardNCols, allBoardEntrys);

            System.out.println("Board successfully created!");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (IntegrityViolationException e){
            System.out.println("A board with that name already exists");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create Board";
    }
}
