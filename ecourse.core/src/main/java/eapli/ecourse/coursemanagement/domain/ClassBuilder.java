package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Calendar;

public class ClassBuilder implements DomainFactory<SchClass> {

    private static final Logger LOGGER = LogManager.getLogger(ClassBuilder.class);
    private String course;
    private int instructor;
    private Calendar startTime;
    private Calendar endTime;

    public ClassBuilder withCourse(String course) {
        this.course = course;
        return this;
    }

    public ClassBuilder withInstructor(int instructor) {
        this.instructor = instructor;
        return this;
    }

    public ClassBuilder withDates(Calendar startTime, Calendar endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        return this;
    }

    public SchClass build() {
        SchClass classs;
        classs = new SchClass(this.course, this.instructor, this.startTime, this.endTime);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Scheduling new class [{}] {}, {}, ({})", course, this.instructor, this.startTime,
                    this.endTime);
        }

        return classs;
    }
}
