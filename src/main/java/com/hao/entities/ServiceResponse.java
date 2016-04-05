package com.hao.entities;

import java.io.Serializable;

/**
 * Created by user on 2016/3/30.
 */
public class ServiceResponse<T> implements Serializable {

    public static final int SUCCESS_KEY = 0;
    public static final int FAIL_KEY = 1;
    private final boolean success;
    private final int code;
    private final T data;
    private final String msg;
    public static final ServiceResponse FAIL_RESPONSE = createFailResponse(1,(String)null);
    public static final ServiceResponse SUCCESS_RESPONSE = createSuccessResponse((Object)null);

    public ServiceResponse(boolean success, int code, T data, String msg) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ServiceResponse<T> createSuccessResponse(T data) {
        return new ServiceResponse<>(true,0,data,(String)null);
    }

    public static <T> ServiceResponse<T> createSuccessResponse(T data,String msg) {
        return new ServiceResponse<>(true,0,data,msg);
    }

    public static <T> ServiceResponse<T> createFailResponse(int code, String msg) {
        return new ServiceResponse(false, code, (Object)null, msg);
    }

    public static <T> ServiceResponse<T> createFailResponse(int code,T data,String msg) {
        return new ServiceResponse<>(false,code,data,msg);
    }

    public static <T> ServiceResponse<T> defaultFailResponse() {
        return FAIL_RESPONSE;
    }

}
