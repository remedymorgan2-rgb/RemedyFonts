#!/bin/sh
# Use the Java set up on the system (no hardcoded path)
exec java -cp "gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
