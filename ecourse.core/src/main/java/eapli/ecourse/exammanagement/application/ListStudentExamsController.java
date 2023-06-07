package eapli.ecourse.exammanagement.application;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

@UseCaseController
public class ListStudentExamsController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientInExamRepository studentInExamRepository = PersistenceContext.repositories().studentsInExam();
    private final ClientUserRepository clientUserRepository = PersistenceContext.repositories().clientUsers();

    public Iterable<Exam> findAllStudentExams()
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.STUDENT);
        return studentInExamRepository.findAllExamsStudentIsAssign(getUserIdentity().get().identity());
    }

    public Optional<StudentUser> getUserIdentity()
    {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        
        for (StudentUser x : clientUserRepository.findAllActive())
        {
            if (user.equals(x.getSystemUser()))
            {
                return clientUserRepository.findByMecanographicNumber(x.identity());
            }
        }
        
        return Optional.empty();
    }
}
