package eapli.ecourse.boardmanagement;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

public class ShareBoardTest {
    private BoardTitle boardTitle;
    private int numberRows;
    private int numberColumns;
    private SystemUser boardOwner;

    /*@BeforeEach
    void setup() {
        boardTitle = new BoardTitle("Test Board");
        numberRows = 5;
        numberColumns = 5;
        boardOwner = getNewDummyUser();
    }

    @Test
    public void createBoard() {

        BoardFactory boardFactory = new BoardFactory();
        Set<BoardCell> allBoardEntrys = new HashSet<>();
        Board board = boardFactory.create(String.valueOf(boardTitle), numberRows, numberColumns, allBoardEntrys, boardOwner);

        Assertions.assertEquals(boardTitle, board.identity());
        Assertions.assertEquals(numberRows, board.getNumberRows());
        Assertions.assertEquals(numberColumns, board.getNumberColumns());
        Assertions.assertEquals(BoardState.OPEN, board.state());
        Assertions.assertEquals(boardOwner, board.boardOwner());
    }

     */

    @Test
    public void createBoardPermissionTest() {
        AccessLevel accessLevel = AccessLevel.READ;

        BoardPermission boardPermission = new BoardPermission(getNewDummyUser(), accessLevel);

        Assertions.assertEquals(getNewDummyUser(), boardPermission.userWithPermission());
        Assertions.assertEquals(accessLevel, boardPermission.accessLevel());
    }

    @Test
    public void assertEqualsBoardPermissions() {
        AccessLevel accessLevel = AccessLevel.READ;

        BoardPermission boardPermission1 = new BoardPermission(getNewDummyUser(), accessLevel);
        BoardPermission boardPermission2 = new BoardPermission(getOtherDUmmyUser(), accessLevel);

        Assertions.assertEquals(boardPermission1, boardPermission2);
        Assertions.assertEquals(boardPermission1.hashCode(), boardPermission2.hashCode());
    }


    public static SystemUser dummyUser(final String username, final Role... roles) {
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.ADMIN);
    }
    private SystemUser getOtherDUmmyUser() {
        return dummyUser("dummy2", BaseRoles.ADMIN);
    }
}
