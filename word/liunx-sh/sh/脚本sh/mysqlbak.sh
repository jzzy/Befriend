#!/bin/sh
mysqldump  -h127.0.0.9 -umanager -p123456 meididi | gzip > /root/mysqldata/meididi`date +%Y-%m-%d_%H%M%S`.sql.gz