package com.rishabh.bakingapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.pojo.FailureResponse;
import com.rishabh.bakingapp.utils.AppUtils;

import butterknife.ButterKnife;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private RelativeLayout baseContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        baseContainer = findViewById(R.id.base_container);
        setLayout();
        ButterKnife.bind(this);
    }

    private void setLayout() {
        if (getResourceId() != -1) {
            removeLayout();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                View view = inflater.inflate(getResourceId(), null);
                baseContainer.addView(view, layoutParams);
            }
        }
    }

    private void removeLayout() {
        if (baseContainer.getChildCount() >= 1)
            baseContainer.removeAllViews();
    }

    public void addFragment(int layoutResId, BaseFragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null)
            getSupportFragmentManager().beginTransaction()
                    .add(layoutResId, fragment, tag)
                    .commit();
    }

    public void replaceFragment(int layoutResId, BaseFragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(layoutResId, fragment, tag)
                    .commit();
    }

    public void addFragmentWithBackstack(int layoutResId, BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(layoutResId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    protected abstract int getResourceId();


    /**
     * A common place to handle no network error
     * Can show a full screen View, Snackbar with retry action
     * or a simple Toast
     */

    @Override
    public void showNoNetworkError() {
        showToastLong(getString(R.string.no_network_error));
    }

    @Override
    public void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method helps both ways
     * Using this generic handling as well as Specific handling can be done
     *
     * @param failureResponse contains errorCode
     *                        which can decide what kind of handling can be done
     */

    @Override
    public void showSpecificError(FailureResponse failureResponse) {
        String message = (failureResponse != null) ? failureResponse.getMsg() : getString(R.string.something_went_wrong);
        showToastLong(message);
    }

    @Override
    public void showProgressDialog() {
        AppUtils.getInstance().showProgressDialog(this);

    }

    @Override
    public void hideProgressDialog() {

        AppUtils.getInstance().hideProgressDialog();
    }
}
