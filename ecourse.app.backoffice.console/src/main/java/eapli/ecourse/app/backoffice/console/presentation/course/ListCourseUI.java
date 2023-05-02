package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.coursemanagement.application.ListCourseController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListCourseUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCourseUI.class);

    private final ListCourseController controller = new ListCourseController();

    @Override
    protected boolean doShow() {
        //todo filter by state
        System.out.println("Select which courses to show");
        System.out.println("1. All");
        System.out.println("2. Closed");
        System.out.println("2. Not Closed");
        System.out.println("3. Open");
        System.out.println("4. In Enrollment");
        System.out.println("5. In Progress");
        System.out.println("0. Exit");
        int option = Console.readOption(1, 6, 0);
        for (Course c : controller.allCourses()) {
            System.out.println(c + "\n");
        }
        return false;
    }

    @Override
    public String headline() {
        return "List Course";
    }
}
