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
        holder.tvInfo.setText("id="+list.get(position).getId());

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
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
