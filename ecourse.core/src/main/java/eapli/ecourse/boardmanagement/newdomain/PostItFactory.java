package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class PostItFactory {
    /**
     * PostItFactory constructor.
     */
    public PostItFactory() {

    }

    public PostIt create(final Board board,
                         final BoardCell cell,
                         final SystemUser owner,
                         final Content content) {
        return new PostIt(board, cell,owner, content);
    }
}
