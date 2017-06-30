#!/bin/bash

APP_HOME=`pwd`
JAVA_OPTS="-server -Xms1500M -Xmx1500M -Xmn512M -XX:PermSize=256M -XX:MaxPermSize=256M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=7"

#DEBUG_OPTS="-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7088"

CLASSPATH="$APP_HOME/classes:$APP_HOME/lib/*:"
APP_MAIN="com.magic.crius.bootstrap.Application"
psid=0
checkpid() {
   javaps=`jps -l | grep $APP_MAIN`
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}

start() {
  checkpid
  if [ $psid -ne 0 ]; then
    echo "warn: $APP_MAINCLASS already started! (pid=$psid)"
  else
    echo -n "Starting $APP_MAIN ..."
    nohup java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN -t 600 -n 10 > $APP_HOME/logs/stdout.out   &
    checkpid
    if [ $psid -ne 0 ]; then
      echo "(pid=$psid) [OK]"
    else
      echo "[Failed]"
    fi
  fi
}

stop() {
  checkpid
  if [ $psid -ne 0 ]; then
    echo -n "Stopping $APP_MAIN ...(pid=$psid) "
    kill -9 $psid
    if [ $? -eq 0 ]; then
       echo "[OK]"
    else
       echo "[Failed]"
    fi

    checkpid
    if [ $psid -ne 0 ]; then
       stop
    fi
  else
    echo "warn: $APP_MAIN is not running"
  fi
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
*)
  echo "Usage: crius-web.sh {start|stop}"
esac

exit 0