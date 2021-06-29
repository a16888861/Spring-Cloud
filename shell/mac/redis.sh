echo 正在启动Redis
redis-server /usr/local/etc/redis.conf
echo sleep 3s
sleep 1
echo sleep 2s
sleep 1
echo sleep 1s
sleep 1
echo Redis启动完成
ps aux|grep redis