package com.nik.springcloud.entities;


public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(){

    }
    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
