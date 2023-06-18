package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BoardTitle implements ValueObject, Comparable<BoardTitle> {
    @Column(unique = true)
    private String name;

    protected BoardTitle() {
        //ORM only
    }

    public BoardTitle(String name) {
        setBoardTitle(name);
    }

    public static BoardTitle of(final String valuep) { return new BoardTitle(valuep); }

    private void setBoardTitle(String name) {
        if (StringPredicates.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("BoardTitle cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardTitle name1 = (BoardTitle) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(BoardTitle o) {
        return name.compareTo(o.name);
    }


}