package com.mis.miGoods.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mis.bean.Goodsinfo;
import com.mis.miGoods.biz.MiGoodsBiz;
import com.mis.miGoods.web.model.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "miGoods")
public class MiGoodsController {

    @Autowired
    private MiGoodsBiz miGoodsBiz;

    @RequestMapping("findById/{gno}")
    public Map<String, Object> findById(@PathVariable String gno) {
        Map<String, Object> map = new HashMap<>();
        Goodsinfo m = null;
        try {
            m = this.miGoodsBiz.findById(gno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", m);
        return map;
    }

    //根据商品类型分页
    @RequestMapping("findByTno")
    public Map<String, Object> findByTno(@RequestParam Integer tno,
                                         @RequestParam int pageno,
                                         @RequestParam int pagesize,
                                         @RequestParam String sortby,
                                         @RequestParam String sort) {
        Map<String, Object> map = new HashMap<>();
        Page<Goodsinfo> page = null;
        try {
            page = this.miGoodsBiz.findByTno(tno, pageno, pagesize, sortby, sort);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "出错了");
            return map;
        }
        map.put("code", 1);

        PageBean pageBean = new PageBean();
        pageBean.setPageno(pageno);
        pageBean.setSort(sort);
        pageBean.setSortby(sortby);
        pageBean.setTotal(page.getTotal());
        pageBean.setDataset(page.getRecords());

        // 其它的分页数据
        //计算总页数
        long totalPages = page.getTotal() % pageBean.getPagesize() == 0 ? page.getTotal() / pageBean.getPagesize() : page.getTotal() / pageBean.getPagesize() + 1;
        pageBean.setTotalpages((int) totalPages);
        //上一页页号的计算
        if (pageBean.getPageno() <= 1) {
            pageBean.setPre(1);
        } else {
            pageBean.setPre(pageBean.getPageno() - 1);
        }
        //下一页的页号
        if (pageBean.getPageno() == totalPages) {
            pageBean.setNext((int) totalPages);
        } else {
            pageBean.setNext(pageBean.getPageno() + 1);
        }
        map.put("data", pageBean);
        return map;
    }

    public Set<Thread> set = new HashSet<>();

    //查找所有商品
    @RequestMapping(value = "findAll")
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<Goodsinfo> list = null;
        try {
            list = this.miGoodsBiz.findAllGoods();
        } catch (Exception ex) {
            map.put("code", 0);
            map.put("msg", ex.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("obj", list);
        return map;
    }

    //刚进页面时的分页
    @RequestMapping("findByPage")
    //@SentinelResource(value = "hotkey-page", blockHandler = "handleBlock")//流控资源名
    public Map<String, Object> findByPage(@RequestParam int pageno,
                                          @RequestParam int pagesize,
                                          @RequestParam String sortby,
                                          @RequestParam String sort) {
        Map<String, Object> map = new HashMap<>();
        Page<Goodsinfo> page = null;
        try {
            page = this.miGoodsBiz.findByPage(pageno, pagesize, sortby, sort);
        } catch (Exception ex) {
            map.put("code", 0);
            map.put("msg", ex.getCause());
            return map;
        }
        map.put("code", 1);

        PageBean pageBean = new PageBean();
        pageBean.setPageno(pageno);
        pageBean.setSort(sort);
        pageBean.setSortby(sortby);
        pageBean.setTotal(page.getTotal());
        pageBean.setDataset(page.getRecords());

        // 其它的分页数据
        //计算总页数
        long totalPages = page.getTotal() % pageBean.getPagesize() == 0 ? page.getTotal() / pageBean.getPagesize() : page.getTotal() / pageBean.getPagesize() + 1;
        pageBean.setTotalpages((int) totalPages);
        //上一页页号的计算
        if (pageBean.getPageno() <= 1) {
            pageBean.setPre(1);
        } else {
            pageBean.setPre(pageBean.getPageno() - 1);
        }
        //下一页的页号
        if (pageBean.getPageno() == totalPages) {
            pageBean.setNext((int) totalPages);
        } else {
            pageBean.setNext(pageBean.getPageno() + 1);
        }

        map.put("data", pageBean);
        return map;
    }

    //根据点击的页号显示某一页的商品
    @RequestMapping("/findByIndex")
    public Map<String, Object> findByIndex(
            @RequestParam("pageno") int pageno,
            @RequestParam("pagesize") int pagesize,
            @RequestParam("sortby") String sortby,
            @RequestParam("sort") String sort,
            @RequestParam("sprice") int sprice,
            @RequestParam("eprice") int eprice,
            @RequestParam("gname") String gname,
            @RequestParam(value = "tno", required = false) Integer tno
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<Goodsinfo> page = miGoodsBiz.findByIndex(pageno, pagesize, sortby, sort, sprice, eprice, gname, tno);
            PageBean pageBean = new PageBean();
            pageBean.setPageno(pageno);
            pageBean.setPagesize(pagesize);
            pageBean.setTotal(page.getTotal());
            pageBean.setSort(sort);
            pageBean.setSortby(sortby);
            pageBean.setDataset(page.getRecords());

            //计算总页数
            long totalPages = page.getTotal() % pagesize == 0 ? page.getTotal() / pagesize : page.getTotal() / pagesize + 1;
            pageBean.setTotalpages((int) totalPages);

            // 上一页页号的计算
            if (pageno <= 1) {
                pageBean.setPre(1);
            } else {
                pageBean.setPre(pageno - 1);
            }

            // 下一页的页号
            if (pageno == totalPages) {
                pageBean.setNext((int) totalPages);
            } else {
                pageBean.setNext(pageno + 1);
            }

            map.put("code", 1);
            map.put("data", pageBean);
        } catch (Exception ex) {
            map.put("code", 0);
            map.put("msg", ex.getMessage());
        }
        return map;
    }

    //通过搜索功能搜索所有的商品
//    @RequestMapping("/findByName")
//    public Map<String, Object> findByName(@RequestParam("pageno") int pageno,
//                                          @RequestParam("pagesize") int pagesize,
//                                          @RequestParam("sortby") String sortby,
//                                          @RequestParam("sort") String sort,
//                                          @RequestParam("gname") String gname) {
//        Map<String, Object> map = new HashMap<>();
//        return map;
//    }


}

