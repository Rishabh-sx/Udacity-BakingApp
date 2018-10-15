package com.rishabh.bakingapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.rishabh.bakingapp.R;


/**
 * Provides convenience methods and abstractions to some tasks in Android
 * <p/>
 * <br/>
 * <br/>
 *
 * @author Rishabh
 */
public class AppUtils {

    private static AppUtils appUtils;
    private ProgressDialog mDialog;

    public static AppUtils getInstance() {
        if (appUtils == null) {
            appUtils = new AppUtils();
        }
        return appUtils;
    }
    /**
     * To show progress dialog for agent while api hit
     *
     * @param context Context of view
     */
    public void showProgressDialog(Context context) {
        hideProgressDialog();
        if (!((Activity) context).isFinishing()) {
            mDialog = new ProgressDialog(context, R.style.MyTheme);
            mDialog.setIndeterminate(true);
            mDialog.setCancelable(false);
            mDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            mDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable
                    .drawable_progress_loader));
            mDialog.show();
        }
    }

    /**
     * hide progress dialog if open
     */
    public void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }


}