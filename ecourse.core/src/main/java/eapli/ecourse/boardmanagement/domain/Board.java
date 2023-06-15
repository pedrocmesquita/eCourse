package eapli.ecourse.boardmanagement.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
public class Board implements AggregateRoot<BoardTitle>{
    /**
     * Board ID, ORM primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    /**
     * Version of board.
     */
    @Version
    private Long version;
    /**
     * Board title.
     */
    @Embedded
    private BoardTitle boardTitle;
    /**
     * Board number of columns.
     */
    @Embedded
    private BoardNCols boardNCols;
    /**
     * Board number of rows.
     */
    @Embedded
    private BoardNRows boardNRows;
    /**
     * Active or Archive board.
     */
    private boolean boardState;
    /**
     * Date when SystemUser created board.
     */

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    /**
     * Board Owner.
     */

    @ManyToOne
    private SystemUser boardOwner;

    /**
     * List of board permissions.
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<BoardPermission> boardPermissions;

    /**
     * List of board entry.
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BoardEntry> boardEntrys;

    @OneToMany
    private List<Log> logs; //history of update logs
    
    protected Board()
    {
        logs.add(new Log("Initial board creation with empty constructor."));
    }

    Board(final BoardTitle boardTitlep,
          final BoardNRows boardNRowp,
          final BoardNCols boardNColp,
          final List<BoardEntry> allBoardEntrys,
          final SystemUser boardOwnerp) {
        this.boardTitle = boardTitlep;
        this.boardNRows = boardNRowp;
        this.boardNCols = boardNColp;
        this.boardEntrys = allBoardEntrys;
        this.boardOwner = boardOwnerp;
        this.createdOn = CurrentTimeCalendars.now();
        this.boardState = true;
        this.boardPermissions = new ArrayList<>();
        this.logs = new ArrayList<>();
        logs.add(new Log("Initial board creation."));
    }

    /**
     * Get board title.
     * @return BoardTitle
     */
    public BoardTitle boardTitle() {
        return boardTitle;
    }

    /**
     * Get board number of columns.
     * @return BoardNCol
     */
    public BoardNCols boardNCol() {
        return boardNCols;
    }

    /**
     * Get board number of rows.
     * @return BoardNCol
     */
    public BoardNRows boardNRow() {
        return boardNRows;
    }

    /**
     * Get board owner of rows.
     * @return SystemUser
     */
    public SystemUser boardOwner() {
        return boardOwner;
    }
    
    /**
     * Getter for logs
     * @return List of logs
     */
    public List<Log> getLogs()
    {
        return logs;
    }
    
    /**
     * Add Permissions to board.
     * @param boardPermissionp board permission.
     */
    public void addPermission(final BoardPermission boardPermissionp)
    {
        this.boardPermissions.add(boardPermissionp);
        logs.add(new Log("Permissions added: " + boardPermissions.toString()));
    }

    /**
     * Check if SystemUser has specific permission in board
     * @param SystemUser SystemUser that should have permission
     * @param accessLevel typer of permission
     * @return true/false
     */
    public boolean userHasPermission(SystemUser SystemUser, AccessLevel accessLevel){
        for (BoardPermission boardPermission : this.boardPermissions) {
            if(boardPermission.userWithPermission().sameAs(SystemUser)
                    && boardPermission.accessLevel().equals(accessLevel)){
                return true;
            }
        }

        return false;
    }

    /**
     * Find row of entry Title
     * @return String
     */
    public String findRowByEntryTitle(String rowTitle){
        EntryTitle findEntryTitle = EntryTitle.of(rowTitle);

        for (BoardEntry boardEntry : this.boardEntrys) {
            if(boardEntry.entryTitle().equals(findEntryTitle)){
                return boardEntry.boardRow().toString();
            }
        }

        return null;
    }

    /**
     * Find column of entry Title
     * @return String
     */
    public String findColumnByEntryTitle(String colTitle){
        EntryTitle findEntryTitle = EntryTitle.of(colTitle);

        for (BoardEntry boardEntry : this.boardEntrys) {
            if(boardEntry.entryTitle().equals(findEntryTitle)){
                return boardEntry.boardCol().toString();
            }
        }

        return null;
    }
    
    /**
     * Get cell from row and column, from the cell list
     * @param row
     * @param column
     * @return
     */
    public BoardEntry getCellByRowColumn(int row, int column)
    {
        return boardEntrys.get((row * boardNCols.value()) * column);
    }
    
    /**
     * Register update in board logs
     * @param log information to register
     */
    public void addLog(Log log)
    {
        logs.add(log);
    }

    /**
     * Check if some Board is the same object then other.
     * @param other
     * @return true/false
     */
    @Override
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Board)) {
            return false;
        }

        Board that = (Board) other;

        return Objects.equals(boardId, that.boardId)
                && Objects.equals(boardTitle, that.boardTitle)
                && Objects.equals(boardNRows, that.boardNRows)
                && Objects.equals(boardNCols, that.boardNCols)
                && Objects.equals(boardState, that.boardState)
                && Objects.equals(boardOwner, that.boardOwner);
    }

    /**
     * Get boardId.
     * @return boardId
     */
    @Override
    public BoardTitle identity() {
        return this.boardTitle;
    }
    // TODO appropriate print with board entries, in a table format
    public String toString() {
        return "Board{" +
                "\n boardId=" + boardId +
                "\n, boardTitle=" + boardTitle.value() +
                "\n, boardNRows=" + boardNRows.value() +
                "\n, boardNCols=" + boardNCols.value() +
                "\n, boardState=" + boardState +
                "\n, createdOn=" + createdOn.getTime() +
                "\n, boardOwner=" + boardOwner.username() +
                '}';
    }
}



