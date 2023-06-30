package com.mis.miGoods.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.mis.bean.Goodstype;
import com.mis.miGoods.dao.TypeDao;
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
 * @Date 2023/5/10 19:55
 **/
@Slf4j
@RestController
@RequestMapping("types")
public class TypeController {
    @Autowired
    private TypeDao typedao;

    @RequestMapping("/showType")
    public Map<String, Object> ShowType() {
        Map map = new HashMap<String, Object>();
        List<Goodstype> list = null;
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.orderByAsc("tno");
            list = this.typedao.selectList(wrapper);
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
    public Map<String, Object> modifys(@RequestParam Integer tno,
                                       @RequestParam String tname,
                                       @RequestParam Integer status) {
        Map map = new HashMap<String, Object>();
        try {
            Goodstype goodstype = new Goodstype();
            goodstype.setTno(tno);
            goodstype.setTname(tname);
            goodstype.setStatus(status);
            this.typedao.updateById(goodstype);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add(   //@RequestParam Integer tno,
                                      @RequestParam String tname,
                                      @RequestParam Integer status) {
        Map map = new HashMap<String, Object>();
        try {
            Goodstype goodstype = new Goodstype();
//            goodstype.setTno(tno);
            goodstype.setTname(tname);
            goodstype.setStatus(status);
            this.typedao.insert(goodstype);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }

    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam Integer tno) {
        Map map = new HashMap<String, Object>();
        try {
            this.typedao.deleteById(tno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);

        return map;
    }
}
