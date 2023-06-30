package com.mis.miOrder.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mis.bean.Goodsinfo;
import com.mis.miOrder.model.CartItem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("cart")
public class MiAddCarController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    //减购物车中商品的数量
    @RequestMapping("lostCartNumByGno")
    public Map<String, Object> lostCartNumByGno(@RequestParam Integer mno,
                                                @RequestParam Integer[] gnolist) {
        Map<String, Object> map = new HashMap<>();

        // 从 Redis 中获取购物车信息
        Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        log.info("选中的购物车中的商品为:" + cart);
        List<CartItem> cartItems = new ArrayList<>();

        // 将商品编号数组转换为列表
        List<Integer> gnolistAsList = Arrays.asList(gnolist);

        // 遍历商品编号列表，查找对应的购物车项
        for (Integer gno : gnolistAsList) {
            // 从购物车信息中获取对应的购物车项
            CartItem ci = (CartItem) cart.get(gno.toString());
            if (ci != null) {
                // 减少购物车项中的商品数量
                int newNum = ci.getNum() - 1;
                if (newNum <= 0) {
                    // 如果商品数量为0，则从购物车中删除该商品
                    this.redisTemplate.opsForHash().delete("cart_" + mno, gno.toString());
                } else {
                    // 更新购物车项中的商品数量
                    ci.setNum(newNum);
                    this.redisTemplate.opsForHash().put("cart_" + mno, gno.toString(), ci);
                }
            }
        }
        // 返回更新后的购物车信息
        //Map<Object, Object> updatedCart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        map.put("code", 1);
        //map.put("data", updatedCart.values());
        return map;
    }

    //加购物车中商品的数量
    @RequestMapping("addCartNumByGno")
    public Map<String, Object> addCartNumByGno(@RequestParam Integer mno,
                                               @RequestParam Integer[] gnolist) {
        Map<String, Object> map = new HashMap<>();

        // 从 Redis 中获取购物车信息
        Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        log.info("选中的购物车中的商品为:" + cart);
        List<CartItem> cartItems = new ArrayList<>();

        // 将商品编号数组转换为列表
        List<Integer> gnolistAsList = Arrays.asList(gnolist);

        // 遍历商品编号列表，查找对应的购物车项
        for (Integer gno : gnolistAsList) {
            // 从购物车信息中获取对应的购物车项
            CartItem ci = (CartItem) cart.get(gno.toString());
            if (ci != null) {
                // 增加购物车项中的商品数量
                int newNum = ci.getNum() + 1;
                ci.setNum(newNum);
                this.redisTemplate.opsForHash().put("cart_" + mno, gno.toString(), ci);
            }
        }

        // 返回更新后的购物车信息
        //Map<Object, Object> updatedCart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        map.put("code", 1);
        //map.put("data", updatedCart.values());
        return map;
    }

    //根据选中的购物车中的商品的gno取到购物车信息
    @RequestMapping("getCartInfoByGno")
    public Map<String, Object> getCartInfoByGno(@RequestParam Integer mno,
                                                @RequestParam Integer[] gnolist) {
        Map<String, Object> result = new HashMap<>();
        // 从 Redis 中获取购物车信息
        Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_" + mno);
        log.info("选中的购物车中的商品为:" + cart);
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
                result.put("code", 0);
                result.put("msg", "查无此商品" + gnolistAsList.get(0));
                return result;
            }
            CartItem ci = new CartItem();
            ci.setGoodsinfo(goodsinfo);
            ci.setNum(1);
            cartItems.add(ci);
        }
        // 返回结果
        result.put("code", 1);
        result.put("data", cartItems);
        return result;
    }

    //添加购物车
    @RequestMapping("addCart")
    public Map<String, Object> addCart(@RequestParam Integer gno,
                                       @RequestParam Integer mno,
                                       @RequestParam Integer num) {
        Map<String, Object> result = new HashMap<>();
        Goodsinfo goodsinfo = null;
        String url = "http://localhost:2357/migoods/miGoods/findById/" + gno;
        Map<String, Object> resultMap = this.restTemplate.getForObject(url, Map.class);
        if ("1".equalsIgnoreCase(resultMap.get("code").toString())) {
            Map m = (Map) resultMap.get("data");
            //如何将一个Map转为  一个  Resfood对象  -> 反射.
            // spring的底层对json的处理使用 jackson框架，这个框架有将map转为对象的方法
            ObjectMapper mapper = new ObjectMapper();
            goodsinfo = mapper.convertValue(m, Goodsinfo.class);
        } else {
            result.put("code", 0);
            result.put("msg", "查无此商品" + gno);
            return result;   //  200
        }
        CartItem ci = (CartItem) this.redisTemplate.opsForHash().get("cart_" + mno, gno + "");
        //加购物车的逻辑
        //CartItem ci= (CartItem) this.redisTemplate.opsForHash().get("cart_"+sessionid,  fid+""  );

        if (ci == null) {
            ci = new CartItem();
            ci.setGoodsinfo(goodsinfo);
            ci.setNum(num);
        } else {
            int newNum = ci.getNum() + num;
            ci.setNum(newNum);
        }
        //计算金额
        if (ci.getNum() <= 0) {
            this.redisTemplate.opsForHash().delete("cart_" + mno, gno + "");
        } else {
            ci.getSmallCount();
            this.redisTemplate.opsForHash().put("cart_" + mno, gno + "", ci);
        }
        result.put("code", 1);

        Map m = redisTemplate.opsForHash().entries("cart_" + mno);
        result.put("data", m.values());
        return result;
    }

    //清空购物车
    @RequestMapping("clearAll")
    public Map<String, Object> clearAll(@RequestParam String mno) {
        Map<String, Object> result = new HashMap<>();
        if (this.redisTemplate.hasKey("cart_" + mno)) {
            Set<Object> keys = this.redisTemplate.opsForHash().keys("cart_" + mno);
            this.redisTemplate.opsForHash().delete("cart_" + mno, keys.toArray());
            result.put("code", 1);
            result.put("obj", keys);   //keys中存的是 删除的商品编号
        } else {
            result.put("code", 0);
        }
        return result;
    }

    //删除选中的购物车中的商品
    @RequestMapping("clear")
    public Map<String, Object> clear(@RequestParam String mno,
                                     @RequestParam String... itemIds) {
        Map<String, Object> result = new HashMap<>();
        if (this.redisTemplate.hasKey("cart_" + mno)) {
            Set<Object> keys = this.redisTemplate.opsForHash().keys("cart_" + mno);
            this.redisTemplate.opsForHash().delete("cart_" + mno, (Object[]) itemIds);
            result.put("code", 1);
            result.put("obj", itemIds);   //keys中存的是 删除的商品编号
        } else {
            result.put("code", 0);
        }
        return result;
    }

    //获取购物车中商品信息
    @RequestMapping("getCartInfo")
    public Map<String, Object> getCartInfo(@RequestParam String mno) {
        Map<String, Object> result = new HashMap<>();
        if (this.redisTemplate.hasKey("cart_" + mno)) {
            Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_" + mno);
            log.info("sessionid为" + ",的购物车为:" + cart);
            result.put("code", 1);
            result.put("data", cart.values());   //keys中存的是 删除的商品编号
        } else {
            result.put("code", 0);
            log.info("购物车是空的...");
        }
        return result;
    }
}

