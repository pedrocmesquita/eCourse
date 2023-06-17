package eapli.ecourse.usertypemanagement.studentusermanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.StudentsInCourse;
import eapli.ecourse.coursemanagement.domain.StudentsInCourseKey;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.framework.domain.repositories.DomainRepository;

public interface StudentsInCourseRepository extends DomainRepository<StudentsInCourseKey, StudentsInCourse> {
    Iterable<Course> findAllCoursesStudentIsAssign(MecanographicNumber mecanographicNumber);

}
