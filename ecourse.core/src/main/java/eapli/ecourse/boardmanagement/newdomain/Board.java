package eapli.ecourse.boardmanagement.newdomain;

import eapli.ecourse.AppSettings;
import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.*;

@Entity
public class Board implements AggregateRoot<BoardTitle> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    @Embedded
    private BoardTitle boardTitle;
    private int numberRows;
    private int numberColumns;
    @Enumerated(EnumType.STRING)
    private BoardState state;
    @ManyToOne
    private SystemUser boardOwner;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Log> logs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BoardCell> cells;

    @OneToMany
    private Set<BoardPermission> boardPermissions;



    protected Board() {
        //ORM
    }

    protected Board(BoardTitle boardTitle, int valueRows, int valueColumns, SystemUser owner) {
        this.boardTitle = boardTitle;
        this.numberRows = valueRows;
        this.numberColumns = valueColumns;
        this.state = BoardState.OPEN;
        this.createdOn = Calendar.getInstance();
        this.boardOwner = owner;
        this.logs = new HashSet<>();
        this.cells= new HashSet<>();
        this.boardPermissions = new HashSet<>();
        this.logs.add(new Log("Initial board creation."));
    }
    public void addLog(Log log) {
        logs.add(log);
    }
    public Set<Log> getLogs()
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
    public BoardCell getCellByRowColumn(int row, int column)
    {
        BoardCell[] cells1 = cells.toArray(new BoardCell[cells.size()]);
        return cells1[(row * numberColumns )- (numberColumns-column)-1];//-1 because array starts at 0
    }

    public SystemUser boardOwner() {
        return boardOwner;
    }
    public int getNumberColumns() {
        return numberColumns;
    }
    public int getNumberRows() {
        return numberRows;
    }
    public Set<BoardPermission> getBoardPermissions() {
        return boardPermissions;
    }

    public BoardState state() {
        return state;
    }
    public boolean userHasPermission(SystemUser SystemUser, AccessLevel accessLevel){
        for (BoardPermission boardPermission : this.boardPermissions) {
            if(boardPermission.userWithPermission().sameAs(SystemUser) && boardPermission.accessLevel().equals(accessLevel)){
                return true;
            }
        }

        return false;
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
    public BoardTitle identity() {
        return this.boardTitle;
    }
    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Board)) {
            return false;
        }

        final Board that = (Board) other;
        if (this == that) {
            return true;
        }

        return boardTitle.equals(that.boardTitle) && state.equals(that.state);
    }
    public String toStringStatic() {

        return
                        "<h2>Title:</h2> " + boardTitle +
                        "<h2>Number of Rows:</h2> " + numberRows +
                        "<h2>Number of Columns:</h2> " + numberColumns ;

    }
    @Override
    public String toString() {
        return
                "BoardID: " + boardId +
                " Title: " + boardTitle +
                " Number of Rows: " + numberRows +
                " Number of Columns: " + numberColumns +
                " State: " + state +
                " Created on: " + createdOn.getTime() ;
                //"Logs: "; //logs;
    }

    public Set<BoardCell> getBoardCells() {
        return cells;
    }

    public void addCells(Set<BoardCell> allBoardEntrys) {
        this.cells.addAll(allBoardEntrys);
    }
    public void addCell(BoardCell boardCell) {
        this.cells.add(boardCell);
    }
}
