package com.letmecook.letmecook.myapplication.net.scheduler;


import com.letmecook.letmecook.myapplication.bean.ResultBean;

/**
 * Created by niejiahuan on 16/8/1.
 */
public class ApiException extends Throwable {
    private int RequestCode = -1;
    private int ReturnCode;
    private String Msg;
    private ResultBean o;

    public ResultBean getO() {
        return o;
    }

    public void setO(ResultBean o) {
        this.o = o;
    }



    public ApiException(int returnCode, String msg) {
        this.ReturnCode = returnCode;
        this.Msg = msg;
    }

    public ApiException(int requestCode, int returnCode, String msg) {
        this.ReturnCode = returnCode;
        this.Msg = msg;
        this.RequestCode = requestCode;
    }

    public ApiException(int returnCode, String msg, ResultBean o) {
        this.ReturnCode = returnCode;
        this.Msg = msg;
        this.o = o;
    }

    public int getRequestCode() {
        return RequestCode;
    }

    public void setRequestCode(int RequestCode) {
        this.RequestCode = RequestCode;
    }

    public void setReturnCode(int ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public void setMsg(String msg) {
        this.Msg = msg;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public String getMsg() {
        return Msg;
    }
}
