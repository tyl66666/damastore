package com.mis.miOrder.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.mis.bean.Orderinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MiOrderInfoDao extends BaseMapper<Orderinfo> {
    @Insert("INSERT INTO `orderinfo` (`odate`, `ano`, `status`, `invoice`, `price`) VALUES (#{odate}, #{ano}, #{status}, #{invoice}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "ono")
    int insertOrderinfo(Orderinfo orderinfo);

    @Update("UPDATE orderinfo SET status = #{status}, rdate = #{rdate} WHERE ono = #{ono}")
    void updateOrderStatusByOno(@Param("ono") Integer ono, @Param("status") Integer status, @Param("rdate") String rdate);

    @Update("UPDATE orderinfo SET invoice = #{invoice} WHERE ono = #{ono}")
    void updateOrderInvoiceByOno(@Param("ono") Integer ono, @Param("invoice") Integer invoice);

    @Update("UPDATE goodsinfo SET balance = #{balance} WHERE gno = #{gno}")
    void updateGoodsNumByGno(@Param("gno") Integer gno, @Param("balance") Integer balance);
}
