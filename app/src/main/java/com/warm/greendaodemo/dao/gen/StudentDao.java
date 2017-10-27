package com.warm.greendaodemo.dao.gen;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.warm.greendaodemo.dao.entity.Student;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STUDENT".
*/
public class StudentDao extends AbstractDao<Student, Long> {

    public static final String TABLENAME = "STUDENT";

    /**
     * Properties of entity Student.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Stu_num = new Property(1, Long.class, "stu_num", false, "STU_NUM");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property TeacherId = new Property(3, Long.class, "teacherId", false, "TEACHER_ID");
        public final static Property Age = new Property(4, Integer.class, "age", false, "AGE");
        public final static Property Address = new Property(5, String.class, "address", false, "ADDRESS");
    }

    private DaoSession daoSession;

    private Query<Student> teacher_StudentsQuery;

    public StudentDao(DaoConfig config) {
        super(config);
    }
    
    public StudentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STU_NUM\" INTEGER," + // 1: stu_num
                "\"NAME\" TEXT," + // 2: name
                "\"TEACHER_ID\" INTEGER NOT NULL ," + // 3: teacherId
                "\"AGE\" INTEGER," + // 4: age
                "\"ADDRESS\" TEXT);"); // 5: address
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_STUDENT_STU_NUM ON STUDENT" +
                " (\"STU_NUM\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STUDENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long stu_num = entity.getStu_num();
        if (stu_num != null) {
            stmt.bindLong(2, stu_num);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getTeacherId());
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long stu_num = entity.getStu_num();
        if (stu_num != null) {
            stmt.bindLong(2, stu_num);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getTeacherId());
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
    }

    @Override
    protected final void attachEntity(Student entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Student readEntity(Cursor cursor, int offset) {
        Student entity = new Student( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // stu_num
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.getLong(offset + 3), // teacherId
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // age
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // address
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Student entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStu_num(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTeacherId(cursor.getLong(offset + 3));
        entity.setAge(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setAddress(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Student entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Student entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Student entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "students" to-many relationship of Teacher. */
    public List<Student> _queryTeacher_Students(Long teacherId) {
        synchronized (this) {
            if (teacher_StudentsQuery == null) {
                QueryBuilder<Student> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TeacherId.eq(null));
                teacher_StudentsQuery = queryBuilder.build();
            }
        }
        Query<Student> query = teacher_StudentsQuery.forCurrentThread();
        query.setParameter(0, teacherId);
        return query.list();
    }

}
