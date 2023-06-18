REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=ecourse.board.server\target\ecourse.board.server-1.4.0-SNAPSHOT.jar;
ecourse.board.server\target\dependency\*;


REM call the java VM, e.g,
java -cp %BASE_CP% eapli.ecourse.board.server.TcpServer