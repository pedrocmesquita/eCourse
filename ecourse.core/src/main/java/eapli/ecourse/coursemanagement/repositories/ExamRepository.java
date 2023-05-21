package eapli.ecourse.coursemanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Exam;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;

public interface ExamRepository extends DomainRepository<Name, Exam>
{
    
    /**
     * Finds all exams with state passed as parameter
     *
     * @param state
     * @return
     */
    Iterable<Exam> findAllExamsWithState(State state);
    
    /**
     * Finds all exams that don't have state passed as parameter
     *
     * @param state
     * @return
     */
    Iterable<Exam> findAllExamsWithoutState(State state);
}
