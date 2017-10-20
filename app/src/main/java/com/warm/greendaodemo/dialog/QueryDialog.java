package com.warm.greendaodemo.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.warm.greendaodemo.R;

/**
 * 作者：warm
 * 时间：2017-10-20 16:32
 * 描述：
 */

public class QueryDialog extends AlertDialog {



    protected QueryDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_query, null, false);
        EditText ed= (EditText) view.findViewById(R.id.query_id);
        setContentView(view);
    }
}
