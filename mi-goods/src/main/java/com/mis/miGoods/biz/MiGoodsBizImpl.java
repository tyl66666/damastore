package com.mis.miGoods.biz;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mis.bean.Goodsinfo;
import com.mis.miGoods.dao.MiGoodsDao;
import com.mis.miGoods.dao.TypeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS,
        isolation = Isolation.DEFAULT, timeout = 2000,
        readOnly = true, rollbackFor = RuntimeException.class
)
@Slf4j
public class MiGoodsBizImpl implements MiGoodsBiz {
    @Autowired
    private MiGoodsDao miGoodsDao;

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Goodsinfo> findAllGoods() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("gno");
        return this.miGoodsDao.selectList(wrapper);
    }

    @Override
    public Goodsinfo findById(String gno) {
        return this.miGoodsDao.selectById(gno);
    }

    //根据商品类型分页
    @Override
    public Page<Goodsinfo> findByTno(Integer tno, int pageno, int pagesize, String sortby,
                                     String sort) {
        Page<Goodsinfo> page = new Page<>(pageno, pagesize);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tno", tno);

        if (sort.equalsIgnoreCase("asc")) {
            wrapper.orderByAsc(sortby);
        } else {
            wrapper.orderByDesc(sortby);
        }
        Page<Goodsinfo> tnoPage = miGoodsDao.selectPage(page, wrapper);
        return tnoPage;

    }

    //刚进页面时的分页
    @Override
    public Page<Goodsinfo> findByPage(int pageno, int pagesize, String sortby, String sort) {
        QueryWrapper wrapper1 = new QueryWrapper();
        if ("asc".equalsIgnoreCase(sort)) {
            wrapper1.orderByAsc(sortby);
        } else {
            wrapper1.orderByDesc(sortby);
        }
        Page<Goodsinfo> page = new Page<>(pageno, pagesize);
        //执行分页查询
        Page<Goodsinfo> miPage = this.miGoodsDao.selectPage(page, wrapper1);
        log.info("总记录数=" + miPage.getTotal());
        log.info("总页数=" + miPage.getPages());
        log.info("当前页码=" + miPage.getCurrent());
        return miPage;
    }

    //根据点击的页号显示某一页的商品
    @Override
    public Page<Goodsinfo> findByIndex(int pageno, int pagesize, String sortby, String sort, int sprice, int eprice, String gname, Integer tno) {
        // 构建查询条件
        QueryWrapper<Goodsinfo> wrapper = new QueryWrapper<>();
        wrapper.ge("price", sprice)
                .le("price", eprice)
//                .like(StringUtils.isNotBlank(gname), "gname", gname)
                .eq(tno != null, "tno", tno)
                .orderBy(true, "asc".equalsIgnoreCase(sort), sortby);

        // 执行分页查询
        Page<Goodsinfo> page = new Page<>(pageno, pagesize);
        Page<Goodsinfo> miIndexPage = miGoodsDao.selectPage(page, wrapper);

        // 返回查询结果
        return miIndexPage;
    }

}
