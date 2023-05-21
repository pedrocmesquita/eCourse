package eapli.ecourse.usertypemanagement.studentusermanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUserBuilder;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class AddStudentUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final ClientUserRepository studentRepository = PersistenceContext.repositories().clientUsers();

    public StudentUser addStudent(String username, String password, String fName, String lName, String email,
                                  String mechanographicNumber , String taxPayerNumber, Calendar birthDate) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        final StudentUserBuilder builder = new StudentUserBuilder();
        final SystemUser newUser = createSystemUserForStudentUser(username, password, fName, lName, email);
        builder.withSystemUser(newUser).withMecanographicNumber(mechanographicNumber).withTaxPayerNumber(taxPayerNumber).withBirthDate(birthDate);
        StudentUser studentUser = builder.build();
        return studentRepository.save(studentUser);
    }

    private SystemUser createSystemUserForStudentUser(String username, String password, String fName, String lName,String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.STUDENT);
        return userService.registerNewUser(username, password, fName, lName, email, roles);
    }
}
