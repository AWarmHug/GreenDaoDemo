package com.warm.greendaodemo.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 作者：warm
 * 时间：2017-10-20 14:36
 * 描述：
 */
@Entity
public class Teacher {
    @Id(autoincrement = true)
    private Long id;
    private  String name;
    private int age;
    private Integer sex;
    private Integer teachAge;
    @Generated(hash = 546605499)
    public Teacher(Long id, String name, int age, Integer sex, Integer teachAge) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.teachAge = teachAge;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Integer getSex() {
        return this.sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getTeachAge() {
        return this.teachAge;
    }
    public void setTeachAge(Integer teachAge) {
        this.teachAge = teachAge;
    }



}
