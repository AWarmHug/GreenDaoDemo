package com.warm.greendaodemo.dao.entity;

import com.warm.greendaodemo.dao.gen.DaoSession;
import com.warm.greendaodemo.dao.gen.StudentDao;
import com.warm.greendaodemo.dao.gen.SubjectDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * 作者：warm
 * 时间：2017-10-28 10:22
 * 描述：
 */
@Entity
public class Subject {
    @Id(autoincrement = true)
    private Long id;
    private String name;

    @ToMany
    @JoinEntity(entity = JoinStudentWithSubject.class,
            sourceProperty = "subjectId",
            targetProperty = "studentId"
    )
    private List<Student> students;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1644932788)
    private transient SubjectDao myDao;

    @Generated(hash = 1290231420)
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1617906264)
    public Subject() {
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1209150692)
    public List<Student> getStudents() {
        if (students == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentsNew = targetDao._querySubject_Students(id);
            synchronized (this) {
                if (students == null) {
                    students = studentsNew;
                }
            }
        }
        return students;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 238993120)
    public synchronized void resetStudents() {
        students = null;
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
    @Generated(hash = 937984622)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSubjectDao() : null;
    }

}
