package eapli.ecourse.boardmanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class BoardPermissionFactory {

    /**
     * BoardPermissionFactory constructor.
     */
    public BoardPermissionFactory() {

    }

    /**
     * Create a BoardPermission.
     * @param userp
     * @param accessLevelp
     * @return BoardPermission
     */
    public BoardPermission create(final SystemUser userp,
                                  final AccessLevel accessLevelp) {
        return new BoardPermission(
                userp,
                accessLevelp
        );
    }
}

