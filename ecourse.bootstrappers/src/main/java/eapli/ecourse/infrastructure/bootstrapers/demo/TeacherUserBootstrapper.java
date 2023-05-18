package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.infrastructure.bootstrapers.TestDataConstants;
import eapli.ecourse.usertypemanagement.teacherusermanagement.application.AddTeacherUserController;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class TeacherUserBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentUserBootstrapper.class);
    private static final AddTeacherUserController controller = new AddTeacherUserController();

    @Override
    public boolean execute() {
        Calendar cal = Calendar.getInstance();
        cal.set(1975, Calendar.JUNE, 24);
        registerTeachers("teacher1", TestDataConstants.PASSWORD1, "Carl", "George", "carl@email.com", "CG", "000000001", cal);
        cal.set(1960, Calendar.FEBRUARY, 25);
        registerTeachers("teacher2", TestDataConstants.PASSWORD1, "Matt", "Leonard", "matt@email.com", "ML", "000000002", cal);
        cal.set(1990, Calendar.DECEMBER, 18);
        registerTeachers("teacher3", TestDataConstants.PASSWORD1, "Georgie", "Aguilar", "georgie@email.com", "GA", "000000003", cal);
        cal.set(2000, Calendar.SEPTEMBER, 12);
        registerTeachers("teacher4", TestDataConstants.PASSWORD1, "Abigail", "Lucas", "abigail@email.com", "AL", "000000004", cal);
        cal.set(1995, Calendar.OCTOBER, 6);
        registerTeachers("teacher5", TestDataConstants.PASSWORD1, "Rosemary", "Bax", "rosemary@email.com", "RB", "000000005", cal);
        return true;
    }

    private TeacherUser registerTeachers(final String username, final String password, final String firstName,
                                         final String lastName, final String email, final String acronym,
                                         final String taxPayerNumber, final Calendar birthDate) {
        TeacherUser teacherUser = null;
        try {
            teacherUser = controller.addTeacher(username, password, firstName, lastName, email, acronym,
                    taxPayerNumber, birthDate);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
            LOGGER.trace("Assuming existing record", e);
        }
        return teacherUser;
    }
}
