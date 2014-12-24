package com.mundord.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mundord.R;

/**
 * Created by jonathan on 02/12/2014.
 */
public class Dialog_Internet extends AlertDialog {
    public Dialog_Internet(Context context) {
        super(context);
        initDialog();
    }

    public Dialog_Internet(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    public Dialog_Internet(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_internet, null);
        setView(view);

        setCancelable(true);

        TextView txt_message = (TextView) view.findViewById(R.id.txt_message);
    }
}
