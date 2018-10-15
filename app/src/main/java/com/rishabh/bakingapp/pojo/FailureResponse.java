package com.rishabh.bakingapp.pojo;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class FailureResponse {
    private int errorCode;
    private String msg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
