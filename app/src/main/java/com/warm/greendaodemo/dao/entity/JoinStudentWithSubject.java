package com.warm.greendaodemo.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：warm
 * 时间：2017-10-28 10:27
 * 描述：
 */
@Entity
public class JoinStudentWithSubject {
    @Id(autoincrement = true)
    private Long id;
    private Long studentId;
    private Long subjectId;
    @Generated(hash = 3618070)
    public JoinStudentWithSubject(Long id, Long studentId, Long subjectId) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
    @Generated(hash = 201007694)
    public JoinStudentWithSubject() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

}
