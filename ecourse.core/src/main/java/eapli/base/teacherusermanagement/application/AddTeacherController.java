package eapli.base.teacherusermanagement.application;

import eapli.base.teacherusermanagement.domain.Acronym;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.Set;

public class AddTeacherController {

    private final UserManagementService userSvc = AuthzRegistry.userService();

    public void addTeacherUser(final String username, final String password, final String firstName,
                              final String lastName, final String email, final Acronym acronym, final Set<Role> roles) {

	}
}
