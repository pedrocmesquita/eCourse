package eapli.ecourse.usertypemanagement.teacherusermanagement.repositories;

import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public interface TeacherUserRepository extends DomainRepository<Acronym, TeacherUser> {

    /**
     * Finds all Teachers, joined with system user
     *
     * @return
     */
    Iterable<TeacherUser> joinAllTeachersWithSystemUser();

    /**
     * Finds Teacher, given a systemuser
     *
     * @return
     */
    TeacherUser getTeacherUserFromSystemUser(SystemUser systemUser);
}
