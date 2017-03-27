SET @SqlScript= CONCAT("SELECT * INTO OUTFILE '/tmp/", date_sub(curdate(),interval 1 day) - INTERVAL 0 SECOND,"-",date_sub(curdate(),interval 0 day) - INTERVAL 1 SECOND, "_sql_out.xlsx' FROM `t_admin`;");
PREPARE Sql_Text FROM @SqlScript;
EXECUTE Sql_Text;