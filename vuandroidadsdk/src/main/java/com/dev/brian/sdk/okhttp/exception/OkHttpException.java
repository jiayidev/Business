package com.dev.brian.sdk.okhttp.exception;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description: 自定义异常类，返回ecode，emsg到业务逻辑
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    private int ecode;
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getCode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
