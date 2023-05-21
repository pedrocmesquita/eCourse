package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public Iterable<TeacherUser> joinAllTeachersWithSystemUser() {
        String jpql = "SELECT t FROM TeacherUser t JOIN t.systemUser s";
        TypedQuery<TeacherUser> query = entityManager().createQuery(jpql, TeacherUser.class);
        return query.getResultList();
    }

    @Override
    public TeacherUser getTeacherUserFromSystemUser(SystemUser systemUser) {
        String jpql = "SELECT t FROM TeacherUser t JOIN t.systemUser su WHERE su.username = :username";
        TypedQuery<TeacherUser> query = entityManager().createQuery(jpql, TeacherUser.class);
        query.setParameter("username", systemUser.username());
        return query.getSingleResult();
    }

    /*
    String systemUserUsername = "exampleUsername";

    String jpqlQuery = "SELECT t FROM TeacherUser t JOIN t.systemUser su WHERE su.username = :username";
    TypedQuery<TeacherUser> query = entityManager.createQuery(jpqlQuery, TeacherUser.class);
    query.setParameter("username", systemUserUsername);

    TeacherUser teacherUser = query.getSingleResult();
     */
}
