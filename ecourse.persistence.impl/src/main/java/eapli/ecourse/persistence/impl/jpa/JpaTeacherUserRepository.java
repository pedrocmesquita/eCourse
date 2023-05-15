package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.teacherusermanagement.domain.Acronym;
import eapli.ecourse.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaTeacherUserRepository
        extends JpaAutoTxRepository<TeacherUser, Acronym, Acronym>
        implements TeacherUserRepository {

    public JpaTeacherUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "acronym");
    }

    public JpaTeacherUserRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "acronym");
    }
}
