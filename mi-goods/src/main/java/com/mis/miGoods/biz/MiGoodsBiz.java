package com.mis.miGoods.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mis.bean.Goodsinfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MiGoodsBiz {
    public List<Goodsinfo> findAllGoods();

    public Goodsinfo findById(String fid);

    public Page<Goodsinfo> findByPage(int pageno, int pagesize, String sortby, String sort);

    public Page<Goodsinfo> findByTno(Integer tno, int pageno, int pagesize, String sortby,
                                     String sort);

    public Page<Goodsinfo> findByIndex(int pageno, int pagesize, String sortby,
                                       String sort, int sprice,
                                       int eprice, String gname, Integer tno);

//    public Page<Goodsinfo> findByName(int pageno, int pagesize, String sortby,
//                                       String sort, String gname);
}
