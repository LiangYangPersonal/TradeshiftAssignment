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

echo "========TRADESHIFT_HOME================="
echo $TRADESHIFT_HOME

CP="`cat $TRADESHIFT_HOME/bin/classpath`"
eval CP="$CP"
CP=$CP:$TRADESHIFT_HOME/config

# echo using classpath: \"$CP\"
if [[ $1 == "debug" ]]; then
  JVM_DEBUG_PARAMS="-agentlib:jdwp=server=y,transport=dt_socket,suspend=n,address=0.0.0.0:8888"
else
  JVM_DEBUG_PARAMS= 
fi 

JVM_PARAMS="-Xmx3G -XX:+UseConcMarkSweepGC $JVM_DEBUG_PARAMS"
MAIN="com.tradeshift.launcher.TradeshiftMain"
CTX="application"

LOG_SUBDIR="var/log"
LOGDIR=$TRADESHIFT_HOME/$LOG_SUBDIR
if [ ! -d $LOGDIR ]
then
	echo "mkdir $LOG_SUBDIR"
	mkdir -p -m 0755 $LOGDIR	
fi

echo "========LOGDIR================="
echo $LOGDIR


$JAVA_HOME/bin/java $JVM_PARAMS -cp "$CP" -DTRADESHIFT_HOME="$TRADESHIFT_HOME" $MAIN -x -c $CTX "$@">>$LOGDIR/tradeshift.err  2>>$LOGDIR/tradeshift.out &

