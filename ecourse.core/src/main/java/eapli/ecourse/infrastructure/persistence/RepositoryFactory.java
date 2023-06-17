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
package eapli.ecourse.infrastructure.persistence;

import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.exammanagement.repositories.ExamsInCourseRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * The interface Repository factory.
 *
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();


    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    CourseRepository courses(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CourseRepository courses();
    EnrollmentRequestRepository requests(TransactionalContext autoTx);
    EnrollmentRequestRepository requests();
    ClassRepository classes(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClassRepository classes();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    TeacherUserRepository teacherUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    TeacherUserRepository teacherUsers();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    TeachersInCourseRepository teachersInCourse(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    TeachersInCourseRepository teachersInCourse();
    StudentsInCourseRepository studentsInCourse(TransactionalContext autoTx);
    StudentsInCourseRepository studentsInCourse();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    ExamRepository exams(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExamRepository exams();

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientInExamRepository studentsInExam();
    
    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExamsInCourseRepository examsInCourse();

    BoardRepository boards(TransactionalContext autoTx);
    BoardRepository boards();
}
