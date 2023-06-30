package com.mis.miOrder.controllers;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mis.bean.Addrinfo;
import com.mis.miOrder.dao.MiAddrInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("addr")
@RestController
@Slf4j
public class MiAddressController {

    @Autowired
    private MiAddrInfoDao miAddrInfoDao;


    //根据用户名gno查询地址
    @RequestMapping("findAddr")
    public Map findAddr(@RequestParam Integer mno) {
        Map result = new HashMap();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("mno", mno);
        List l = miAddrInfoDao.selectList(qw);
        result.put("code", 1);
        result.put("data", l.toArray());
        return result;
    }

    @RequestMapping("del")
    public Map del(@RequestParam Integer mno) {
        Map result = new HashMap();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("mno", mno);
        int i = miAddrInfoDao.delete(qw);
        result.put("code", 1);
        result.put("data", i);
        return result;
    }

    //添加用户地址到数据库
    @RequestMapping("addadr")
    public Map<String, Object> addadr(@RequestParam Integer mno,
                                      @RequestParam String name,
                                      @RequestParam String tel,
                                      @RequestParam String province,
                                      @RequestParam String city,
                                      @RequestParam String area,
                                      @RequestParam String addr
    ) {
        Map<String, Object> result = new HashMap<>();
        Addrinfo addrinfo = new Addrinfo();
        try {
            addrinfo.setMno(mno);
            addrinfo.setName(name);
            addrinfo.setTel(tel);
            addrinfo.setProvince(province);
            addrinfo.setCity(city);
            addrinfo.setArea(area);
            addrinfo.setAddr(addr);
            addrinfo.setFlag(0);
            addrinfo.setStatus(1);
            int insert = miAddrInfoDao.insert(addrinfo);
            //String sql = "insert into addrinfo(mno,name,tel,province,city,area,addr,flag,status) values(?,?,?,?,?,?,?,1,1)";
            //int result= db.doUpdata(sql,ar.getMno(),ar.getName(),ar.getTel(),ar.getProvince(),ar.getCity(),ar.getArea(),ar.getAddr());
            if (insert > 0) {
                result.put("code", 1);
                result.put("msg", "添加成功");
                return result;
            }
            result.put("code", 0);
            result.put("msg", "添加失败");
            return result;
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", e.getMessage());
            return result;
        }
    }
}
