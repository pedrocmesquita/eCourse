package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question implements ValueObject {

    @Id
    @GeneratedValue
    private Long pk;

    private String question;

    protected Question() {
        //ORM only
    }

    protected Question(String question) {
        setQuestion(question);
    }

    protected void setQuestion(String question) {
        if(StringPredicates.isNullOrEmpty(question))
            throw new IllegalArgumentException("Question cannot be null or empty");
        this.question = question;
    }
}
