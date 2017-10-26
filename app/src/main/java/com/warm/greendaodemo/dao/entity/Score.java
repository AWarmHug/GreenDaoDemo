package com.warm.greendaodemo.dao.entity;

import com.warm.greendaodemo.dao.gen.DaoSession;
import com.warm.greendaodemo.dao.gen.ScoreDao;
import com.warm.greendaodemo.dao.gen.StudentDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * 作者：warm
 * 时间：2017-10-20 18:09
 * 描述：
 */
@Entity
public class Score {
    @Id(autoincrement = true)
    private  Long id;
    private Float chinese;
    private  Float math;
    private  Float english;

    private Long studentId;
    @ToOne(joinProperty = "studentId")
    private Student student;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 302717168)
    private transient ScoreDao myDao;
    @Generated(hash = 732546871)
    public Score(Long id, Float chinese, Float math, Float english,
            Long studentId) {
        this.id = id;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
        this.studentId = studentId;
    }
    @Generated(hash = 226049941)
    public Score() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Float getChinese() {
        return this.chinese;
    }
    public void setChinese(Float chinese) {
        this.chinese = chinese;
    }
    public Float getMath() {
        return this.math;
    }
    public void setMath(Float math) {
        this.math = math;
    }
    public Float getEnglish() {
        return this.english;
    }
    public void setEnglish(Float english) {
        this.english = english;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    @Generated(hash = 79695740)
    private transient Long student__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 313494093)
    public Student getStudent() {
        Long __key = this.studentId;
        if (student__resolvedKey == null || !student__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            Student studentNew = targetDao.load(__key);
            synchronized (this) {
                student = studentNew;
                student__resolvedKey = __key;
            }
        }
        return student;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 490304967)
    public void setStudent(Student student) {
        synchronized (this) {
            this.student = student;
            studentId = student == null ? null : student.getId();
            student__resolvedKey = studentId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 339145390)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getScoreDao() : null;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                ", studentId=" + studentId +
                '}';
    }
}
