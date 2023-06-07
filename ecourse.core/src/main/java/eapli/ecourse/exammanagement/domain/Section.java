package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Section implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    private Description description;

    /**
     * Cascade = CascadeType.ALL as questions are part of the same aggregate
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    protected Section() {
        //ORM only
    }

    protected Section(Description description, List<Question> questions) {
        setDescription(description);
        setQuestions(questions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(description, section.description) && Objects.equals(questions, section.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, questions);
    }

    protected void setDescription(Description description) {
        if(StringPredicates.isNullOrEmpty(description.toString()))
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;
    }

    protected void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Description description() {
        return description;
    }
}
