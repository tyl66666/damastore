package com.mis.miOrder.model;


import com.mis.bean.Goodsinfo;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Goodsinfo goodsinfo;
    private Integer num;
    private double smallCount;

    public Double getSmallCount() {
        if (goodsinfo != null) {
            //goodsinfo.getPrice()的结果是个String类型 先转换为Integer类型
            Double i = Double.parseDouble(this.goodsinfo.getPrice());
            smallCount = i * this.num;
        }
        return smallCount;
    }

    public Goodsinfo getGoodsinfo() {
        return goodsinfo;
    }

    public void setGoodsinfo(Goodsinfo goodsinfo) {
        this.goodsinfo = goodsinfo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "goodsinfo=" + goodsinfo +
                ", num=" + num +
                ", smallCount=" + smallCount +
                '}';
    }
}
