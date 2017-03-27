USE meididi;


SET @starttm := date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND;


SET @endtm := date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND;

#加8小时
SET @tm8 := date_sub(@endtm, INTERVAL - 5 HOUR);

#加10s
SET @ntm := DATE_ADD(NOW(), INTERVAL 10 SECOND);

SELECT
	@starttm,
	@endtm ,@tm8 ,@ntm;

DROP EVENT
IF EXISTS `myeventZ10`;
DELIMITER $$ 



SET GLOBAL event_scheduler = 1 ; CREATE EVENT myeventZ10 ON SCHEDULE EVERY 1 DAY STARTS (
	DATE_ADD(
		NOW(),
		INTERVAL 60 * 10 + 2 SECOND
	)
) DO

BEGIN
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
END 用户手机号码,
 date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND,
 date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND,
 NOW()
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
	开始咨询时间 INTO OUTFILE '/tmp/10.19consultantActivity.xlsx' ; END$$  
DELIMITER ; 