package com.mi.personel.controller;

import com.mi.personel.dao.OrderInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/9 21:00
 **/
@Slf4j
@RestController
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private OrderInfoDao orderInfoDao;

    @GetMapping("/showPrice")
    public Map<String, Object> showGoods() {
        Map map = new HashMap<String, Object>();
        List list = null;
        try {
            list = this.orderInfoDao.showPrice();
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @GetMapping("/showPrice1")
    public Map<String, Object> showGoods1() {
        Map map = new HashMap<String, Object>();
        List list = null;
        try {
            list = this.orderInfoDao.showPrice1();
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @GetMapping("/showTypes")
    public Map<String, Object> showTypes() {
        Map map = new HashMap<String, Object>();
        List list = null;
        try {
            list = this.orderInfoDao.showTypes();
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }
}
