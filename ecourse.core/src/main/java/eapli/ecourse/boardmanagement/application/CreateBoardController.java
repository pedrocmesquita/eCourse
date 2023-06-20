package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
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
import java.util.Set;

public class CreateBoardController {
    private final AuthorizationService authz;

    private final BoardService boardSvc = new BoardService(PersistenceContext.repositories().boards());

    private final BoardCellRepository boardCellRepository = PersistenceContext.repositories().cells();

    private final BoardRepository boardRepository = PersistenceContext.repositories().boards();

    public CreateBoardController() {
        authz = AuthzRegistry.authorizationService();
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
    }

    public Board createBoard(final String boardTitlep,
                             final int boardNRowp,
                             final int boardNColp,
                             final Set<BoardCell> allBoardEntrys) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());
        return boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp, allBoardEntrys, authz.session().get().authenticatedUser());
    }

    public Board createBoard(final String boardTitlep,
                             final int boardNRowp,
                             final int boardNColp,
                             final Set<BoardCell> allBoardEntrys,
                             final SystemUser authUser) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authUser);
    }


    public BoardCell createBoardCell(BoardRow row, BoardCol column) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());
        return boardCellRepository.save(new BoardCell(row,column));
    }
    public String createBoardCell(BoardRow row, BoardCol column,Board board) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());
        board.addCell(new BoardCell(row,column));
        boardRepository.save(board);
        this.boardCellRepository.save(new BoardCell(row,column));
        return board.toString();
    }

    public BoardCell createBoardEntry(final BoardRow boardRowp,
                                       final BoardCol boardColp,
                                       final SystemUser authUser ) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return new BoardCell(boardRowp, boardColp);
    }
}
