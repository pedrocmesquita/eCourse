package eapli.ecourse.app.teacher.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursesPrinter;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.application.ListTeacherCoursesController;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListAssignCoursesUI extends AbstractListUI<Course> {

    private final ListTeacherCoursesController controller = new ListTeacherCoursesController();

    @Override
    protected Iterable<Course> elements() {
        return controller.findAllCoursesTeacherIsAssign();
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
        return "Assigned to Courses";
    }
}
