package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class ExamBuilder implements DomainFactory<Exam>
{
    
    private static final Logger LOGGER = LogManager.getLogger(ExamBuilder.class);
    private Name title;
    private Description description;
    private Date dateOpen;
    private Date dateClose;
    
    public ExamBuilder withTitle(String title)
    {
        this.title = new Name(title);
        return this;
    }
    
    public ExamBuilder withDescription(Description description)
    {
        this.description = description;
        return this;
    }
    
    public ExamBuilder withDescription(String description)
    {
        this.description = new Description(description);
        return this;
    }
    
    public ExamBuilder withDates(Date dateOpen, Date dateClose)
    {
        this.dateOpen = dateOpen;
        this.dateClose = dateClose;
        return this;
    }
    
    public Exam build()
    {
        Exam exam;
        exam = new Exam(this.title, this.description, this.dateOpen, this.dateClose);
        
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("Creating new exam [{}] {}, {}, ({}, {})", exam, this.title, this.description,
                    this.dateOpen, this.dateClose);
        }
        
        return exam;
    }
}
