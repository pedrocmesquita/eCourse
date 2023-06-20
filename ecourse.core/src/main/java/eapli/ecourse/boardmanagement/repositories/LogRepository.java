package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.framework.domain.repositories.DomainRepository;

public interface LogRepository extends DomainRepository<Long, Log> {

    Iterable<Log> getLogByBoard(Board board);
}
