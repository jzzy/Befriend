set @starttm:=date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
SELECT @starttm,@endtm;
set @rqtm:=date_sub(curdate(),interval 1 day);#前一天日期
SET @SqlScript= CONCAT("

SELECT
	d.id 咨询师id,
	d. NAME 咨询师昵称,
 CASE d.sex
WHEN 1 THEN
	'男'
WHEN 0 THEN
	'女'
END 性别,
 d.phone 电话,
CASE
WHEN a. START BETWEEN @starttm
AND @endtm THEN
	u.`name`
ELSE
	NULL
END 用户名称,
 CASE
WHEN a. START BETWEEN @starttm
AND @endtm THEN
	u.phone
ELSE
	NULL
END 用户手机号码,
 CASE
WHEN a. START BETWEEN @starttm
AND @endtm THEN
	u.id
ELSE
	NULL
END 用户id,
 d.create_time 注册时间,
 b.time 最近登陆时间,
 d.hos_name 咨询机构,
 CASE
WHEN a. START BETWEEN @starttm
AND @endtm THEN
	a. START
ELSE
	NULL
END 开始咨询时间,
 CASE
WHEN a.
END BETWEEN @starttm
AND @endtm THEN
	a.
END
ELSE
	NULL
END 结束咨询时间,
 CASE
WHEN a. START BETWEEN @starttm
AND @endtm THEN
	timediff(
		STR_TO_DATE(a. END, '%Y-%m-%d %H:%i:%s'),
		STR_TO_DATE(
			a. START,
			'%Y-%m-%d %H:%i:%s'
		)
	)
ELSE
	NULL
END 咨询时长 --   timediff(STR_TO_DATE(a. END, '%Y-%m-%d %H:%i:%s'),STR_TO_DATE(a. start, '%Y-%m-%d %H:%i:%s'))咨询时长



FROM
	t_consultant d
LEFT JOIN t_login_record b ON d.id = b.user_id
LEFT JOIN t_consult_order a ON d.id = a.con_id
LEFT JOIN t_user u ON u.id = a.user_id
WHERE
	(
		(
			b.time BETWEEN @starttm
			AND @endtm
		)
		AND b.type = 'c'
	)
AND d. NAME <> 'meididi'
GROUP BY
	d.id,
	开始咨询时间 
INTO OUTFILE '/tmp/", date_sub(curdate(),interval 1 day), "_zixunshi_.xls'
;");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;


