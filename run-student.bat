REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=ecourse.app.student.console\target\ecourse.app.student.console-1.4.0-SNAPSHOT.jar;ecourse.app.student.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% eapli.ecourse.app.student.console.BaseStudentApp
