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
package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.exammanagement.repositories.ExamsInCourseRepository;
import eapli.ecourse.persistence.impl.jpa.JpaBoardRepository;
import eapli.ecourse.persistence.impl.jpa.JpaClassRepository;
import eapli.ecourse.persistence.impl.jpa.JpaEnrollmentRequestRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.ecourse.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.ecourse.persistence.impl.jpa.JpaCourseRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

/**
 * @author nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    //todo in memory
    @Override
    public JpaCourseRepository courses(final TransactionalContext autoTx) {
        return null;
    }

    @Override
    public JpaCourseRepository courses() {
        return null;
    }

    @Override
    public JpaEnrollmentRequestRepository requests(final TransactionalContext autoTx) {
        return null;
    }
    @Override
    public JpaEnrollmentRequestRepository requests() {
        return null;
    }
//    @Override
//    public ExamRepository exams() {
//        return null;
//    }

    @Override
    public JpaClassRepository classes(final TransactionalContext autoTx) {
        return null;
    }
    @Override
    public JpaClassRepository classes() {
        return null;
    }

    @Override
    public TeacherUserRepository teacherUsers(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public TeacherUserRepository teacherUsers() {
        return null;
    }

    @Override
    public TeachersInCourseRepository teachersInCourse(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public TeachersInCourseRepository teachersInCourse() {
        return null;
    }

    //todo
    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return null;
    }

    //todo
    @Override
    public ExamRepository exams() {
        return null;
    }

    @Override
    public ClientInExamRepository studentsInExam()
    {
        return null;
    }
    
    @Override
    public ExamsInCourseRepository examsInCourse()
    {
        return null;
    }
    @Override
    public JpaBoardRepository boards(final TransactionalContext autoTx) {
        return null;
    }
    
    @Override
    public JpaBoardRepository boards() {
        return null;
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
