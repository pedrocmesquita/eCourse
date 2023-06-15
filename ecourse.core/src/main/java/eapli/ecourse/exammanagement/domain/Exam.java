package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exam implements AggregateRoot<Designation> {

    /**
     * Business ID and pk
     */
    @EmbeddedId
    private Designation title;

    @Embedded
    private Description description;

    /**
     * When feedback and grading is publish
     */
    @Embedded
    private Setting setting;

    /**
     * Open and close date
     */
    @Embedded
    private Date date;

    /**
     * List of sections with questions
     * Cascade = CascadeType.ALL as sections are part of the same aggregate
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Section> sections;

    protected Exam() {
        //ORM only
    }

    /**
     * Constructor
     *
     * @param title         mandatory
     * @param description   optional
     * @param setting       mandatory
     * @param date          mandatory
     * @param sections      mandatory
     */
    public Exam(Designation title, Description description, Setting setting, Date date, List<Section> sections) {
        Preconditions.noneNull(title, setting, date, sections);
        if(sections.size() < 1)
            throw new IllegalArgumentException("There must be latest one section");
        this.title = title;
        this.description = description;
        this.setting = setting;
        this.date = date;
        this.sections = sections;
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
    public boolean sameAs(Object other) {
        if (!(other instanceof Exam)) {
            return false;
        }

        final Exam that = (Exam) other;
        if (this == that) {
            return true;
        }

        return title.equals(that.title) && description.equals(that.description)
                && setting.equals(that.setting) && sections.equals(that.sections)
                && date.equals(that.date);
    }

    @Override
    public Designation identity() {
        return this.title;
    }
    
    public Designation getTitle()
    {
        return identity();
    }
    
    public Description getDescription()
    {
        return description;
    }
    
    public Date getDate()
    {
        return date;
    }

    @Override
    public String toString() {
        return
                "\nTitle: " + title +
                "\nDescription: " + description +
                date.toString()
                ;
    }
}
