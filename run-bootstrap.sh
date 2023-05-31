#!/bin/bash

# Set the class path, assuming the build was executed with Maven copy-dependencies
BASE_CP="ecourse.app.bootstrap/target/ecourse.app.bootstrap-1.4.0-SNAPSHOT.jar:ecourse.app.bootstrap/target/dependency/*"

# Call the Java VM
java -cp "$BASE_CP" eapli.ecourse.app.bootstrap.BaseBootstrap -bootstrap:demo

