package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class PostIt implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postItId;
    @ManyToOne
    private Board board;
    @OneToOne
    private BoardCell cell;
    @ManyToOne
    private SystemUser owner;
    @Embedded
    private Content content;
    @Temporal(TemporalType.DATE)
    private Calendar date;
    @OneToOne
    private PostIt backup;

public PostIt(Board Board, BoardCell cell, SystemUser owner, Content content){
    cell.setPost(this);
    this.cell = cell;
    this.board = board;
    this.owner = owner;
    this.content = content;
    this.date = CurrentTimeCalendars.now();
    this.backup = null;
    board.addLog(new Log("Post-it added: " + this.toString()));

}

    public PostIt() {
        //ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostIt cell = (PostIt) o;
        return Objects.equals(postItId, cell.postItId) && Objects.equals(board, cell.board) && Objects.equals(date, cell.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postItId, board, cell, owner, content, date);
    }

    @Override
    public String toString() {
        return
                "PostIt ID: " + postItId +
                "Board: " + board +
                "Cell: " + cell +
                "Owner: " + owner +
                "Content: " + content +
                "Date: " + date +
                "Backup" + backup;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof PostIt)) {
            return false;
        }

        final PostIt that = (PostIt) other;
        if (this == that) {
            return true;
        }

        return content.equals(that.content)
                && date.equals(that.date);
    }

    @Override
    public Long identity() {
        return this.postItId;
    }
}
