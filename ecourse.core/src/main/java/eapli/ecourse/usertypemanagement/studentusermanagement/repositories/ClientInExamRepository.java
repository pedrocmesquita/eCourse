package eapli.ecourse.usertypemanagement.studentusermanagement.repositories;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.StudentsInExam;
import eapli.ecourse.exammanagement.domain.StudentsInExamKey;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface ClientInExamRepository extends DomainRepository<StudentsInExamKey, StudentsInExam>
{
    Iterable<Exam> findAllExamsStudentIsAssign(MecanographicNumber number);
}
