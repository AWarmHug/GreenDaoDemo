package com.warm.greendaodemo.dao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.warm.greendaodemo.dao.gen.DaoMaster;
import com.warm.greendaodemo.dao.gen.StudentDao;
import com.warm.greendaodemo.dao.gen.TeacherDao;

import java.io.File;
import java.io.IOException;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    private static final String TAG = "MySQLiteOpenHelper--";
    public MySQLiteOpenHelper(final Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(new ContextWrapper(context) {
            /**
             * 获得数据库路径，如果不存在，则创建对象对象
             *
             * @param name
             */
            @Override
            public File getDatabasePath(String name) {
                // 判断是否存在sd卡
                boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());
                if (!sdExist) {// 如果不存在,
                    Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
                    return null;
                } else {// 如果存在
                    // 获取sd卡路径
                   File file=new File( context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),name);

                    // 数据库文件是否创建成功
                    boolean isFileCreateSuccess = false;
                    // 判断文件是否存在，不存在则创建该文件
                    if (!file.exists()) {
                        try {
                            isFileCreateSuccess = file.createNewFile();// 创建文件
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        isFileCreateSuccess = true;
                    // 返回数据库文件对象
                    if (isFileCreateSuccess)
                        return file;
                    else
                        return super.getDatabasePath(name);
                }
            }


            /**
             * Android 4.0会调用此方法获取数据库。
             *
             * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
             *      int,
             *      android.database.sqlite.SQLiteDatabase.CursorFactory,
             *      android.database.DatabaseErrorHandler)
             * @param name
             * @param mode
             * @param factory
             * @param errorHandler
             */
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
            }
        }, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: oldVersion="+oldVersion+"newVersion="+newVersion);
        if (oldVersion<3) {
            newVersion3(db);
        }
        if (oldVersion<4){
            newVersion4(db);
        }
        if (oldVersion<5){
            newVersion5(db);
        }
      /*  if (oldVersion<6){
            newVersion6(db);
        }*/

    }

    private void newVersion3(SQLiteDatabase db){
        MigrationHelper.migrate(db,TeacherDao.class);

    }

    private void newVersion4(SQLiteDatabase db) {
        MigrationHelper.migrate(db,StudentDao.class);
    }

    private void newVersion5(SQLiteDatabase db){
        MigrationHelper.migrate(db,StudentDao.class);
    }

    private void newVersion6(SQLiteDatabase db){
        MigrationHelper.migrate(db,StudentDao.class);
    }

}
