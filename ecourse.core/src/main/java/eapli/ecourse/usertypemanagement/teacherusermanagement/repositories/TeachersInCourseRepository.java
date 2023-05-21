package eapli.ecourse.usertypemanagement.teacherusermanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.coursemanagement.domain.TeachersInCourseKey;
import eapli.framework.domain.repositories.DomainRepository;

public interface TeachersInCourseRepository extends DomainRepository<TeachersInCourseKey, TeachersInCourse> {
    Iterable<Course> findAllCoursesTeacherIsAssign(Acronym acronym);
}
