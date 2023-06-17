package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BoardCell implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cellId;
    @Embedded
    private BoardRow row;
    @Embedded
    private BoardCol column;
    @OneToOne
    private PostIt postIt;
    public BoardCell(BoardRow row, BoardCol column) {
        this.row = row;
        this.column = column;
    }

    public BoardCell() {
        //ORM
    }

    public void setPost(PostIt post)
    {
        this.postIt = post;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardCell cell = (BoardCell) o;
        return Objects.equals(cellId, cell.cellId) && Objects.equals(row, cell.row) && Objects.equals(row, cell.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellId, row, column);
    }

    @Override
    public String toString() {
        return "CellID: " + cellId +
                "Row: " + row +
                "Column:" + column;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof BoardCell)) {
            return false;
        }

        final BoardCell that = (BoardCell) other;
        if (this == that) {
            return true;
        }

        return cellId.equals(that.cellId) && row.equals(that.row)
                && column.equals(that.column);
    }

    @Override
    public Long identity() {
        return this.cellId;
    }
}
