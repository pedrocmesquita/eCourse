package eapli.ecourse.boardmanagement.domain;


import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BoardPermission
        implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Version of board.
     */
    @Version
    private Long version;

    /**
     * Board Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPermissionId;

    /**
     * SystemUser that have permission in specific board.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private SystemUser SystemUser;

    /**
     * Board title.
     */
    private AccessLevel accessLevel;


    protected BoardPermission() {

    }

    public BoardPermission(final SystemUser userp,
                    final AccessLevel accessLevelp) {
        this.SystemUser = userp;
        this.accessLevel = accessLevelp;
    }

    /**
     * SystemUser that have a specific permission in board.
     * @return SystemUser
     */
    public SystemUser userWithPermission() {
        return SystemUser;
    }

    /**
     * Type of permission in board.
     * @return AccessLevel
     */
    public AccessLevel accessLevel() {
        return accessLevel;
    }

    /**
     * Check if some BoardPermission is the same object then other.
     * @param other
     * @return true/false
     */
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof BoardPermission)) {
            return false;
        }

        BoardPermission that = (BoardPermission) other;

        return Objects.equals(this.boardPermissionId, that.boardPermissionId)
                && Objects.equals(this.SystemUser, that.SystemUser)
                && this.accessLevel == that.accessLevel;
    }

    /**
     * @return boardPermissionId
     */
    public Long identity() {
        return boardPermissionId;
    }
}
