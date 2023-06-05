package eapli.ecourse.exammanagement.application;

import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

@UseCaseController
public class CreateExamController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExamBuilder builder = new ExamBuilder();
    private final ExamRepository repository = PersistenceContext.repositories().exams();

    public Exam createExam() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER);
        Exam exam = builder.build();
        return this.repository.save(exam);
    }

    public void addOther(String title, String description, SettingType feedbackSetting, SettingType gradeSetting,
                    Calendar openDate, Calendar closeDate) {
        builder.withTitle(title);
        builder.withDescription(description);
        builder.withSetting(feedbackSetting, gradeSetting);
        builder.withDate(openDate, closeDate);
    }

    public Question addQuestion(String question) {
        return builder.addQuestion(question);
    }

    public void newSection() {
        builder.newSection();
    }

    public Section addSection(String description) {
        return builder.addSection(description);
    }
}
