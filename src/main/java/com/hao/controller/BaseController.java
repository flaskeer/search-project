package com.hao.controller;

import com.hao.constant.ResultConst;
import com.hao.entities.PageParams;
import com.hao.entities.ServiceResponse;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2016/3/30.
 */
public abstract class BaseController {

    /**
     * 参数错误的相应结果
     */
    protected static final ServiceResponse BAD_PARAM_RESPONSE = ServiceResponse.createFailResponse(ResultConst.PARAMETER_ERROR,"参数错误");

    protected static final ServiceResponse SERVICE_ERROR_RESPONSE = ServiceResponse.createFailResponse(ResultConst.FAULT,"服务异常");

    /**
     * 构建参数错误的响应
     * @param msg
     * @return
     */
    protected ServiceResponse paramErrorResponse(String msg) {
        return new ServiceResponse(false,ResultConst.PARAMETER_ERROR,null,msg);
    }

    protected PageParams getPageParams(HttpServletRequest request) {
        int page = ServletRequestUtils.getIntParameter(request,"page",1);
        int pageSize = ServletRequestUtils.getIntParameter(request,"limit",10);
        return getPageParams(page,pageSize);
    }

    protected  PageParams getPageParams(int page, int pageSize) {
        return  new PageParams(page,pageSize);
    }


}
