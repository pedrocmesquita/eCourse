package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;

public class ListCourseController {

    private final ListCourseService service = new ListCourseService();

    public Iterable<Course> allCourses() {
        return service.allCourses();
    }

}
