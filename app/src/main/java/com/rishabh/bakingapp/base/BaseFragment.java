package com.rishabh.bakingapp.base;


import android.support.v4.app.Fragment;

import com.rishabh.bakingapp.pojo.FailureResponse;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void showNoNetworkError() {
        ((BaseActivity) getActivity()).showNoNetworkError();
    }

    @Override
    public void showToastLong(String message) {
        ((BaseActivity) getActivity()).showToastLong(message);
    }

    @Override
    public void showSpecificError(FailureResponse failureResponse) {
        ((BaseActivity) getActivity()).showSpecificError(failureResponse);
    }

    @Override
    public void showProgressDialog() {
        if(getActivity()!=null)((BaseActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        if(getActivity()!=null)
        ((BaseActivity) getActivity()).hideProgressDialog();
    }
}
