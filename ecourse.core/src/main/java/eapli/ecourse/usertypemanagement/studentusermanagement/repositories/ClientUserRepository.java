/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.ecourse.usertypemanagement.studentusermanagement.repositories;

import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface ClientUserRepository
        extends DomainRepository<MecanographicNumber, StudentUser>
{
    
    /**
     * returns the client user (utente) whose username is given
     *
     * @param name the username to search for
     * @return
     */
    Optional<StudentUser> findByUsername(Username name);
    
    /**
     * returns the student user with the given mecanographic number
     *
     * @param number
     * @return
     */
    default Optional<StudentUser> findByMecanographicNumber(final MecanographicNumber number)
    {
        return ofIdentity(number);
    }
    
    public Iterable<StudentUser> findAllActive();
    
    
    /**
     * Finds student, given a systemuser
     *
     * @return StudentUser of system user
     */
    //StudentUser getClientUserFromSystemUser(SystemUser systemUser);
    StudentUser getStudentUserFromSystemUser(SystemUser systemUser);
}
