use meididi;
SET @starttm := date_sub(curdate(), INTERVAL 1 DAY) - INTERVAL 0 SECOND;


SET @endtm := date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 1 SECOND;
 SET @SqlScript = CONCAT(
 "

SELECT
	a.id AS '用户id',
  a.create_time 注册时间,
  a.phone 电话,
	a. NAME 用户昵称,
  a.age 年龄,
	CASE a.sex
WHEN 1 THEN
	'男'
WHEN 0 THEN
	'女'
END 性别,
 
 
 b.time 最近登陆时间 

FROM
	t_login_record b
RIGHT   JOIN t_user a ON b.user_id = a.id

WHERE
	(
		a.create_time BETWEEN @starttm
		AND @endtm 
	)
ORDER BY
	a.create_time

INTO OUTFILE '/tmp/",
	date_sub(curdate(),interval 1 day),
	"_meitian,xinzeng,userCount.xlsx'
;

 ");

 PREPARE Sql_Text FROM @SqlScript;
 EXECUTE Sql_Text;