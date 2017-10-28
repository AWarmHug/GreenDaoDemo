package com.warm.greendaodemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warm.greendaodemo.R;
import com.warm.greendaodemo.dao.entity.Student;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2017-10-20 17:38
 * 描述：
 */

public class StudentAdapter extends BaseAdapter<Student,StudentAdapter.ViewHolder>{


    public StudentAdapter(List<Student> list) {
        super(list);
    }

    @Override
    protected void onBind(ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvInfo.setText("id="+list.get(position).getId()+"今年"+(list.get(position).getAge()==null?0:list.get(position).getAge())+"岁"+"住址"+(list.get(position).getAddress()==null?"无":list.get(position).getAddress()));
        StringBuffer sb=new StringBuffer("选课为");
        for (int i = 0; i <list.get(position).getSubjects().size(); i++) {
            sb.append(list.get(position).getSubjects().get(i).getName());
        }
        holder.tv_subject.setText(sb.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_subject)
        TextView tv_subject;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
