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
package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.repositories.*;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.exammanagement.repositories.ExamsInCourseRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * @author nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaStudentUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaStudentUserRepository(autoTx);
    }

    @Override
    public JpaStudentUserRepository clientUsers() {
        return new JpaStudentUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CourseRepository courses(final TransactionalContext autoTx) {
        return new JpaCourseRepository(autoTx);
    }

    @Override
    public CourseRepository courses() {
        return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EnrollmentRequestRepository requests(final TransactionalContext autoTx) {
        return new JpaEnrollmentRequestRepository(autoTx);
    }

    @Override
    public EnrollmentRequestRepository requests() {
        return new JpaEnrollmentRequestRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public ClassRepository classes(final TransactionalContext autoTx) {
        return new JpaClassRepository(autoTx);
    }
    @Override
    public ClassRepository classes() {
        return new JpaClassRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TeacherUserRepository teacherUsers(TransactionalContext autoTx) {
        return new JpaTeacherUserRepository(autoTx);
    }

    @Override
    public TeacherUserRepository teacherUsers() {
        return new JpaTeacherUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TeachersInCourseRepository teachersInCourse(final TransactionalContext autoTx) {
        return new JpaTeachersInCourseRepository(autoTx);
    }

    @Override
    public TeachersInCourseRepository teachersInCourse() {
        return new JpaTeachersInCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public StudentsInCourseRepository studentsInCourse(final TransactionalContext autoTx) {
        return new JpaStudentsInCourseRepository(autoTx);
    }

    @Override
    public StudentsInCourseRepository studentsInCourse() {
        return new JpaStudentsInCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return new JpaExamRepository(autoTx);
    }

    @Override
    public ExamRepository exams() {
        return new JpaExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ClientInExamRepository studentsInExam()
    {
        return new JpaStudentsInExamRepository(Application.settings().getPersistenceUnitName());
    }
    
    @Override
    public ExamsInCourseRepository examsInCourse()
    {
        return new JpaExamsInCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardRepository boards(final TransactionalContext autoTx) {
        return new JpaBoardRepository(autoTx);
    }

    @Override
    public BoardRepository boards() {
        return new JpaBoardRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public LogRepository logs(final TransactionalContext autoTx) {
        return new JpaLogRepository(autoTx);
    }

    @Override
    public LogRepository logs() {
        return new JpaLogRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardCellRepository cells(final TransactionalContext autoTx) {
        return new JpaBoardCellRepository(autoTx);
    }

    @Override
    public BoardCellRepository cells() {
        return new JpaBoardCellRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public PostItRepository postIts(final TransactionalContext autoTx) {
        return new JpaPostItRepository(autoTx);
    }

    @Override
    public PostItRepository postIts() {
        return new JpaPostItRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardPermissionRepository boardPermissions(final TransactionalContext autoTx) {
        return new JpaBoardPermissionRepository(autoTx);
    }

    @Override
    public BoardPermissionRepository boardPermissions() {
        return new JpaBoardPermissionRepository(Application.settings().getPersistenceUnitName());
    }

}
