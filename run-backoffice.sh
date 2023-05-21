#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=ecourse.app.backoffice.console\target\ecourse.app.backoffice.console-1.4.0-SNAPSHOT.jar;ecourse.app.backoffice.console\target\dependency\*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.ecourse.app.backoffice.console.BaseBackoffice
