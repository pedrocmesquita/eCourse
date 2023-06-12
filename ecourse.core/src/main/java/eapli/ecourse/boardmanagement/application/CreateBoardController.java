package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardEntry;
import eapli.ecourse.boardmanagement.domain.BoardEntryFactory;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.List;
import java.util.Optional;

public class CreateBoardController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz;


    //temporario
    private final BoardRepository repository = new BoardRepository();

    /**
     * Create a board service with repository injection.
     */
    private final BoardService boardSvc = new BoardService(
            PersistenceContext.repositories().boards());

    public CreateBoardController() {
        authz = AuthzRegistry.authorizationService();
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
    }


    /**
     * Create shared board.
     * @param boardTitlep Board Title
     * @param boardNRowp Board number of rows
     * @param boardNColp Board number of columns
     * @param allBoardEntrys Board entrys
     * @return Board
     */
    public Board createBoard(final String boardTitlep,
                             final String boardNRowp,
                             final String boardNColp,
                             final List<BoardEntry> allBoardEntrys) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());

        Board board = boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authz.session().get().authenticatedUser());
        repository.add(board);
        return board;
    }

    /**
     * Create shared board.
     * @param boardTitlep Board Title
     * @param boardNRowp Board number of rows
     * @param boardNColp Board number of columns
     * @param allBoardEntrys Board entrys
     * @param authUser authenticated user
     * @return Board
     */
    public Board createBoard(final String boardTitlep,
                             final String boardNRowp,
                             final String boardNColp,
                             final List<BoardEntry> allBoardEntrys,
                             final SystemUser authUser) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authUser);
    }

    /**
     * Create board entry.
     * @param entryNumberp Entry number
     * @param boardRowp Row position
     * @param boardColp Column position
     * @param entryTitlep Entry Title
     * @param boardNRowp Board number of rows
     * @param boardNColps Board number of columns
     * @return BoardEntry
     */
    public BoardEntry createBoardEntry(final String entryNumberp,
                                       final String boardRowp,
                                       final String boardColp,
                                       final String entryTitlep,
                                       final String boardNRowp,
                                       final String boardNColps
                                       ) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());

        return new BoardEntryFactory().create(
                entryNumberp,
                boardRowp,
                boardColp,
                entryTitlep,
                boardNRowp,
                boardNColps
        );
    }

    /**
     * Create board entry.
     * @param entryNumberp Entry number
     * @param boardRowp Row position
     * @param boardColp Column position
     * @param entryTitlep Entry Title
     * @param boardNRowp Board number of rows
     * @param boardNColps Board number of columns
     * @return BoardEntry
     */
    public BoardEntry createBoardEntry(final String entryNumberp,
                                       final String boardRowp,
                                       final String boardColp,
                                       final String entryTitlep,
                                       final String boardNRowp,
                                       final String boardNColps,
                                       final SystemUser authUser ) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return new BoardEntryFactory().create(
                entryNumberp,
                boardRowp,
                boardColp,
                entryTitlep,
                boardNRowp,
                boardNColps
        );
    }
}
