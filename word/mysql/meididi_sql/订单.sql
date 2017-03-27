set @starttm:=date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND;
-- SELECT @starttm,@endtm;

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

