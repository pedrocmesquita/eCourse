package eapli.ecourse.usertypemanagement.teacherusermanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUserBuilder;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@UseCaseController
public class AddTeacherUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final TeacherUserRepository teacherRepository = PersistenceContext.repositories().teacherUsers();

    public TeacherUser addTeacher(String username, String password, String fName, String lName, String email,
                                  String acronym, String taxPayerNumber, Calendar birthDate) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        final TeacherUserBuilder builder = new TeacherUserBuilder();
        final SystemUser newUser = createSystemUserForTeacherUser(username, password, fName, lName, email);
        builder.withSystemUser(newUser).withAcronym(acronym).withTaxPayerNumber(taxPayerNumber).withBirthDate(birthDate);
        TeacherUser teacher = builder.build();
        return teacherRepository.save(teacher);
    }

    private SystemUser createSystemUserForTeacherUser(String username, String password, String fName, String lName,String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.TEACHER);
        return userService.registerNewUser(username, password, fName, lName, email, roles);
    }
}
