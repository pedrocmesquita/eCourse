package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.Grade;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface GradeRepository extends DomainRepository<Long, Grade> {
    //Iterable<Grade> findGradeByStudent(SystemUser student);
   // Iterable<Grade> findGradeByCourse(Course course);
}
