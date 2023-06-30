package com.mis.miGoods.bean;

import java.io.Serializable;

/**
 * @program: xiaomi
 * @description:
 * @author: HUIiii
 * @create: 2022-11-17 12:52
 **/

public class Cartinfo implements Serializable {
    private Integer cno;
    private Integer mno;
    private Integer gno;
    private Integer num;

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getMno() {
        return mno;
    }

    public void setMno(Integer mno) {
        this.mno = mno;
    }

    public Integer getGno() {
        return gno;
    }

    public void setGno(Integer gno) {
        this.gno = gno;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
