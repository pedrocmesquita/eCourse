package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.coursemanagement.domain.TeachersInCourseKey;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaTeachersInCourseRepository
        extends JpaAutoTxRepository<TeachersInCourse, TeachersInCourseKey, TeachersInCourseKey>
        implements TeachersInCourseRepository {

    public JpaTeachersInCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaTeachersInCourseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }


    @Override
    public Iterable<Course> findAllCoursesTeacherIsAssign(Acronym acronym) {
        String jpql = "SELECT c FROM Course c " +
                "JOIN TeachersInCourse tic ON c.courseId = tic.course.id " +
                "WHERE tic.teacher.id = :teacherId";

        TypedQuery<Course> query = entityManager().createQuery(jpql, Course.class);
        query.setParameter("teacherId", acronym);

        return query.getResultList();
        /*
        String jpql = "SELECT c FROM COURSE c " +
              "JOIN TEACHERSINCOURSE tic ON tic.COURSE_ID = c.COURSEID " +
              "WHERE tic.TEACHER_ID = :teacherId";
         */

    }
}
