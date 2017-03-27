USE meididi;


SET @starttm := date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND;


SET @endtm := date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND;

#加8小时
SET @tm8 := date_sub(@endtm, INTERVAL - 5 HOUR);

#加10s
SET @ntm := DATE_ADD(NOW(), INTERVAL 10 SECOND);

SELECT
	@starttm ,@endtm ,@tm8 ,@ntm;

DROP EVENT
IF EXISTS `myeventD10`;
DELIMITER $$ 



SET GLOBAL event_scheduler = 1 ; CREATE EVENT myeventD10 ON SCHEDULE EVERY 1 DAY STARTS (
	DATE_ADD(
		NOW(),
		INTERVAL 60 * 10 + 1 SECOND
	)
) DO

BEGIN
	SELECT
		a.time,
		a.type,
		a.user_id,
		a.phone,
		date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND,
		date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND,
		NOW()
	FROM
		t_login_record a
	WHERE
		time BETWEEN date_sub(curdate(), INTERVAL 0 DAY) - INTERVAL 0 SECOND
	AND date_sub(curdate(), INTERVAL - 1 DAY) - INTERVAL 1 SECOND
	AND type = 'u' INTO OUTFILE '/tmp/10.19userCount.xlsx' ; END$$  
DELIMITER ;  