package com.mis.miGoods.bean;

import java.io.Serializable;

/**
 * @program: mistore
 * @description:
 * @author: 作者
 * @create: 2022-11-12 08:36
 */
public class Goods implements Serializable {
    private String gno;
    private String gname;
    private String tno;
    private String price;
    private String intro;
    private String balance;
    private String pics;
    private String qperied;
    private String descr;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGno() {
        return gno;
    }

    public void setGno(String gno) {
        this.gno = gno;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getQperied() {
        return qperied;
    }

    public void setQperied(String qperied) {
        this.qperied = qperied;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
