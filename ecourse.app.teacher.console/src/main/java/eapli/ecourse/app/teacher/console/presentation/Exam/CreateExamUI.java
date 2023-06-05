package eapli.ecourse.app.teacher.console.presentation.Exam;


import eapli.ecourse.exammanagement.application.CreateExamController;
import eapli.ecourse.exammanagement.domain.*;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;

public class CreateExamUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateExamUI.class);
    private static CreateExamController controller = new CreateExamController();
    private static int SECTION_COUNTER = 1;
    private static int QUESTION_COUNTER;

    @Override
    protected boolean doShow() {
        final String title = Console.readLine("Title");
        final String description = Console.readLine("Description");
        final Calendar openDate = Console.readCalendar("Open date (dd-M-yyyy hh:mm:ss)", "dd-M-yyyy hh:mm:ss");
        final Calendar closeDate = Console.readCalendar("Close date (dd-M-yyyy hh:mm:ss)", "dd-M-yyyy hh:mm:ss");

        Iterable<SettingType> iterableSetting = Arrays.asList(SettingType.values());
        final SelectWidget<SettingType> selectorSetting= new SelectWidget<SettingType>("", iterableSetting);
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
            addSection();
            //add question loop
            do {
                addQuestion();
                addQuestion = Console.readBoolean("\nAdd another question? (Y/N)");
            } while (addQuestion);
            addSection = Console.readBoolean("\nAdd another section? (Y/N)");
        } while (addSection);

        Exam exam = controller.createExam();
        System.out.println(exam.identity());
        return true;
    }

    private void addSection() {
        System.out.println("\nCreate Section " + SECTION_COUNTER);
        SECTION_COUNTER++;
        controller.newSection();
        String description = Console.readLine("Description");
        controller.addSection(description);
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
