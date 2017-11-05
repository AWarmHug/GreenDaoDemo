package com.warm.greendaodemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.warm.greendaodemo.adapter.BaseAdapter;
import com.warm.greendaodemo.adapter.StudentAdapter;
import com.warm.greendaodemo.dao.entity.Score;
import com.warm.greendaodemo.dao.entity.Student;
import com.warm.greendaodemo.dao.gen.ScoreDao;
import com.warm.greendaodemo.dao.gen.StudentDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class StudentActivity extends AppCompatActivity {


    @BindView(R.id.recy)
    RecyclerView recy;

    private long teacherId;

    private StudentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this);
        teacherId = getIntent().getLongExtra("teacherId", 0);
        mAdapter = new StudentAdapter(new ArrayList<Student>());
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Student>() {
            @Override
            public void itemClick(int position, final Student student) {
//                queryScore(student.getId());
                new AlertDialog.Builder(StudentActivity.this)
                        .setTitle("提示")
                        .setMessage("请选择操作")
                        .setNegativeButton("语文+10", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chineseAdd(student.getId());
                            }
                        })
                        .setPositiveButton("查看成绩", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                queryScore(student.getId());

                                Toast.makeText(StudentActivity.this, "成绩为" + student.getScore().toString(), Toast.LENGTH_SHORT).show();

                            }
                        })
                        .create()
                        .show();


            }

            @Override
            public void itemLongClick(int position, Student student) {

            }
        });
        recy.setAdapter(mAdapter);
        recy.setLayoutManager(new GridLayoutManager(StudentActivity.this, 2));
        refresh();


    }

    private void refresh() {

        Observable.just((teacherId != 0 ? (MyApp.getDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.TeacherId.eq(teacherId)).build()) : (MyApp.getDaoSession().getStudentDao().queryBuilder().offset(2).limit(10).build())).list())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(@NonNull List<Student> students) throws Exception {
                        mAdapter.refreshAll(students);
                    }
                });
    }

    private void queryScore(long studentId) {
        Observable.just(MyApp.getDaoSession().getScoreDao().queryBuilder().where(ScoreDao.Properties.StudentId.eq(studentId)).build().unique())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Score>() {
                    @Override
                    public void accept(@NonNull Score score) throws Exception {
                        Toast.makeText(StudentActivity.this, "成绩为" + score.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void chineseAdd(long studentId) {

        Observable.just(MyApp.getDaoSession().getScoreDao().queryBuilder().where(ScoreDao.Properties.StudentId.eq(studentId)).build().unique())
                .map(new Function<Score, Score>() {
                    @Override
                    public Score apply(@NonNull Score score) throws Exception {
                        score.setChinese(score.getChinese() + 10);
                        MyApp.getDaoSession().getScoreDao().update(score);
                        return score;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Score>() {
                    @Override
                    public void accept(@NonNull Score score) throws Exception {
                        Toast.makeText(StudentActivity.this, "加分成功", Toast.LENGTH_SHORT).show();

                    }
                });
    }


}
