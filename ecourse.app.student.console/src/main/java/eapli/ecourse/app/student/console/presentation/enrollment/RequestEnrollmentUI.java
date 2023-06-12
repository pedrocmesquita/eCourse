package eapli.ecourse.app.student.console.presentation.enrollment;

import eapli.ecourse.enrollmentmanagement.application.RequestEnrollmentController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RequestEnrollmentUI extends AbstractUI {
    private final RequestEnrollmentController ctrl =
            new RequestEnrollmentController();
    @Override
    protected boolean doShow() {
        try {
            String coursename = Console.readLine("Course name of the course you wish to enroll: ");
            System.out.println();

            ctrl.attemptEnroll(coursename);

            System.out.println("Request for course " +coursename+ " created successfully. Please wait for your response.");
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Request Enrollment in a Course";
    }
}
