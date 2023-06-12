package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.coursemanagement.domain.TeachersInCourseKey;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamsInCourse;
import eapli.ecourse.exammanagement.domain.ExamsInCourseKey;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

public interface ExamsInCourseRepository extends DomainRepository<ExamsInCourseKey, ExamsInCourse>
{
    Iterable<Exam> findAllExamsInCourse(Name name);
}
