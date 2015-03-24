package com.cml.wodi.util;

import android.content.Context;


import com.cml.wodi.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by teamlab on 2015/3/6.
 */
public class DialogUtil {
    public static SweetAlertDialog showExitDialog(Context context, SweetAlertDialog.OnSweetClickListener cancelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText(context.getString(R.string.system_exit_confirm)).
                setContentText(null)
                .setCancelText(context.getString(R.string.cancel))
                .setConfirmText(context.getString(R.string.confirm))
                .showCancelButton(true)
                .setCancelClickListener(cancelListener)
                .setConfirmClickListener(confirmListener).show();
        return dialog;
    }
}
