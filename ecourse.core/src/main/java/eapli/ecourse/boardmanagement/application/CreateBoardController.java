package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
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

    private final BoardRepository repository = PersistenceContext.repositories().boards();

    private final BoardService boardSvc = new BoardService(PersistenceContext.repositories().boards());

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

        Board board = boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp, allBoardEntrys, authz.session().get().authenticatedUser());
        repository.save(board);
        return board;
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
        return new BoardCell(row,column);
    }

    public BoardCell createBoardEntry(final BoardRow boardRowp,
                                       final BoardCol boardColp,
                                       final SystemUser authUser ) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return new BoardCell(boardRowp, boardColp);
    }
}
