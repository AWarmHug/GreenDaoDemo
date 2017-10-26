package com.warm.greendaodemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.greendaodemo.R;
import com.warm.greendaodemo.dao.entity.Teacher;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2017-10-20 14:44
 * 描述：
 */

public class TeacherAdapter extends BaseAdapter<Teacher,TeacherAdapter.ViewHolder> {


    public TeacherAdapter(List<Teacher> list) {
        super(list);
    }

    @Override
    protected void onBind(ViewHolder holder, int position) {
        holder.iv.setImageResource(R.mipmap.ic_launcher);
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_info.setText("我的id是"+list.get(position).getId()+"年纪是"+list.get(position).getAge()+"性别是"+list.get(position).getSex()+"教龄="+(list.get(position).getTeachAge()==null?0: list.get(position).getTeachAge()));
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < list.get(position).getStudents().size(); i++) {
            sb.append("id="+list.get(position).getStudents().get(i).getId());
        }
        holder.tv_student_id.setText(sb.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher,parent,false));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_info)
        TextView tv_info;
        @BindView(R.id.tv_student_id)
        TextView tv_student_id;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
