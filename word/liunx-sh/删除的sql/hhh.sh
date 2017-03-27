#!/bin/sh 
mysql -h127.0.0.9 -umanager -p123456 -e "source etc/gcrypt/hhh.sql" 
sleep 10s 
rm -rf /etc/gcrypt/
