package com.mis.miOrder.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mis.bean.Addrinfo;
import com.mis.bean.Goodsinfo;
import com.mis.bean.Memberinfo;
import com.mis.miGoods.dao.MiGoodsDao;
import com.mis.miOrder.biz.MiOrderInfoBiz;
import com.mis.miOrder.dao.MiAddrInfoDao;
import com.mis.miOrder.dao.MiOrderItemInfoDao;
import com.mis.miOrder.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequestMapping("order")
@RestController
@Slf4j
public class MiOrderController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MiOrderInfoBiz miOrderInfoBiz;
    @Autowired
    private MiAddrInfoDao miAddrInfoDao;
    @Autowired
    private Addrinfo addrinfo;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MiOrderItemInfoDao miOrderItemInfoDao;

    //商品下单,操作数据库,再删掉购物车中商品的信息
    @RequestMapping("orderGoods")
    public Map<String, Object> orderGoods(@RequestParam Integer ano,
                                          @RequestParam Integer mno,
                                          @RequestParam Integer[] gnolist) {
        Map<String, Object> map = new HashMap<>();

        // 从 Redis 中获取购物车信息
        Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        List<CartItem> cartItems = new ArrayList<>();
        // 将商品编号数组转换为列表
        List<Integer> gnolistAsList = Arrays.asList(gnolist);
        // 遍历商品编号列表，查找对应的购物车项
        for (Integer gno : gnolistAsList) {
            // 从购物车信息中获取对应的购物车项
            CartItem ci = (CartItem) cart.get(gno.toString());
            if (ci != null) {
                cartItems.add(ci);
            }
        }
        if (cartItems.size() == 0) {//说明不是添加了购物车再购买,是直接购买的
            Goodsinfo goodsinfo = null;
            String url = "http://localhost:2357/migoods/miGoods/findById/" + gnolistAsList.get(0);
            Map<String, Object> resultMap = this.restTemplate.getForObject(url, Map.class);
            if ("1".equalsIgnoreCase(resultMap.get("code").toString())) {
                Map m = (Map) resultMap.get("data");
                ObjectMapper mapper = new ObjectMapper();
                goodsinfo = mapper.convertValue(m, Goodsinfo.class);
            } else {
                map.put("code", 0);
                map.put("msg", "查无此商品" + gnolistAsList.get(0));
                return map;
            }
            CartItem ci = new CartItem();
            ci.setGoodsinfo(goodsinfo);
            ci.setNum(1);
            cartItems.add(ci);
        }
        //调用业务层
        int result = this.miOrderInfoBiz.order(ano, cartItems);
        if (result > 0) {
            // 删除购物车中的商品信息
            for (Integer gno : gnolistAsList) {
                this.redisTemplate.opsForHash().delete("cart_" + mno, gno.toString());
            }
            map.put("data", cartItems);
            map.put("ono", result);
            map.put("code", 1);
        } else {
            map.put("code", 0);
            log.info("业务层有错误...");
        }
        return map;
    }

    //获取订单信息
    @RequestMapping("getOrderInfo")
    public Map<String, Object> getOrderInfo(@RequestParam Integer mno) {
        Map<String, Object> map = new HashMap<>();
        List list = null;
        try {
            list = this.miOrderItemInfoDao.selectOrderInfoByMno(mno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    //用户点击确认收货之后,改变status的值(由0->1)
    @RequestMapping("confirmDelivery")
    public Map<String, Object> confirmDelivery(@RequestParam Integer ono) {
        Map<String, Object> map = new HashMap<>();

        int result = this.miOrderInfoBiz.confirmDelivery(ono);
        if (result != 1) {
            map.put("code", 0);
            return map;
        }
        map.put("code", 1);
        return map;
    }
}
