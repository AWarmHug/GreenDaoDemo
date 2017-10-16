package com.warm.greendaodemo.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */
@Entity
public class Shop {

    public static final int COLLECT=0x110;
    public static final int BUY=0x111;
    

    private String name;
    private String pic;
    private float price;
    private int num;
    private String shoper;
    private int type;

    @Generated(hash = 600746270)
    public Shop(String name, String pic, float price, int num, String shoper,
            int type) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.num = num;
        this.shoper = shoper;
        this.type = type;
    }
    @Generated(hash = 633476670)
    public Shop() {
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getShoper() {
        return this.shoper;
    }
    public void setShoper(String shoper) {
        this.shoper = shoper;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }



}
