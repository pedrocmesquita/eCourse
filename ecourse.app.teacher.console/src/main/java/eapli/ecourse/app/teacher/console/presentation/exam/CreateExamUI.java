package eapli.ecourse.app.teacher.console.presentation.exam;


import eapli.ecourse.exammanagement.application.CreateExamController;
import eapli.ecourse.app.common.console.presentation.course.SelectCourseWidget;
import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.coursemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Arrays;
import java.util.Calendar;

public class CreateExamUI extends AbstractUI {

    private static final CreateExamController controller = new CreateExamController();
    private final SelectCourseWidget courseWidget = new SelectCourseWidget(controller.allCoursesTeacherIsAssigned(controller.getUserAcronym()));
    private static int SECTION_COUNTER = 1;
    private static int QUESTION_COUNTER;

    @Override
    protected boolean doShow() {
        System.out.println("Select a Course to create an exam");
        final Course selectedCourse = courseWidget.selectCourse();
        if (selectedCourse == null)
            return false;
        final String title = Console.readLine("Title");
        final String description = Console.readLine("Description");
        final Calendar openDate = Console.readCalendar("Open date (dd-MM-yyyy hh:mm:ss)", "dd-MM-yyyy hh:mm:ss");
        final Calendar closeDate = Console.readCalendar("Close date (dd-MM-yyyy hh:mm:ss)", "dd-MM-yyyy hh:mm:ss");

        final Iterable<SettingType> iterableSetting = Arrays.asList(SettingType.values());
        final SelectWidget<SettingType> selectorSetting = new SelectWidget<>("", iterableSetting);
        System.out.println("\nSelect feedback setting");
        selectorSetting.show();
        final SettingType feedbackSetting = selectorSetting.selectedElement();
        System.out.println("\nSelect grade setting");
        selectorSetting.show();
        final SettingType gradeSetting = selectorSetting.selectedElement();

        controller.addOther(title, description, feedbackSetting, gradeSetting, openDate, closeDate);

        boolean addSection;
        boolean addQuestion;

        //add section loop
        do {
            QUESTION_COUNTER = 1;
            newSection();
            String descriptionSection = Console.readLine("Description");
            //add question loop
            do {
                addQuestion();
                addQuestion = Console.readBoolean("\nAdd another question? (Y/N)");
            } while (addQuestion);
            controller.addSection(descriptionSection);
            addSection = Console.readBoolean("\nAdd another section? (Y/N)");
        } while (addSection);
        try {
            controller.createExam(selectedCourse);
            System.out.println("Exam created with success");
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to enter a exam with a title which already exists in the database.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private void newSection() {
        System.out.println("\nCreate Section " + SECTION_COUNTER);
        SECTION_COUNTER++;
        controller.newSection();
    }

    private void addQuestion() {
        System.out.println("\nCreate Question " + QUESTION_COUNTER);
        QUESTION_COUNTER++;
        String question = Console.readLine("Question");
        controller.addQuestion(question);
    }

    @Override
    public String headline() {
        return "Create Exam";
    }
}
