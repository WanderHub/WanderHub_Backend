#!/bin/bash
# 빌드 파일의 이름이 콘텐츠와 다르다면 다음 줄의 .jar 파일 이름을 수정하시기 바랍니다.
BUILD_JAR=$(ls /home/ubuntu/WanderHub_Backend/build/libs/server-0.0.1-SNAPSHOT.jar)

echo "> 현재 시간: $(date)" >> /home/ubuntu/wanderHub-back/log/deploy.log

echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/wanderHub-back/log/deploy.log

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/wanderHub-back/log/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/wanderHub-back/log/deploy.log
else
  echo "> kill -9 $CURRENT_PID" >> /home/ubuntu/wanderHub-back/log/deploy.log
  sudo kill -9 $CURRENT_PID
  sleep 5
fi

echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/wanderHub-back/log/deploy.log
cd /home/ubuntu/WanderHub_Backend/build/libs/
sudo nohup java -jar -Dspring.profiles.active=prod server-0.0.1-SNAPSHOT.jar >> /home/ubuntu/wanderHub-back/log/deploy.log 2>/home/ubuntu/wanderHub-back/log/deploy_err.log &