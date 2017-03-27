USE meididi;
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
WHEN c. START BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
	c. START
ELSE
	NULL
END 开始咨询时间,
 CASE
WHEN c.
END BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
	c.
END
ELSE
	NULL
END 结束咨询时间,
 CASE
WHEN c. START BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
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
 CASE
WHEN c. START BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
	d.hos_name
ELSE
	NULL
END 咨询机构,
 CASE
WHEN c. START BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
	d.id
ELSE
	NULL
END 咨询师ID,
 CASE
WHEN c. START BETWEEN (
	date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
)
AND (
	date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
) THEN
	d. NAME
ELSE
	NULL
END 咨询师昵称
FROM
	t_login_record b
RIGHT JOIN t_user a ON b.user_id = a.id
LEFT JOIN t_consult_order c ON a.id = c.user_id
LEFT JOIN t_consultant d ON c.con_id = d.id
WHERE
	(
		b.time BETWEEN (
			date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
		)
		AND (
			date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
		)
	)
AND b.type = 'u'
GROUP BY
	a.id,
	开始咨询时间 INTO OUTFILE '/tmp/", NOW(), "_userActivyty.xlsx';
");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;

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
	d.create_time 注册时间,
	b.time 最近登陆时间,
	d.hos_name 咨询机构,
	CASE
WHEN a. START BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	a. START
ELSE
	NULL
END 开始咨询时间,
 CASE
WHEN a.
END BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	a.
END
ELSE
	NULL
END 结束咨询时间,
 CASE
WHEN a. START BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	timediff(
		STR_TO_DATE(a. END, '%Y-%m-%d %H:%i:%s'),
		STR_TO_DATE(
			a. START,
			'%Y-%m-%d %H:%i:%s'
		)
	)
ELSE
	NULL
END 咨询时长,
 --   timediff(STR_TO_DATE(a. END, '%Y-%m-%d %H:%i:%s'),STR_TO_DATE(a. start, '%Y-%m-%d %H:%i:%s'))咨询时长
CASE
WHEN a. START BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	u.id
ELSE
	NULL
END 用户id,
 CASE
WHEN a. START BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	u.`name`
ELSE
	NULL
END 用户名称,
 CASE
WHEN a. START BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND THEN
	u.phone
ELSE
	NULL
END 用户手机号码
FROM
	t_consultant d
LEFT JOIN t_login_record b ON d.id = b.user_id
LEFT JOIN t_consult_order a ON d.id = a.con_id
LEFT JOIN t_user u ON u.id = a.user_id
WHERE
	(
		(
			b.time BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
			AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
		)
		AND b.type = 'c'
	) -- AND d. NAME <> 'meididi'
GROUP BY
	d.id,
	开始咨询时间 INTO OUTFILE '/tmp/", NOW(), "_consultantActivity.xlsx' ; 

");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;

SET @SqlScript= CONCAT("
SELECT
		a.time,
		a.type,
		a.user_id,
		a.phone
	FROM
		t_login_record a
	WHERE
		time BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
	AND (
		date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
	)
	AND type = 'u' INTO OUTFILE '/tmp/", NOW(),"_userCount.xlsx' ;
");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;

#d登入人数
SET @starttm := date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND;
SET @endtm := date_sub(curdate(), INTERVAL -1 DAY) - INTERVAL 1 SECOND;
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
	NOW(),
	"_new_Save_User.xlsx'
;

 ");

 PREPARE Sql_Text FROM @SqlScript;
 EXECUTE Sql_Text;
SET @SqlScript= CONCAT("
SELECT
	a.id 用户id,
	a. NAME 用户昵称,
	a.age 年龄,
	CASE a.sex
WHEN 1 THEN
	'男'
WHEN 0 THEN
	'女'
END 性别,
 a.phone 电话,
 b.create_time 订单创建时间,
 b.number 订单号,
 c.NAME 咨询机构,
 d.id 咨询师ID,
 d. NAME 咨询师昵称,
 b.money 订单金额,
 CASE 
 WHEN b.state<='1' THEN
	'0'
 WHEN b.state>='2' THEN
  b.first_money  
 END 预支付金额,
 b.first_time 预约时间,
 CASE b.state
 WHEN - 1 THEN
	'订单取消'
 WHEN 0 THEN
	'未确认'
 WHEN 1 THEN
	'已确认'
 WHEN 2 THEN
	'已支付订金'
 WHEN 3 THEN
	'服务开始'
 WHEN 4 THEN
	'支付尾款'
 WHEN 5 THEN
	'已评价'
WHEN 6 THEN
	'已完结'
WHEN 7 THEN
	'申请退款'
WHEN 8 THEN
	'退款完成'
WHEN 9 THEN
	'订单争议'
END 订单状态


FROM
	t_user a
LEFT JOIN t_order b ON a.id = b.user_id
LEFT JOIN t_hospital_information c on b.hos_id=c.account_id
LEFT JOIN t_consultant d ON b.con_id = d.id
WHERE
	(
		b.create_time BETWEEN @starttm
		AND @endtm
	)

ORDER BY
	b.create_time

INTO OUTFILE '/tmp/", NOW(), "_dingdan_User.xlsx'

;
");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;

#咨询人数统计
 SET @SqlScript= CONCAT(
 "
SELECT
	a.id AS 用户id,
 	a.age as 年龄,
 	a. NAME as 用户昵称,
 	CASE a.sex
 WHEN 1 THEN
 	'男'
 WHEN 0 THEN
 	'女'
 END 性别,
  a.phone as 电话
INTO OUTFILE '/tmp/",NOW(), "_zixun_Renshu_Count.xlsx'
FROM
	t_login_record b
RIGHT JOIN t_user a ON b.user_id = a.id
LEFT JOIN t_consult_order c ON a.id = c.user_id
LEFT JOIN t_consultant d ON c.con_id = d.id
WHERE
	(
	  c.start between @starttm and @endtm or c.end between @starttm and @endtm
	) 
AND b.type = 'u' ;
");
 PREPARE Sql_Text FROM @SqlScript;
 EXECUTE Sql_Text;
 