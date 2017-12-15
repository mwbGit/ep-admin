#!/bin/bash

function log()
{
	echo "####tail tomcat log####"
	echo "####password=Mwb123qwe####"
    ssh  root@47.93.194.91 tail -f /usr/local/tomcat/logs/catalina.out
}

function restart()
{
	echo "####tomcat restart####"
	echo "####password=Mwb123qwe####"
    ssh  root@47.93.194.91 service tomcat restart
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
elif [ "$1" == "restart" ]
then
	restart
elif [ "$1" == "start" ]
then
    start
elif [ "$1" == "stop" ]
then
	stop
else
	echo "Please enter -->  mwb.sh log|start|stop|restart "
fi
