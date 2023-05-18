package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourseBuilder implements DomainFactory<Course> {

    private static final Logger LOGGER = LogManager.getLogger(CourseBuilder.class);
    private Name name;
    private Description description;
    private EnrollLimit enrollLimit = null;

    public CourseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public CourseBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    public CourseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public CourseBuilder withEnrollLimit(EnrollLimit enrollLimit) {
        this.enrollLimit = enrollLimit;
        return this;
    }

    public CourseBuilder withEnrollLimit(Integer min, Integer max) {
        this.enrollLimit = new EnrollLimit(min, max);
        return this;
    }

    public Course build() {
        Course course;
        course =  new Course(this.name, this.description, this.enrollLimit);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new course [{}] {}, {}, ({})", course, this.name, this.description,
                    (enrollLimit == null ? null : this.enrollLimit.getMinEnroll() + ", "+ this.enrollLimit.getMaxEnroll()));
        }
        return course;
    }
}
