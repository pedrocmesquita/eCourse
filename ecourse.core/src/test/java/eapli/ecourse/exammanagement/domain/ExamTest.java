package eapli.ecourse.exammanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExamTest {

    private static final Designation TITLE = Designation.valueOf("title");
    private static final Description DESCRIPTION = Description.valueOf("description");
    private static final Setting SETTING = new Setting(SettingType.NONE, SettingType.NONE);
    private static final Calendar tomorrow = Calendar.getInstance();

    static {
        tomorrow.add(Calendar.DATE, 1);
    }

    private static final Calendar dayAfterTomorrow = Calendar.getInstance();

    static {
        dayAfterTomorrow.add(Calendar.DATE, 2);
    }

    private static final Date DATE = new Date(tomorrow, dayAfterTomorrow);
    private static final Question QUESTION = new Question("question");
    private static final List<Question> QUESTIONS = new ArrayList<>();

    static {
        QUESTIONS.add(QUESTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSettingNotNull() {
        final Setting setting = new Setting(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDateNotNull() {
        final Date date = new Date(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOpenDateAfterCloseDate() {
        final Date date = new Date(dayAfterTomorrow, tomorrow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDatesAreInFuture() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        Calendar dayBeforeYesterday = Calendar.getInstance();
        dayBeforeYesterday.add(Calendar.DATE, -2);
        final Date date = new Date(dayBeforeYesterday, yesterday);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionNotNull() {
        final Exam exam = new Exam(TITLE, DESCRIPTION, SETTING, DATE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMinimumOneSection() {
        final Exam exam = new Exam(TITLE, DESCRIPTION, SETTING, DATE, new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionDescriptionNotNull() {
        final Section section = new Section(null, QUESTIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionQuestionNotNull() {
        final Section section = new Section(DESCRIPTION, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureSectionMinimumOneQuestion() {
        final Section section = new Section(DESCRIPTION, new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuestionNotNull() {
        final Question question = new Question(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuestionNotEmpty() {
        final Question question = new Question("");
    }
}