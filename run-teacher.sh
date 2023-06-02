#!/bin/bash

# Set the class path, assuming the build was executed with Maven copy-dependencies
BASE_CP="ecourse.app.teacher.console/target/ecourse.app.teacher.console-1.4.0-SNAPSHOT.jar:ecourse.app.teacher.console/target/dependency/*"

# Call the Java VM
java -cp "$BASE_CP" eapli.ecourse.app.teacher.console.BaseTeacherApp
