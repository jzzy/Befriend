set @starttm:=date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
-- SELECT @starttm,@endtm;

SET @SqlScript= CONCAT("

SELECT
	a.id 用户id,
	a.age 年龄,
	a. NAME 用户昵称,
	CASE a.sex
WHEN 1 THEN
	'男'
WHEN 0 THEN
	'女'
END 性别,
 a.phone 电话,
 a.create_time 注册时间,
 b.time 最近登陆时间,
 CASE
WHEN c. START BETWEEN @starttm
AND @endtm THEN
	c. START
ELSE
	NULL
END 开始咨询时间,
 CASE
WHEN c.
END BETWEEN @starttm
AND @endtm THEN
	c.END
ELSE
	NULL
END 结束咨询时间,
 CASE
WHEN c. START BETWEEN @starttm
AND @endtm THEN
	timediff(
		STR_TO_DATE(c. END, '%Y-%m-%d %H:%i:%s'),
		STR_TO_DATE(
			c. START,
			'%Y-%m-%d %H:%i:%s'
		)
	)
ELSE
	NULL
END 咨询时长,
 d.hos_name 咨询机构,
 d.id 咨询师ID,
 d.name 咨询师昵称

INTO OUTFILE '/tmp/", date_sub(curdate(),interval 1 day), "_t_quanbu,yonghu.xlsx'

FROM
	t_login_record b
RIGHT JOIN t_user a ON b.user_id = a.id
LEFT JOIN t_consult_order c ON a.id = c.user_id
LEFT JOIN t_consultant d ON c.con_id = d.id
WHERE
	(
	  b.time between @starttm
		AND @endtm 
	) 
AND b.type = 'u'
GROUP BY a.id,开始咨询时间

;");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;