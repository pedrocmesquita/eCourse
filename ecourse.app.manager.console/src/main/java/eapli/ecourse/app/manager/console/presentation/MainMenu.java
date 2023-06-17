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
package eapli.ecourse.app.manager.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.manager.console.presentation.authz.AddUserUI;
import eapli.ecourse.app.manager.console.presentation.authz.DeactivateUserAction;
import eapli.ecourse.app.manager.console.presentation.authz.ListUsersAction;
import eapli.ecourse.app.common.console.presentation.boards.CreateBoardUI;
import eapli.ecourse.app.manager.console.presentation.course.*;
import eapli.ecourse.app.manager.console.presentation.studentuser.AcceptRefuseSignupRequestAction;
import eapli.ecourse.app.manager.console.presentation.teacheruser.AssignTeacherToCourseUI;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    //COURSE
    private static final int CREATE_COURSE = 1;
    private static final int LIST_COURSE = 2;
    private static final int OPEN_CLOSE_COURSE = 3;
    private static final int OPEN_CLOSE_ENROLL = 4;
    private static final int ASSIGN_TEACHER = 5;
    private static final int ANSWER_ENROLLMENT = 6;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;
    private static final int SET_USER_CREATE_BOARD_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int COURSES_OPTION = 3;
    private static final int SETTINGS_OPTION = 5;
    private static final int BOARD_OPTION = 4;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "eCourse [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("eCourse [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.MANAGER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu coursesMenu = buildCoursesMenu();
            mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);
            final Menu boardMenu = buildBoardMenu();
            mainMenu.addSubMenu(BOARD_OPTION, boardMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
    private Menu buildBoardMenu() {
        final Menu menu = new Menu("Boards >");

        menu.addItem(SET_USER_CREATE_BOARD_OPTION, "Create Board",
                new CreateBoardUI()::show);

        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }
    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");

        menu.addItem(CREATE_COURSE, "Create Course", new CreateCourseUI()::show);
        menu.addItem(LIST_COURSE, "List Courses", new ListCourseUI()::show);
        menu.addItem(OPEN_CLOSE_COURSE, "Open/Close Courses", new OpenCloseCourseUI()::show);
        menu.addItem(OPEN_CLOSE_ENROLL, "Open/Close Enrollments", new OpenCloseEnrollmentUI()::show);
        menu.addItem(ASSIGN_TEACHER, "Assign Teacher", new AssignTeacherToCourseUI()::show);
        //menu.addItem(ANSWER_ENROLLMENT,"Accept or Reject Enrollments", new AcceptRejectEnrollmentUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
}
