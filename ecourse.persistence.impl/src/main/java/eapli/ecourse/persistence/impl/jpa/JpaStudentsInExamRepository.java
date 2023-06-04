package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Exam;
import eapli.ecourse.coursemanagement.domain.StudentsInExam;
import eapli.ecourse.coursemanagement.domain.StudentsInExamKey;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaStudentsInExamRepository
        extends JpaAutoTxRepository<StudentsInExam, StudentsInExamKey, StudentsInExamKey>
        implements ClientInExamRepository
{
    
    public JpaStudentsInExamRepository(final TransactionalContext autoTx)
    {
        super(autoTx, "id");
    }
    
    public JpaStudentsInExamRepository(final String puname)
    {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
    
    @Override
    public Iterable<Exam> findAllExamsStudentIsAssign(MecanographicNumber number)
    {
        String jpql = "SELECT c FROM Exam c " +
                "JOIN StudentsInExam tic ON c.examId = tic.exam.id " +
                "WHERE tic.student.id = :studentId";
        
        TypedQuery<Exam> query = entityManager().createQuery(jpql, Exam.class);
        query.setParameter("studentId", number);
        
        return query.getResultList();
    }
}
