#!/bin/sh
/etc/rc.d/init.d/mysql stop
/etc/rc.d/init.d/mysql start
service mysqld restart
sleep 1s
/soft/tomcat7/bin/shutdown.sh
sleep 10s
/soft/tomcat7/bin/startup.sh 

