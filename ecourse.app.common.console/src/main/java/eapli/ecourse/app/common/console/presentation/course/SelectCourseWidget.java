package eapli.ecourse.app.common.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursesPrinter;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.presentation.console.SelectWidget;

/**
 * Course widget to show and select a course
 */
public class SelectCourseWidget {

    private final Iterable<Course> courses;

    public SelectCourseWidget(Iterable<Course> courses) {
        this.courses = courses;
    }

    /**
     * shows courses passed as parameter in constructor and ask to select one
     *
     * @return selected course
     */
    public Course selectCourse() {
        if (!courses.iterator().hasNext()) {
            System.out.println("There are no courses available");
            return null;
        }
        final SelectWidget<Course> selector = new SelectWidget<>(CoursesPrinter.HEADER, courses, new CoursesPrinter());
        selector.show();
        return selector.selectedElement();
    }
}
