package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class BoardPermission implements AggregateRoot<Long> {
    /**
     * Board Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPermissionId;

    /**
     * SystemUser that have permission in specific board.
     */
    @OneToOne(fetch = FetchType.LAZY)
    private SystemUser SystemUser;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;


    public BoardPermission(SystemUser userp,
                            AccessLevel accessLevelp) {
        this.SystemUser = userp;
        this.accessLevel = accessLevelp;
    }

    protected BoardPermission() {
        //ORM
    }

    public AccessLevel accessLevel() {
        return accessLevel;
    }
    public SystemUser userWithPermission() {
        return SystemUser;
    }


    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }
    @Override
    public Long identity() {
        return this.boardPermissionId;
    }
    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof BoardPermission)) {
            return false;
        }

        final BoardPermission that = (BoardPermission) other;
        if (this == that) {
            return true;
        }

        return boardPermissionId.equals(that.boardPermissionId) && SystemUser.equals(that.SystemUser) && accessLevel.equals(that.accessLevel);
    }
    @Override
    public String toString() {
        return
                "BoardPermissionID: " + boardPermissionId +
                        "UserId: " + SystemUser +
                        "Permission: " + accessLevel;
    }
}
