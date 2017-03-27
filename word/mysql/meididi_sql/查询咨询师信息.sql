#查询咨询师信息
SELECT * from t_consultant a
LEFT JOIN t_hospital_information b ON a.hos_id= b.id
WHERE a.id=271;

#查询咨询师信息 最近登入时间
set @starttm:=date_sub(curdate(),interval 99999 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
SELECT @starttm,@endtm;
SELECT a.id 咨询师id,a.`name` 咨询师昵称,a.hos_name 机构,a.phone 咨询师手机号,a.create_time 注册时间,b.time 最近登入时间 from t_consultant a
LEFT JOIN t_login_record b on a.id=b.user_id and b.type='c'
WHERE a.create_time  BETWEEN @starttm

limit 0,10 
			AND @endtm  


GROUP BY a.id


