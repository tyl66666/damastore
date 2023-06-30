package com.mis.miGoods.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.mis.bean.Orderinfo;
import com.mis.miGoods.dao.MiGoodsDao;
import com.mis.miGoods.dao.OrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/11 19:41
 **/
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private MiGoodsDao goodsMapper;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/showOrder")
    public Map<String, Object> showGoods() {
        Map map = new HashMap<String, Object>();
        List list = null;
        try {
            list = this.goodsMapper.showOrder();
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));
//            }

        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @PostMapping("/modifyOrder")
    public Map<String, Object> modifys(@RequestParam String post,
                                       @RequestParam String ono) {
        Map map = new HashMap<String, Object>();
        try {
            Orderinfo orderinfo = new Orderinfo();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String sdate = formatter.format(now);

            UpdateWrapper<Orderinfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ono", ono);

            if (post.equals("del")) {
                //orderinfo.setStatus(0);
                orderinfo.setSdate(null);
                orderinfo.setStatus(0);
                //updateWrapper.eq("sdate", null);
            } else {
                //orderinfo.setStatus(1);
                orderinfo.setSdate(sdate);
                orderinfo.setStatus(2);
                //updateWrapper.eq("sdate", sdate);
            }

            this.orderDao.update(orderinfo, updateWrapper);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }

    @PostMapping("/delOrder")
    public Map<String, Object> del(@RequestParam String ono) {
        Map map = new HashMap<String, Object>();
        try {
//            this.orderDao.deleteById(ono);
            this.orderDao.delete(new QueryWrapper<Orderinfo>().eq("ono", ono));
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);

        return map;
    }

}
