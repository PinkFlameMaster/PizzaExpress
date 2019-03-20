package com.vo;

import java.util.List;

public class RetrunMsg {
    private List data;          //返回的数据 push进 List中
    private boolean isSuccess;  //是否能正确返回数据
    private String errorMsg;    //错误信息

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
