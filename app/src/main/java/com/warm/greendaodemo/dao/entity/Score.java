package com.warm.greendaodemo.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：warm
 * 时间：2017-10-20 18:09
 * 描述：
 */
@Entity
public class Score {
    @Id
    long id;

    float chinese;
    float math;
    float english;
    @Generated(hash = 2035604318)
    public Score(long id, float chinese, float math, float english) {
        this.id = id;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }
    @Generated(hash = 226049941)
    public Score() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public float getChinese() {
        return this.chinese;
    }
    public void setChinese(float chinese) {
        this.chinese = chinese;
    }
    public float getMath() {
        return this.math;
    }
    public void setMath(float math) {
        this.math = math;
    }
    public float getEnglish() {
        return this.english;
    }
    public void setEnglish(float english) {
        this.english = english;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }
}
