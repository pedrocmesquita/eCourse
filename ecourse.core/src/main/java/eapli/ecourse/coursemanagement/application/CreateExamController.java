package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Exam;
import eapli.ecourse.coursemanagement.domain.ExamBuilder;
import eapli.ecourse.coursemanagement.repositories.ExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Date;

@UseCaseController
public class CreateExamController
{
    
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExamRepository examRepository = PersistenceContext.repositories().exams();
    
    public Exam createExam(String title, String description, Date dateOpen, Date dateClose)
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER);
        
        final ExamBuilder examBuilder = new ExamBuilder();
        examBuilder.withTitle(title).withDescription(description).withDates(dateOpen,dateClose);
        
        Exam exam = examBuilder.build();
        return this.examRepository.save(exam);
    }
}
