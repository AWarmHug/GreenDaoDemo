package com.warm.greendaodemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.warm.greendaodemo.adapter.BaseAdapter;
import com.warm.greendaodemo.adapter.TeacherAdapter;
import com.warm.greendaodemo.dao.entity.Score;
import com.warm.greendaodemo.dao.entity.Student;
import com.warm.greendaodemo.dao.entity.Teacher;
import com.warm.greendaodemo.dao.gen.TeacherDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TeacherActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity11";

    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_detele)
    Button btDetele;
    @BindView(R.id.bt_refresh)
    Button btRefresh;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.recy)
    RecyclerView mRecy;

    private TeacherAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        ButterKnife.bind(this);
        mAdapter = new TeacherAdapter(new ArrayList<Teacher>());
        mRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Teacher>() {
            @Override
            public void itemClick(int position, final Teacher teacher) {

                AlertDialog dialog = new AlertDialog.Builder(TeacherActivity.this)
                        .setTitle("选择操作")
                        .setPositiveButton("查看学生详情", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(TeacherActivity.this, StudentActivity.class);
                                intent.putExtra("teacherId", teacher.getId());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("添加学生", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Score score = new Score();
                                score.setChinese(60f);
                                score.setEnglish(80f);
                                score.setMath(90f);
                                Student student = new Student();
                                student.setName("张三的学生");
                                student.setTeacherId(teacher.getId());
                                student.setAge(15);
                                student.setAddress("江苏常州");
//                                student.setTarget("常州大学");
                                addStudent(student,score);
                            }
                        })
                        .create();
                dialog.show();


            }

            @Override
            public void itemLongClick(int position, Teacher teacher) {
                delete(position, teacher);
            }
        });
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        refresh();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, StudentActivity.class));
        return true;
    }

    private void addStudent(Student student, final Score score) {
        Observable.just(student)
                .map(new Function<Student, Long>() {
                    @Override
                    public Long apply(@NonNull Student student) throws Exception {
                        Long id = MyApp.getDaoSession().getStudentDao().insert(student);
                        score.setStudentId(id);
                        MyApp.getDaoSession().getScoreDao().insert(score);
                        return id;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (aLong > 0) {
                            Toast.makeText(TeacherActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @OnClick({R.id.bt_add, R.id.bt_detele, R.id.bt_refresh, R.id.bt_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:

                final Teacher teacher = new Teacher();
                teacher.setName("张三");
                teacher.setAge(24);
                teacher.setSex(1);
                teacher.setTeachAge(2);
                Observable.just(teacher)
                        .map(new Function<Teacher, Long>() {
                            @Override
                            public Long apply(@NonNull Teacher teacher) throws Exception {
                                return MyApp.getDaoSession().getTeacherDao().insert(teacher);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
                                teacher.setId(aLong);
                                mAdapter.insertTopData(teacher);
                            }
                        });
                break;
            case R.id.bt_detele:
                Observable
                        .create(new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                                MyApp.getDaoSession().getTeacherDao().deleteAll();
                                e.onNext(1);
                                e.onComplete();
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(@NonNull Integer integer) throws Exception {
                                mAdapter.removeAll();
                            }
                        });

                break;
            case R.id.bt_refresh:
                refresh();
                break;
            case R.id.bt_query:
                View v = LayoutInflater.from(this).inflate(R.layout.dialog_query, null, false);
                final EditText ed = (EditText) v.findViewById(R.id.query_id);

                AlertDialog mDialog = new AlertDialog.Builder(this)
                        .setView(v)
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                query(Long.parseLong(ed.getText().toString()));
                            }
                        }).create();
                mDialog.show();


                break;
        }
    }


    private void refresh() {
        Observable
                .create(new ObservableOnSubscribe<List<Teacher>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<Teacher>> e) throws Exception {
                        e.onNext(MyApp.getDaoSession().getTeacherDao().queryBuilder().orderDesc(TeacherDao.Properties.Id).build().list());
                        e.onComplete();

                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Teacher>>() {
                    @Override
                    public void accept(@NonNull List<Teacher> teachers) throws Exception {
                        mAdapter.refreshAll(teachers);
                    }
                });
    }


    private void delete(final int position, Teacher teacher) {
        Observable.just(teacher)

                .map(new Function<Teacher, Integer>() {
                    @Override
                    public Integer apply(@NonNull Teacher teacher) throws Exception {
                        MyApp.getDaoSession().getTeacherDao().delete(teacher);
                        return position;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mAdapter.removeItem(position);
                    }
                });

    }

    private void query(long id) {

        Observable.just(id)
                .map(new Function<Long, List<Teacher>>() {
                    @Override
                    public List<Teacher> apply(@NonNull Long aLong) throws Exception {
                        return MyApp.getDaoSession().getTeacherDao().queryBuilder().where(TeacherDao.Properties.Id.eq(aLong)).build().list();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Teacher>>() {
                    @Override
                    public void accept(@NonNull List<Teacher> teachers) throws Exception {
                        if (teachers.size() == 0) {
                            Toast.makeText(TeacherActivity.this, "查询无果", Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.refreshAll(teachers);
                    }
                });

    }


}
