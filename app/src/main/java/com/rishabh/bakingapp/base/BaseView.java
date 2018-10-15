package com.rishabh.bakingapp.base;

import com.rishabh.bakingapp.pojo.FailureResponse;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface BaseView {

    void showNoNetworkError();
    void showToastLong(String message);
    void showSpecificError(FailureResponse failureResponse);
    void showProgressDialog();
    void hideProgressDialog();
}
