package com.warm.greendaodemo.dao.entity;

import com.warm.greendaodemo.dao.gen.DaoSession;
import com.warm.greendaodemo.dao.gen.StudentDao;
import com.warm.greendaodemo.dao.gen.SubjectDao;
import com.warm.greendaodemo.dao.gen.TeacherDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */
@Entity(
        //标明GreenDao当前实体属于哪个schema,作用是什么我也不太清楚，一般不设置
//        schema="default_schema",

        //设置是否活跃，true：update/delete/refresh 可以有这几个操作
        active = true,
        //表的名字
        nameInDb = "STUDENT",
        //是否创建该表
        createInDb = true,
        // 是否生成所有属性的构造器
        generateConstructors = true,
        // 是否生成get、set方法
        generateGettersSetters = true
)
public class Student {
    //设置id  autoincrement是否自增
    @Id(autoincrement = true)
    private Long id;

    //@Unique 设置成唯一值
    //如果不设置id，可以通过这个设置索引 并设置unique = true 设置成为主键
    @Index
    private Long stu_num;

    //表中的列明
    @Property(nameInDb = "NAME")
    private String name;

    //不能为null
    @NotNull
    private Long teacherId;
    @ToOne(joinProperty = "teacherId")
    private Teacher teacher;

    private Integer age;

    private String address;

    //Student类中的代码
    @ToMany
    @JoinEntity(
            //中间关系实体
            entity = JoinStudentWithSubject.class,
            //JoinStudentWithSubject.studentId 对应Student的Id
            sourceProperty = "studentId",
            //JoinStudentWithSubject.subjectId 对应Subject的Id
            targetProperty = "subjectId"
    )
    private List<Subject> subjects;

    //设置不存到表中
    @Transient
    private boolean selected;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    @Generated(hash = 155140967)
    private transient Long teacher__resolvedKey;


    @Generated(hash = 538563793)
    public Student(Long id, Long stu_num, String name, @NotNull Long teacherId,
                   Integer age, String address) {
        this.id = id;
        this.stu_num = stu_num;
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

    public Long getStu_num() {
        return this.stu_num;
    }

    public void setStu_num(Long stu_num) {
        this.stu_num = stu_num;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 127814275)
    public Teacher getTeacher() {
        Long __key = this.teacherId;
        if (teacher__resolvedKey == null || !teacher__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeacherDao targetDao = daoSession.getTeacherDao();
            Teacher teacherNew = targetDao.load(__key);
            synchronized (this) {
                teacher = teacherNew;
                teacher__resolvedKey = __key;
            }
        }
        return teacher;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 972512811)
    public void setTeacher(@NotNull Teacher teacher) {
        if (teacher == null) {
            throw new DaoException(
                    "To-one property 'teacherId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.teacher = teacher;
            teacherId = teacher.getId();
            teacher__resolvedKey = teacherId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 147443111)
    public List<Subject> getSubjects() {
        if (subjects == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubjectDao targetDao = daoSession.getSubjectDao();
            List<Subject> subjectsNew = targetDao._queryStudent_Subjects(id);
            synchronized (this) {
                if (subjects == null) {
                    subjects = subjectsNew;
                }
            }
        }
        return subjects;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1744012163)
    public synchronized void resetSubjects() {
        subjects = null;
    }
}
