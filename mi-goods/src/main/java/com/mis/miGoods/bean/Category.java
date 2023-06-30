package com.mis.miGoods.bean;

/**
 * @program: Ycchart
 * @description:
 * @author: 作者 huchaojie
 * @create: 2022-11-06 18:42
 */
public class Category {

    /**
     * 月份
     */
    private  String month;
    /**
     * 销售额
     */
    private  Integer count;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
