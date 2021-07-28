echo 正在启动sentinel-dashboard~
echo sleep 3s
sleep 1
echo sleep 2s
sleep 1
echo sleep 1s
sleep 1
java -Dserver.port=8849 -Dcsp.sentinel.dashboard.server=localhost:8849 -Dproject.name=sentinel-dashboard -jar /Volumes/Application/Java/sentinel-dashboard-1.8.2.jar
echo sentinel-dashboard启动完成~