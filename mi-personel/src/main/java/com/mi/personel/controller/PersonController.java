package com.mi.personel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mi.personel.dao.AdminDao;
import com.mi.personel.dao.UserDao;

import com.mis.bean.Admininfo;
import com.mis.bean.Memberinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/7 21:16
 **/
@Slf4j
@RestController
@RequestMapping("personel")
public class PersonController {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/showAdmin")
    public Map<String, Object> showAdmin() {
        Map map = new HashMap<String, Object>();
        List<Admininfo> list = null;
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.orderByAsc("aid");
            list = this.adminDao.selectList(wrapper);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @RequestMapping("/showUser")
    public Map<String, Object> showUser() {
        Map map = new HashMap<String, Object>();
        List<Memberinfo> list = null;
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.orderByAsc("mno");
            list = this.userDao.selectList(wrapper);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @RequestMapping("/modifys")
    public Map<String, Object> modifys(@RequestParam Integer mno,
                                       @RequestParam String nickname,
                                       @RequestParam String tel,
                                       @RequestParam String emails) {
        Map map = new HashMap<String, Object>();
        try {
            Memberinfo user = new Memberinfo();
            user.setMno(mno);
            user.setTel(tel);
            user.setNickname(nickname);
            user.setEmail(emails);
            this.userDao.updateById(user);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }

    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam Integer mno) {
        Map map = new HashMap<String, Object>();
        try {
            this.userDao.deleteById(mno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);

        return map;
    }
}

