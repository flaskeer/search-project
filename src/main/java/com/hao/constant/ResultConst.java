package com.hao.constant;

import com.hao.entities.ServiceResponse;

/**
 * 返回结果code定义
 * Created by user on 2016/3/30.
 */
public class ResultConst {

    private ResultConst() {}

    /**
     * 成功
     */
    public static final int SUCCESS = ServiceResponse.SUCCESS_KEY;

    /**
     * 失败
     */
    public static final int FAULT = ServiceResponse.FAIL_KEY;

    public static final int PARAMETER_ERROR = 100;
}
