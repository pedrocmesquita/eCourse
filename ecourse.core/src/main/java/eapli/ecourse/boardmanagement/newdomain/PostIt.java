package eapli.ecourse.boardmanagement.newdomain;

import eapli.ecourse.boardmanagement.repositories.LogRepository;
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
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
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

public PostIt(Board board, BoardCell cell, SystemUser owner, Content content){
    cell.setPost(this);
    this.cell = cell;
    this.board = board;
    this.owner = owner;
    this.content = content;
    this.date = CurrentTimeCalendars.now();
    this.backup = null;


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
                " Board: " + board +
                " Cell: " + cell.identity() +
                " Owner: " + owner +
                " Content: " + content +
                " Date: " + date.getTime();
                //"Backup" + backup;
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

    public SystemUser getOwner() {
    return owner;
    }

    /**
     * Attempt to relocate a post
     * @return true if successful, false otherwise
     */
    public boolean relocate(BoardCell newCell)
    {
        //attempted to relocate to same cell, which is weird
        if (newCell.getPost() == this)
        {
            return false;
        }

        //attempted to relocate to space filled by another cell
        if (newCell.getPost() != null)
        {
            return false;
        }

        cell.setPost(null);
        cell = newCell;
        newCell.setPost(this);
        board.addLog(new Log("Post-it relocated: " + this.toString()));
        return true;
    }

    public boolean setContent(Content newContent) {
    this.content = newContent;
    this.backup = this;
    return true;
    }

    public void setBackup(PostIt post) {
        this.backup = post;
    }

    public Content getContent() {
        return content;
    }
    private void replacePostWithBackup()
    {
        PostIt temp = backup;
        this.cell.setPost(null);
        this.backup= this;
        this.cell = temp.getCell();
        this.cell.setPost(this);
        this.content = temp.getContent();
        }
    public boolean rollbackPost()
    {
        PostIt post = backup.getCell().getPost();

        if (post != null)   //if the backup cell has a post
        {
            if (post != this)   //and its not the current one
            {
                return false;   //dont rollback
            }
        }
        replacePostWithBackup();    //rollback the post
        //board.addLog(new Log("Post-it rolled back: " + this));
        return true;
    }
    private void saveBackup() {
        this.backup = this;
    }

    private BoardCell getCell() {
        return cell;
    }

    public Board getBoard() {
        return board;
    }

    public BoardCol getColumn() {
        return cell.getColumn();
    }

    public BoardRow getRow() {
        return cell.getRow();
    }
}
