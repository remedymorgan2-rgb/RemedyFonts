#!/bin/bash
export JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-17-openjdk
exec gradle "$@"
