package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.application.OpenCloseCourseController;
import eapli.ecourse.coursemanagement.application.OpenCloseEnrollmentController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseBootstrapper.class);
    private static final CreateCourseController createCourseController = new CreateCourseController();
    private static final OpenCloseCourseController openCloseCourseController = new OpenCloseCourseController();
    private static final OpenCloseEnrollmentController openCloseEnrollmentController = new OpenCloseEnrollmentController();

    @Override
    public boolean execute() {
        //state = closed
        createCourse("Java-1-23", "Java introduction course year 2023", null, null);
        createCourse("Math-1-23", "Algebra introduction course year 2023", null, null);
        createCourse("Physics-3-23", "Physics advanced course year 2023", 2, 4);
        //state=open
        createAndOpenCourse("Java-2-23", "Java intermediate course year 2023", null, null);
        //state=enroll
        createAndOpenEnrollCourse("Java-3-23", "Java advanced course year 2023", 2, 4);
        return true;
    }


    private Course createCourse(final String name, final String description,
                                final Integer minEnroll, final Integer maxEnroll) {
        Course course = null;
        try {
            course = createCourseController.createCourse(name, description, minEnroll, maxEnroll);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated course
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", e);
        }
        return course;
    }

    private Course createAndOpenCourse(final String name, final String description,
                                       final Integer minEnroll, final Integer maxEnroll) {
        Course course = null;
        try {
            course = createCourseController.createCourse(name, description, minEnroll, maxEnroll);
            openCloseCourseController.toggleOpenCloseCourse(course);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated course
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", e);
        }
        return course;
    }

    private Course createAndOpenEnrollCourse(final String name, final String description,
                                       final Integer minEnroll, final Integer maxEnroll) {
        Course course = null;
        try {
            course = createCourseController.createCourse(name, description, minEnroll, maxEnroll);
            openCloseCourseController.toggleOpenCloseCourse(course);
            openCloseEnrollmentController.toggleOpenCloseEnroll(course);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated course
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", name);
            LOGGER.trace("Assuming existing record", e);
        }
        return course;
    }
}
