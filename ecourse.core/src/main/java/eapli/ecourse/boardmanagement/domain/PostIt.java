package eapli.ecourse.boardmanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;

public class PostIt
{
    private Board board;        //board this postit belongs to
    private BoardEntry cell;    //cell this postit should be on
    private SystemUser owner;   //owner of the post
    private Content content;    //content of the post
    private Calendar date;      //date of post-it creation
    private PostIt backup;      //save previous iteration of post everytime it changes contents or position
    
    /**
     * Constructor without rollback post
     * @param cell
     * @param owner
     * @param content
     */
    public PostIt(Board board, BoardEntry cell, SystemUser owner, Content content)
    {
        cell.setPost(this);
        this.cell = cell;
        this.board = board;
        this.owner = owner;
        this.content = content;
        this.date = CurrentTimeCalendars.now();
        this.backup = null;
        board.addLog(new Log("Post-it added: " + this.toString()));
    }
    
    /**
     * Save current post to the backup
     */
    private void saveBackup()
    {
        this.backup = this;
    }
    
    ///**
    // * Change a post's cell
    // * @param cell new cell for the post
    // */
    //public void setCell(BoardEntry cell)
    //{
    //    saveBackup();
    //    this.cell = cell;
    //}
    
    /**
     * Change a post's content
     * @param content new content for the post
     */
    public void setContent(Content content)
    {
        saveBackup();
        this.content = content;
        board.addLog(new Log("Post-it content changed: " + content.toString()));
    }
    
    /**
     * Getter for the cell
     * to retrieve the cell from the backup post
     * @return cell of the post
     */
    private BoardEntry getCell()
    {
        return cell;
    }
    
    /**
     * Getter for the content
     * to retrieve the content from the backup post
     * @return content of the post
     */
    private Content getContent()
    {
        return content;
    }
    
    /**
     * Getter for the post maker
     * @return SystemUser of the post maker
     */
    public SystemUser getOwner()
    {
        return owner;
    }
    
    /**
     * Replaces current post with the one saved in backup
     */
    private void replacePostWithBackup()
    {
        PostIt temp = backup;
        this.cell.setPost(null);
        saveBackup();
        this.cell = temp.getCell();
        this.cell.setPost(this);
        this.content = temp.getContent();
        board.addLog(new Log("Post-it changes undone: " + this.toString()));
    }
    
    /**
     * Undo changes to post-it
     * @return true if successful, false otherwise
     */
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
        board.addLog(new Log("Post-it rolled back: " + this.toString()));
        return true;
    }
    
    /**
     * Attempt to relocate a post
     * @return true if successful, false otherwise
     */
    public boolean relocate(BoardEntry newCell)
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
    
    @Override
    public String toString()
    {
        return "PostIt{" +
                "board=" + board.boardTitle() +
                ", row=" + cell.boardRow() +
                ", column=" + cell.boardCol() +
                ", owner=" + owner +
                ", content=" + content +
                ", date=" + date +
        //        ", backup=" + backup +
                '}';
    }
}
