echo 正在启动nacos
sh /Volumes/Application/Java/nacos/bin/startup.sh -m standalone
echo sleep 3s
sleep 1
echo sleep 2s
sleep 1
echo sleep 1s
sleep 1
echo nacos启动完成
tail -f /Volumes/Application/Java/nacos/logs/nacos.log