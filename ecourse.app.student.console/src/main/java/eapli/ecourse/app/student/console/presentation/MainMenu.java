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
package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.app.student.console.presentation.board.CreateBoardUI;
import eapli.ecourse.app.student.console.presentation.enrollment.RequestEnrollmentUI;
import eapli.ecourse.app.student.console.presentation.exam.ListExamsUI;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends StudentUserBaseUI
{
    
    private static final String SEPARATOR_LABEL = "--------------";
    
    private static final String RETURN = "Return ";
    
    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";
    
    private static final int EXIT_OPTION = 0;
    
    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    //private static final int BOOKINGS_OPTION = 2;
    //private static final int ACCOUNT_OPTION = 3;
    //private static final int SETTINGS_OPTION = 4;
    private static final int COURSES_OPTION = 2;
    private static final int EXAMS_OPTION = 3;
    private static final int BOARD_OPTION = 4;


    
    // BOOKINGS MENU
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int LIST_MY_BOOKINGS_OPTION = 3;
    
    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;
    
    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;
    private static final int SET_USER_CREATE_BOARD_OPTION = 2;
    
    //COURSE

    private static final int ENROLL_OPTION = 1;

    //EXAM
    private static final int LIST_EXAMS_OPTION = 1;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    
    private final Menu menu;
    private final MenuRenderer renderer;
    
    public MainMenu()
    {
        menu = buildMainMenu();
        renderer = getRenderer(menu);
    }
    
    private MenuRenderer getRenderer(final Menu menu)
    {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal())
        {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        else
        {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }
    
    @Override
    public boolean show()
    {
        drawFormTitle();
        return doShow();
    }
    
    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow()
    {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer =
                new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }
    
    private Menu buildMainMenu()
    {
        final Menu mainMenu = new Menu();
        final Menu myUserMenu = new MyUserMenu(BaseRoles.STUDENT);
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        
        if (! Application.settings().isMenuLayoutHorizontal())
        {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.MANAGER))
        {
            final Menu coursesMenu = buildCoursesMenu();
            mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);
            final Menu examsMenu = buildExamsMenu();
            mainMenu.addSubMenu(EXAMS_OPTION, examsMenu);
            final Menu boardMenu= buildBoardMenu();
            mainMenu.addSubMenu(BOARD_OPTION, boardMenu);
        }
        
        if (! Application.settings().isMenuLayoutHorizontal())
        {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        
        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));
        
        return mainMenu;
    }
    private Menu buildCoursesMenu()
    {
        final Menu coursesMenu = new Menu("Courses  >");

        //coursesMenu.addItem(LIST_COURSES_OPTION, "View List of Courses", new ListCoursesUI()::show);
        coursesMenu.addItem(ENROLL_OPTION, "Request Enrollment in a Course", new RequestEnrollmentUI()::show);
        coursesMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return coursesMenu;
    }
    private Menu buildBoardMenu() {
        final Menu menu = new Menu("Boards  >");

        menu.addItem(SET_USER_CREATE_BOARD_OPTION, "Create Board",
                new CreateBoardUI()::show);

        menu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return menu;
    }
    
    private Menu buildExamsMenu()
    {
        final Menu examsMenu = new Menu("Exams  >");
        
        examsMenu.addItem(LIST_EXAMS_OPTION, "View List of Exams", new ListExamsUI()::show);
        examsMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);
        
        return examsMenu;
    }
}
