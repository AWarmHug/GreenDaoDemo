package com.warm.greendaodemo.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;

    private String name;

    private Long teacherId;

    private Integer age;

    private String address;


    @Generated(hash = 2120073720)
    public Student(Long id, String name, Long teacherId, Integer age,
            String address) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.age = age;
        this.address = address;
    }

    @Generated(hash = 1556870573)
    public Student() {
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

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}
