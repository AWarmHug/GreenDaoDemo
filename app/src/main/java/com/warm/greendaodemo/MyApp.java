package com.warm.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.warm.greendaodemo.dao.entity.DaoMaster;
import com.warm.greendaodemo.dao.entity.DaoSession;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/18
 * 简介:
 */

public class MyApp extends Application {

    //获取所有Dao的顶级上层，用来负责所有dao的增删改查
    private static DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        setupDb();
    }

    private void setupDb() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
