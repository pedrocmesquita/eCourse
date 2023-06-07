package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExamBuilder implements DomainFactory<Exam> {

    private static final Logger LOGGER = LogManager.getLogger(ExamBuilder.class);
    private Designation title;
    private Description description;
    private Setting setting;
    private Date date;
    private List<Section> sections;
    private List<Question> questions;

    public ExamBuilder withTitle(String title) {
        return withTitle(Designation.valueOf(title));
    }

    public ExamBuilder withTitle(Designation title) {
        this.title = title;
        return this;
    }

    public ExamBuilder withDescription(String description) {
        return withDescription(Description.valueOf(description));
    }

    public ExamBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    public ExamBuilder withSetting(SettingType feedbackSetting, SettingType gradeSetting) {
        this.setting = new Setting(feedbackSetting, gradeSetting);
        return this;
    }

    public ExamBuilder withDate(Calendar startDate, Calendar closeDate) {
        this.date = new Date(startDate, closeDate);
        return this;
    }

    public Section addSection(String description) {
        Section section = new Section(Description.valueOf(description), questions);
        sections.add(section);
        return section;
    }

    public Question addQuestion(String question) {
        Question questionObj = new Question(question);
        questions.add(questionObj);
        return questionObj;
    }

    public void newSection() {
        sections = new ArrayList<>();
        questions = new ArrayList<>();
    }

    @Override
    public Exam build() {
        Exam exam;
        exam = new Exam(title, description, setting, date, sections);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new exam");
        }
        return exam;
    }
}
