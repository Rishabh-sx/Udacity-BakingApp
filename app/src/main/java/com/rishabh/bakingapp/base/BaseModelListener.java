package com.rishabh.bakingapp.base;

import com.rishabh.bakingapp.pojo.FailureResponse;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface BaseModelListener {
    void noNetworkError();
    void onErrorOccurred(FailureResponse failureResponse);
}