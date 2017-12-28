#!/bin/bash

function log()
{
	echo "####tail tomcat log####"
	echo "####password=Mwb123qwe####"
    ssh  root@47.93.194.91 tail -f /usr/local/tomcat/logs/catalina.out
}

function deploy()
{
	echo "####tomcat deploy epark####"
	echo "####password=Mwb123qwe####"
    scp target/epark.war root@47.93.194.91:/data/wwwroot/www.mengweibo.com/
	echo "####deploy complete####"
}

function stop()
{
	echo "####tomcat stop####"
	echo "####password=Mwb123qwe####"
    ssh  root@47.93.194.91 service tomcat stop
}

function start()
{
	echo "####tomcat start####"
	echo "####password=Mwb123qwe####"
    ssh  root@47.93.194.91 service tomcat start
}

if [ "$1" == "log" ]
then
	log
elif [ "$1" == "deploy" ]
then
	deploy
elif [ "$1" == "-d" ]
then
    deploy
elif [ "$1" == "start" ]
then
    start
elif [ "$1" == "stop" ]
then
	stop
else
	echo "Please enter -->  mwb.sh log|start|stop|deploy|-d "
fi
