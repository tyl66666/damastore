package com.mis.miGoods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mis.bean.Goodsinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MiGoodsDao extends BaseMapper<Goodsinfo> {
    @Select("select (@i:=@i+1) as id ,orderinfo.ono,odate,goodsinfo.gname,name,tel,province,city,area,addr,nums,orderinfo.price,orderinfo.status from orderinfo,orderiteminfo,addrinfo,goodsinfo ,(select @i:=0) as it where orderinfo.ono=orderiteminfo.ono and orderinfo.ano=addrinfo.ano and orderiteminfo.gno=goodsinfo.gno;")
//    @Results({
//            @Result(property="ono",column="ono"),
//            @Result(property="gno",column="gno"),
//            @Result(property="ano",column="ano")
//    })
    List<Map<String,Object>> showOrder();
}
