package com.warm.greendaodemo.dao.entity;

import com.warm.greendaodemo.dao.gen.DaoSession;
import com.warm.greendaodemo.dao.gen.ScoreDao;
import com.warm.greendaodemo.dao.gen.StudentDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */
@Entity
public class Student {
    @Id
    long id;

    String name;

    long teacherId;


    @ToOne(joinProperty = "id")
    Score score;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    @Generated(hash = 1752064941)
    private transient Long score__resolvedKey;

    @Generated(hash = 752529600)
    public Student(long id, String name, long teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 307814219)
    public Score getScore() {
        long __key = this.id;
        if (score__resolvedKey == null || !score__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ScoreDao targetDao = daoSession.getScoreDao();
            Score scoreNew = targetDao.load(__key);
            synchronized (this) {
                score = scoreNew;
                score__resolvedKey = __key;
            }
        }
        return score;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1375786680)
    public void setScore(@NotNull Score score) {
        if (score == null) {
            throw new DaoException(
                    "To-one property 'id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.score = score;
            id = score.getId();
            score__resolvedKey = id;
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
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }


}
