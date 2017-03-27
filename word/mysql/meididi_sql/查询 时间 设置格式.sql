set @starttm:=date_sub(curdate(),interval 0 day) - INTERVAL 0 SECOND;
set @endtm:=date_sub(curdate(),interval -1 day) - INTERVAL 1 SECOND;
set @tm8:=date_sub(@endtm, interval -8 hour);#加8小时
set @ntm:=DATE_ADD(NOW(),  INTERVAL 10 SECOND);#加10s

SELECT @starttm,@endtm,@tm8,NOW(),@ntm;