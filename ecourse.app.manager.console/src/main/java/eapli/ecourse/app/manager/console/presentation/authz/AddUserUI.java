/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.ecourse.app.manager.console.presentation.authz;

import eapli.ecourse.app.common.console.presentation.myuser.UserDataWidget;
import eapli.ecourse.usermanagement.application.AddUserController;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.studentusermanagement.application.AddStudentUserController;
import eapli.ecourse.usertypemanagement.teacherusermanagement.application.AddTeacherUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * UI for adding a user to the application.
 * <p>
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    private final AddTeacherUserController teacherUserController = new AddTeacherUserController();
    private final AddStudentUserController studentUserController = new AddStudentUserController();

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);
        try {
            if (roleTypes.contains(BaseRoles.TEACHER)) {
                String acronym = Console.readLine("Acronym");
                teacherUserController.addTeacher(userData.username(), userData.password(), userData.firstName(),
                        userData.lastName(), userData.email(), acronym, userData.taxPayerNumber(), userData.birthDate());

            } else if (roleTypes.contains(BaseRoles.STUDENT)) {
                String mechaNumber = Console.readLine("mechanographic number");
                studentUserController.addStudent(userData.username(), userData.password(), userData.firstName(),
                        userData.lastName(), userData.email(), mechaNumber, userData.taxPayerNumber(), userData.birthDate());

            } else {
                this.theController.addUser(userData.username(), userData.password(), userData.firstName(),
                    userData.lastName(), userData.email(), roleTypes);
            }
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        }

//        try {
//            Class<?> clazz = Class.forName("eapli.ecourse.usertypemanagement.teacherusermanagement.application.AddTeacherUserController");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }


//        try {
//            this.theController.addUser(userData.username(), userData.password(), userData.firstName(),
//                    userData.lastName(), userData.email(), roleTypes);
//        } catch (final IntegrityViolationException | ConcurrencyException e) {
//            System.out.println("That username is already in use.");
//        }
        return false;
    }


    private boolean showRoles(final Set<Role> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
