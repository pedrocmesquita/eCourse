package eapli.ecourse.app.manager.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursesPrinter;
import eapli.ecourse.coursemanagement.application.ListCourseController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
public class ListCourseUI extends AbstractListUI<Course> {

    private final ListCourseController controller = new ListCourseController();

    @Override
    protected Iterable<Course> elements() {
        return controller.allCourses();
    }

    @Override
    protected Visitor<Course> elementPrinter() {
        return new CoursesPrinter();
    }

    @Override
    protected String elementName() {
        return "Courses";
    }

    @Override
    protected String listHeader() {
        return CoursesPrinter.HEADER;
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "List Courses";
    }
}
