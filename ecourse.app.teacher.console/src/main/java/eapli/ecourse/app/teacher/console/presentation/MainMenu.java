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
package eapli.ecourse.app.teacher.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.app.common.console.presentation.boards.*;
import eapli.ecourse.app.teacher.console.presentation.course.ListCourseExamsUI;
import eapli.ecourse.app.teacher.console.presentation.exam.CreateExamUI;
import eapli.ecourse.app.teacher.console.presentation.classes.ScheduleClassUI;
import eapli.ecourse.app.teacher.console.presentation.course.ListAssignCoursesUI;
import eapli.ecourse.app.teacher.console.presentation.exam.ListExamGradesUI;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
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

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int COURSES_OPTION = 2;
    private static final int CLASSES_OPTION = 3;
    private static final int EXAMS_OPTION = 4;
    private static final int BOARD_OPTION = 5;


    //BOARD
    private static final int SET_USER_CREATE_BOARD_OPTION = 1;
    private static final int SHARE_BOARD_OPTION = 2;
    private static final int VIEW_BOARD_HISTORY_OPTION = 3;

    private static final int CREATE_POSTIT_OPTION = 4;
    private static final int EDIT_POSTIT_OPTION = 5;
    private static final int UNDO_POSTIT_OPTION = 6;


    //COURSE
    private static final int LIST_ASSIGN_COURSES_OPTION = 1;
    private static final int SCHEDULE_CLASS_OPTION = 1;

    //EXAM
    private static final int CREATE_EXAM_OPTION = 1;
    private static final int LIST_EXAMS = 2;
    private static final int LIST_EXAM_GRADES = 3;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final Menu menu;
    private final MenuRenderer renderer;

    public MainMenu() {
        menu = buildMainMenu();
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "eCourse [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("eCourse [ ==Anonymous== ]");
    }

    private Menu buildBoardMenu() {
        final Menu menu = new Menu("Boards");

        menu.addItem(SET_USER_CREATE_BOARD_OPTION, "Create Board",
                new CreateBoardUI()::show);

        menu.addItem(SHARE_BOARD_OPTION, "Share Board",
                new ShareBoardUI()::show);
        menu.addItem(VIEW_BOARD_HISTORY_OPTION, "View Board History", new ViewBoardHistoryUI()::show);
        menu.addItem(CREATE_POSTIT_OPTION, "Create PostIt", new CreatePostItsUI()::show);
        menu.addItem(EDIT_POSTIT_OPTION, "Edit PostIt", new ChangePostItUI()::show);
        menu.addItem(UNDO_POSTIT_OPTION, "Undo PostIt", new UndoPostItUI()::show);

        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }
    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu(BaseRoles.TEACHER);
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.MANAGER, BaseRoles.ADMIN)) {
            final Menu coursesMenu = buildTeachersMenu();
            mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);
            final Menu classesMenu = buildClassesMenu();
            mainMenu.addSubMenu(CLASSES_OPTION, classesMenu);
            final Menu examsMenu = buildExamsMenu();
            mainMenu.addSubMenu(EXAMS_OPTION, examsMenu);
            final Menu boardMenu= buildBoardMenu();
            mainMenu.addSubMenu(BOARD_OPTION, boardMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildTeachersMenu() {
        final Menu coursesMenu = new Menu("Courses  >");

        coursesMenu.addItem(LIST_ASSIGN_COURSES_OPTION, "Assigned Courses", new ListAssignCoursesUI()::show);
        coursesMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return coursesMenu;
    }
    private Menu buildClassesMenu() {
        final Menu classesMenu = new Menu("Classes  >");

        classesMenu.addItem(SCHEDULE_CLASS_OPTION, "Schedule a Class", new ScheduleClassUI()::show);
        classesMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return classesMenu;
    }
    private Menu buildExamsMenu() {
        final Menu examsMenu = new Menu("Exams  >");

        examsMenu.addItem(CREATE_EXAM_OPTION, "Create a Exam", new CreateExamUI()::show);
        examsMenu.addItem(LIST_EXAMS, "List all exams in a course", new ListCourseExamsUI()::show);
        examsMenu.addItem(LIST_EXAM_GRADES, "List all exams grades", new ListExamGradesUI()::show);
        examsMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return examsMenu;
    }
}
