package com.lpt.result;

public class Result {
    //响应码
    private int code;
    private Object object;
    private String message = "";

    public Result(int code, Object object, String message) {
        this.code = code;
        this.object = object;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
