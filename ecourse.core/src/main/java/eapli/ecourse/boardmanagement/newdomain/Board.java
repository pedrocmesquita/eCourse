package eapli.ecourse.boardmanagement.newdomain;

import eapli.ecourse.AppSettings;
import eapli.ecourse.Application;
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
        this.logs.add(new Log("Initial board creation."));
    }
    public void addLog(Log log)
    {
        //logs.add(log);
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
    @Override
    public String toString() {
        return
                "BoardID: " + boardId +
                "Title: " + boardTitle +
                "Number of Rows: " + numberRows +
                "Number of Columns: " + numberColumns +
                "State: " + state +
                "Created on: " + createdOn +
                "Logs: "; //logs;
    }
}
