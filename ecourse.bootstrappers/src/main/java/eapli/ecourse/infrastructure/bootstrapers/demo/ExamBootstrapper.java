package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.exammanagement.application.CreateExamController;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.SettingType;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class ExamBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamBootstrapper.class);
    private static final CreateExamController controller = new CreateExamController();


    @Override
    public boolean execute() {
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JUNE, 25);
        createExam("JAVA", "exam java", SettingType.NONE, SettingType.NONE, cal, cal, "OOP?", "OOP section");
        return true;
    }

    private Exam createExam(String title, String description, SettingType feedback, SettingType grade,
                            Calendar openDate, Calendar closeDate, String question, String descriptionSection) {
        Exam exam = null;
        try {
            controller.addOther(title, description, feedback, grade, openDate, closeDate);
            controller.newSection();
            controller.addQuestion(question);
            controller.addSection(descriptionSection);
            exam = controller.createExam();
        }catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated course
        }
        return exam;
    }

}
