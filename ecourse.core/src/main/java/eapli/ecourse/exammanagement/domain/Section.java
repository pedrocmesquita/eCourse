package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Description;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section implements ValueObject {

    @Id
    @GeneratedValue
    private Long pk;

    private Description description;

    @OneToMany
    private List<Question> questions;

    protected Section() {
        //ORM only
    }

    protected Section(Description description, List<Question> questions) {
        setDescription(description);
        setQuestions(questions);
    }

    protected void setDescription(Description description) {
        if(StringPredicates.isNullOrEmpty(description.toString()))
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;
    }

    protected void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
