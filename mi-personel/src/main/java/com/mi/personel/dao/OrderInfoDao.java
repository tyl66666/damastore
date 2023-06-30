package com.mi.personel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mis.bean.Orderinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/11 22:30
 **/
public interface OrderInfoDao extends BaseMapper<Orderinfo> {
    @Select(" select  date_format(odate,'%m') as month ,sum(price) price from orderinfo group by  date_format(odate,'%m')  order by month asc ")
    List<Map<String,Object>> showPrice();

    @Select(" select  date_format(odate,'%m') as name ,sum(price) value from orderinfo group by  date_format(odate,'%m')  order by name asc ")
    List<Map<String,Object>> showPrice1();

    @Select("select count(tname) as value , a.tname name from (select (@i:=@i+1) as id ,tname,odate,orderinfo.price \n" +
            "             from orderinfo,orderiteminfo,goodsinfo ,goodstype,(select @i:=0) as it where orderinfo.ono=orderiteminfo.ono \n" +
            "          and orderiteminfo.gno=goodsinfo.gno and goodsinfo.tno=goodstype.tno) as a group by tname")
    List<Map<String,Object>> showTypes();
}
