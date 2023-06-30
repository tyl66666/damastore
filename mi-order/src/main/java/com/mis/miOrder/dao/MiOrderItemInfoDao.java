package com.mis.miOrder.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mis.bean.Orderiteminfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


public interface MiOrderItemInfoDao extends BaseMapper<Orderiteminfo> {
    @Select("SELECT o.ono, g.gname, o.odate, oi.nums, oi.price * oi.nums AS subtotal, o.status " +
            "FROM orderinfo o " +
            "JOIN addrinfo a ON o.ano = a.ano " +
            "JOIN orderiteminfo oi ON o.ono = oi.ono " +
            "JOIN goodsinfo g ON oi.gno = g.gno " +
            "WHERE a.mno = #{mno} " +
            "ORDER BY o.odate DESC")
    List<Map<String, Object>> selectOrderInfoByMno(@Param("mno") Integer mno);
    //这一段是显示订单的sql语句

    @Update("UPDATE orderiteminfo SET status = #{status} WHERE ono = #{ono}")
    void updateOrderItemStatusByOno(@Param("ono") Integer ono, @Param("status") Integer status);
}
