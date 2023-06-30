package com.mis.miOrder.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mis.bean.Addrinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MiAddrInfoDao extends BaseMapper<Addrinfo> {
    @Select("SELECT ano FROM addrinfo WHERE mno = #{mno}")
    List<Integer> selectAnoListByMno(Integer mno);
}
