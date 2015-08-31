#!/bin/bash

# script to start/shutdown the server
# requires JAVA_HOME and TRADESHIFT_HOME variables.

if [[ -z "$TRADESHIFT_HOME" ]]; then
        echo "TRADESHIFT_HOME is not set.";
        exit 1;
fi

if [[ -z "$JAVA_HOME" ]]; then
        echo "JAVA_HOME is not set.";
        exit 1;
fi

CP="`cat $TRADESHIFT_HOME/bin/classpath`"
eval CP="$CP"

# echo using classpath: \"$CP\"

MAIN="com.tradeshift.launcher.TradeshiftMain"
JVM_PARAMS="-Xmx1G -XX:+UseConcMarkSweepGC"

$JAVA_HOME/bin/java $JVM_PARAMS -cp "$CP" $MAIN -s "$@"

