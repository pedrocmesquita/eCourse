package eapli.ecourse.app.manager.console.presentation.course;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.application.AcceptRejectEnrollmentController;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.validations.Preconditions;

import java.util.Iterator;
import java.util.List;

public class AcceptRejectEnrollmentUI extends AbstractUI {
   private final AcceptRejectEnrollmentController ctrl = new AcceptRejectEnrollmentController();
    @Override
    protected boolean doShow() {

        try{
            Iterable<EnrollmentRequest> pendingRequests = this.ctrl.getPendingRequests();
            EnrollmentRequest selected = selectRequest(pendingRequests, "Pending Requests:\n");
            boolean accept = asnwer();

            if(accept) {
                this.ctrl.accept(selected);
                System.out.println("Enrollment request accepted.");
            } else {
                this.ctrl.reject(selected);
                System.out.println("Enrollment request rejected.");
            }

        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());

            return false;
        }

        return true;
    }
    @Override
    public String headline() {
        return "Answer Request";
    }
    public EnrollmentRequest selectRequest(Iterable<EnrollmentRequest> requests, String message) {
        int i = 0;

        Iterator<EnrollmentRequest> iterator = requests.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("There are no pending requests.\n");
        }

        System.out.println(message);

        for (EnrollmentRequest request : requests) {
            System.out.println(i + 1 + " - " + request.toString() + "\n");
            i++;
        }

        int option;
        try {
            option = Integer.parseInt(Console.readLine("Select an option: "));
            System.out.println();
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid option, try again.");
        }

        int size = i;
        Preconditions.ensure(option > 0 && option <= size, "Invalid option, try again.");

        iterator = requests.iterator();
        for (i = 0; i < option - 1; i++) {
            iterator.next();
        }

        return iterator.next();
    }

    public boolean asnwer(){
        System.out.println("1 - Accept the Request");
        System.out.println("2 - Reject the Request");
        int option = Integer.parseInt(Console.readLine("\nSelect an option: "));
        System.out.println();

        Preconditions.ensure(option == 1 || option == 2, "Invalid option, try again.");

        return option == 1;
    }
}
