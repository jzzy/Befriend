##查看所有用户
SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;
##删除用户
DROP USER 'manager'@'localhost';
flush privileges;
##创建用户
CREATE USER 'manager'@'localhost' IDENTIFIED BY '123456'; 
flush privileges;
##授权
GRANT ALL ON *.* TO 'manager'@'localhost'; 
flush privileges;



##更新root密码
update user set password=PASSWORD('123456') where user='root' and host='root' or host='localhost';