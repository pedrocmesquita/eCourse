package eapli.ecourse.boardmanagement;

import eapli.ecourse.boardmanagement.domain.*;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private final String boardTitle = "Test Board";
    private final String boardNRow = "3";
    private final String boardNCol = "4";


    /*@Test
    public void testCreateValidBoard() {
        List<BoardEntry> allBoardEntrys = new ArrayList<>();
        SystemUser boardOwner = getNewDummyUser();

        BoardFactory factory = new BoardFactory();
        Board board = factory.create(boardTitle, boardNRow, boardNCol, allBoardEntrys, boardOwner);

        BoardPermissionFactory factoryBP = new BoardPermissionFactory();
        board.addPermission(factoryBP.create(boardOwner, AccessLevelType.WRITE));

        assertNotNull(board);
        assertEquals(null, board.identity());
        assertEquals(boardTitle, board.boardTitle().value());
        assertEquals(Integer.parseInt(boardNRow), board.boardNRow().value());
        assertEquals(Integer.parseInt(boardNCol), board.boardNCol().value());
        assertEquals(boardOwner, board.boardOwner());
    }

     */
    @Test
    public void testCreateBoardWithInvalidNumberOfRows() {
        List<BoardEntry> allBoardEntrys = new ArrayList<>();
        SystemUser boardOwner = getNewDummyUser();

        BoardFactory factory = new BoardFactory();

        assertThrows(IllegalArgumentException.class,
                () -> factory.create(boardTitle,
                        "-1",
                        boardNCol,
                        allBoardEntrys,
                        boardOwner));
    }

    @Test
    public void testCreateBoardWithInvalidNumberOfColumns() {
        List<BoardEntry> allBoardEntrys = new ArrayList<>();
        SystemUser boardOwner = getNewDummyUser();

        BoardFactory factory = new BoardFactory();

        assertThrows(IllegalArgumentException.class,
                () -> factory.create(boardTitle,
                        boardNRow,
                        "-1",
                        allBoardEntrys,
                        boardOwner));
    }




    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.ADMIN);
    }


}
