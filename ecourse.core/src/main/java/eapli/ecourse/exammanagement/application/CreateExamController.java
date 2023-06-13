package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.Optional;

@UseCaseController
public class CreateExamController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExamBuilder builder = new ExamBuilder();
    private final ExamRepository examRepository = PersistenceContext.repositories().exams();
    private final TeachersInCourseRepository teachersInCourseRepository = PersistenceContext.repositories().teachersInCourse();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();


    public Exam createExam(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.TEACHER);
        Exam exam = builder.build();
        course.addExam(exam);
        courseRepository.save(course);
        return this.examRepository.save(exam);
    }

    //todo refactor to avoid code duplication
    public Iterable<Course> allCoursesTeacherIsAssigned(Acronym acronym) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return teachersInCourseRepository.findAllCoursesTeacherIsAssign(acronym);
    }

    public Acronym getUserAcronym(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        if(!user.roleTypes().contains(BaseRoles.TEACHER))
            throw new IllegalArgumentException("User must be a teacher");
        return teacherUserRepository.getTeacherUserFromSystemUser(user).acronym();
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
