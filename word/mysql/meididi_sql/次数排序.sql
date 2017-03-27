set @starttm:=date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
-- SELECT @starttm,@endtm;

SET @SqlScript= CONCAT("

SELECT
	a.id 用户ID,
	a. NAME 用户昵称,
	a.phone 用户手机号,
	a.create_time 用户的注册时间,
 	c.time 用户最近登陆,
	count(*)次数

INTO OUTFILE '/tmp/", date_sub(curdate(),interval 1 day), "_cishu,paixun.xlsx'

FROM
	t_user a
LEFT JOIN t_login_record c ON c.user_id = a.id
LEFT JOIN t_consult_order b ON a.id = b.user_id
 AND(
 	b. START BETWEEN @starttm
 	AND @endtm
  	OR b.
  	END BETWEEN @starttm
  	AND @endtm
  ) and c.type='u'
GROUP BY
	a.id
ORDER BY
	COUNT(*)DESC;");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;