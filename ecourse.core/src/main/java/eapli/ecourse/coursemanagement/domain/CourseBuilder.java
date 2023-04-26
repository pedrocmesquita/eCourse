package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class CourseBuilder implements DomainFactory<Course> {

    private Name name;
    private Description description;
    private EnrollLimit enrollLimit;

    public CourseBuilder withName(Name name) {
        this.name = name;
        return this;
    }

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

    @Override
    public Course build() {
        return new Course(this.name, this.description, this.enrollLimit);
    }
}
