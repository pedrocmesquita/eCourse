package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Question implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    private String question;

    protected Question() {
        //ORM only
    }

    protected Question(String question) {
        setQuestion(question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }

    protected void setQuestion(String question) {
        if(StringPredicates.isNullOrEmpty(question))
            throw new IllegalArgumentException("Question cannot be null or empty");
        this.question = question;
    }
}
