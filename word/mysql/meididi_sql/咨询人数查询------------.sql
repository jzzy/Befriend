
set @starttm:=date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
 SELECT @starttm,@endtm;
-- 
--  SET @SqlScript= CONCAT(
--  "
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
FROM
	t_login_record b
RIGHT JOIN t_user a ON b.user_id = a.id
LEFT JOIN t_consult_order c ON a.id = c.user_id
LEFT JOIN t_consultant d ON c.con_id = d.id
WHERE
	(
	  c.start between @starttm and @endtm or c.end between @starttm and @endtm
	) 
AND b.type = 'u' 
GROUP BY a.id
ORDER BY
	a.create_time;
 INTO OUTFILE '/tmp/",date_sub(curdate(),interval 1 day), "_zixun,renshu.xls'
 
--  "
--  );
--  
--  
--  
--  PREPARE Sql_Text FROM @SqlScript;
--  EXECUTE Sql_Text;
 