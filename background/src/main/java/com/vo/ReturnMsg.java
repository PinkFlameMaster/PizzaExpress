package com.vo;

import java.util.List;

public class ReturnMsg {
    private List data;          //返回的数据 push进 List中
    private String status;  //是否能正确返回数据 ,选填“success”和“failure”
    private String errorMsg;    //错误信息

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
