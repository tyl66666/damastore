package com.mis.miGoods.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mis.bean.Goodsinfo;
import com.mis.bean.Memberinfo;
import com.mis.miGoods.dao.MiGoodsDao;
import com.mis.web.bean.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/10 20:41
 **/
@Slf4j
@RestController
@RequestMapping("addgoods")
public class AddGoodsController {
    @Autowired
    private MiGoodsDao goodsDao;

    private String filePath = "E:/upload";

    @PostMapping("/uploadPic")
    @ResponseBody
    public Map<String, String> upload(@RequestParam Integer gno,
                                      @RequestPart MultipartFile head
    ) {
//        int gnoo=Integer.parseInt(gno);
        Goodsinfo s = new Goodsinfo();
        s.setGno(gno);
        s.setPics(head.getOriginalFilename());
//        String lNames=new String();
//        for (MultipartFile f:lifes){
//            lNames+=f.getOriginalFilename()+",";
//        }
//        lNames = lNames.substring(0, lNames.length() - 1);
//        s.setLifes(lNames);
        //操作：1.先将数据插入到数据库  2.再将图片保存到服务器中 -> 事务 -> 业务层
//        stuBiz.addStud(s,head);

        int r = goodsDao.updateById(s);
        if (r <= 0) {
            throw new RuntimeException("上传照片失败");
        }
        Memberinfo memberinfo = new Memberinfo();
        //项目绝对路径
        String absolutePath = new File("").getAbsolutePath();
        log.info("absolutePath:" + absolutePath);
        // 保存到项目中的文件夹中
        //String path = absolutePath + "mi-index/src/main/resources/static/images/upload";
        File localFile = new File(absolutePath + "/mi-index/src/main/resources/static/images" + File.separator + memberinfo.getPhoto());
        try {
            head.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传照片失败");
        }
        Map<String, String> map = new HashMap<>();
        map.put("code", 1 + "");
        return map;
    }

    @GetMapping("/showGoods")
    public Map<String, Object> showGoods() {
        Map map = new HashMap<String, Object>();
        List<Goodsinfo> list = null;
        try {
            list = this.goodsDao.selectList
                    (new QueryWrapper<Goodsinfo>().orderByAsc("gno"));
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    @RequestMapping("findByPage")
    // @SentinelResource(value = "hotkey-page",blockHandler = "handleBlock")   //流控资源名
    public Map<String, Object> findByPage(@RequestParam int pageno, @RequestParam int pagesize,
                                          @RequestParam String sortby, @RequestParam String sort) {
        Map<String, Object> map = new HashMap<>();
        Page<Goodsinfo> page = null;
        try {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.orderByAsc("gno");
            Page<Goodsinfo> goodsPage = new Page<>(pageno, pagesize);
            page = this.goodsDao.selectPage(goodsPage, wrapper);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        PageBean pageBean = new PageBean();
        pageBean.setPageno(pageno);
        pageBean.setSort(sort);
        pageBean.setSortby(sortby);
        pageBean.setTotal(page.getTotal());
        pageBean.setDataset(page.getRecords());
        //其它分页数据
        //计算总页数
        long totalpages = page.getTotal() % pageBean.getPagesize() == 0 ? page.getTotal() / pageBean.getPagesize() : page.getTotal() / pageBean.getPagesize() + 1;
        pageBean.setTotalPages((int) totalpages);

        //上一页页号的计算
        if (pageBean.getPageno() <= 1) {
            pageBean.setPre(1);
        } else {
            pageBean.setPre(pageBean.getPageno() - 1);
        }
        //下一页页号的计算
        if (pageBean.getPageno() == totalpages) {
            pageBean.setNext((int) totalpages);
        } else {
            pageBean.setNext(pageBean.getPageno() + 1);
        }
        map.put("data", pageBean);
        return map;
    }


    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String gname,
                                   @RequestParam Integer tno,
                                   @RequestParam String price,
                                   @RequestParam String intro,
                                   @RequestParam Integer balance,
                                   @RequestParam String pics,
//                                      @RequestParam String qperied,
                                   @RequestParam String descr,
                                   @RequestParam Integer status) {
        Map map = new HashMap<String, Object>();
        try {
            Goodsinfo goodsinfo = new Goodsinfo();
            goodsinfo.setGname(gname);
            goodsinfo.setTno(tno);
            goodsinfo.setPrice(price);
            goodsinfo.setIntro(intro);
            goodsinfo.setBalance(balance);
            goodsinfo.setPics(pics);
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            goodsinfo.setQperied(today.format(formatter));
            goodsinfo.setDescr(descr);
            goodsinfo.setStatus(status);
            this.goodsDao.insert(goodsinfo);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }

    @PostMapping("/delgood")
    public Map<String, Object> del(@RequestParam Integer gno) {
        Map map = new HashMap<String, Object>();
        try {
            this.goodsDao.deleteById(gno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);

        return map;
    }

    @PostMapping("/modifygood")
    public Map<String, Object> modifys(@RequestParam Integer gno,
                                       @RequestParam Integer tno,
                                       @RequestParam String gname,
                                       @RequestParam String price,
                                       @RequestParam Integer balance,
                                       @RequestParam Integer status,
                                       @RequestParam String pic
    ) {
        Map map = new HashMap<String, Object>();
        try {
            Goodsinfo goodsinfo = new Goodsinfo();
            goodsinfo.getGno();
            goodsinfo.getTno();
            goodsinfo.setPrice(price);
            goodsinfo.setGname(gname);
            goodsinfo.setBalance(balance);
            goodsinfo.setStatus(status);
            goodsinfo.setPics(pic);
            this.goodsDao.updateById(goodsinfo);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        return map;
    }
}
