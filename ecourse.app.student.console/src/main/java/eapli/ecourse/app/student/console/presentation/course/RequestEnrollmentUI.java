package eapli.ecourse.app.student.console.presentation.course;

import eapli.ecourse.coursemanagement.application.RequestEnrollmentController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;

public class RequestEnrollmentUI extends AbstractUI
{
    private final RequestEnrollmentController ctrl = new RequestEnrollmentController();
    
    @Override
    protected boolean doShow()
    {
        try
        {
            ctrl.attemptEnroll(Console.readLine("Insert the course name."));
        } catch (IllegalArgumentException ex)
        {
            System.out.println("Course not found.");
        }
            return false;
    }
    
    @Override
    public String headline()
    {
        return "Request Enrollment in Course";
    }
}
