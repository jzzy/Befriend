#!/bin/sh

rsync -av /soft/tomcat7/webapps/meididi/ /root/back_meididi/meididi/
tar -zcvf /root/back_meididi/meididi_WEB`date +%Y-%m-%d_%H%M%S`.tar.gz /soft/tomcat7/webapps/meididi
mysqldump  -h127.0.0.9 -umanager -p123456 meididi | gzip > /root/back_meididi/meididi_sql`date +%Y-%m-%d_%H%M%S`.sql.gz
