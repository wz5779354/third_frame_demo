package com.letmecook.letmecook.myapplication.bean;

/**
 * Created by gaoshuqing on 2016/10/11.
 */

public class ResultBean<T> {

    private int returnCode;
    private String msg;
    private String secure;
    private T data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int ReturnCode) {
        this.returnCode = ReturnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String Msg) {
        this.msg = Msg;
    }

    public String isSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        this.secure = secure;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
