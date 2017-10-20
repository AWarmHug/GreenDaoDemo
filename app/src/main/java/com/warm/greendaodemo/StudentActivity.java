package com.warm.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.warm.greendaodemo.adapter.BaseAdapter;
import com.warm.greendaodemo.adapter.StudentAdapter;
import com.warm.greendaodemo.dao.entity.Student;
import com.warm.greendaodemo.dao.gen.StudentDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StudentActivity extends AppCompatActivity {


    @BindView(R.id.recy)
    RecyclerView recy;

    private long teacherId;

    private  StudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this);
        teacherId=getIntent().getLongExtra("teacherId",0);
        mAdapter =new StudentAdapter(new ArrayList<Student>());
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Student>() {
            @Override
            public void itemClick(int position, Student student) {
                Toast.makeText(StudentActivity.this, "成绩为"+student.getScore().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void itemLongClick(int position, Student student) {

            }
        });
        recy.setAdapter(mAdapter);
        recy.setLayoutManager(new GridLayoutManager(StudentActivity.this,2));
        refresh();

    }

    private void refresh(){

        Observable.just(MyApp.getDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.TeacherId.eq(teacherId)).build().list())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(@NonNull List<Student> students) throws Exception {
                        mAdapter.refreshAll(students);
                    }
                });
    }
}
