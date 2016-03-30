package com.cheche.util;

import com.alibaba.fastjson.JSON;
import com.cheche.entities.ServiceResponse;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * response工具类
 * Created by user on 2016/3/30.
 */
public final class ResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    public static final String REP_KEY_SUCCESS = "success";
    public static final String REP_KEY_CODE = "code";
    public static final String REP_KEY_MSG = "msg";
    public static final String REP_KEY_DATA = "data";

    private ResponseUtil() {}



    /**
     * 将response转为JSON结果写入response
     * @param response
     * @param serviceResponse
     * @return
     */
    public static boolean writeJSONResult(HttpServletResponse response, ServiceResponse serviceResponse) {
        Preconditions.checkNotNull(response);
        String json = result2json(serviceResponse);
        return writeJsonResult(response,json);
    }



    public static String result2json(ServiceResponse serviceResponse) {
        Map<String,Object> map = createResponse(serviceResponse);
        return JSON.toJSONString(map);
    }

    public static Map<String,Object> createResponse(ServiceResponse serviceResponse) {
        Map<String,Object> map = Maps.newLinkedHashMap();
        map.put(REP_KEY_SUCCESS,serviceResponse.isSuccess());
        map.put(REP_KEY_CODE,serviceResponse.getCode());
        map.put(REP_KEY_MSG,serviceResponse.getMsg());
        map.put(REP_KEY_DATA,serviceResponse.getData());
        return map;
    }

    /**
     * 写json字符串到客户端
     * @param response
     * @param json
     * @return
     */
    private static boolean writeJsonResult(HttpServletResponse response, String json) {
        try {
            //支持跨站HTTP请求
            response.addHeader("Access-Control-Allow-Origin","*");
            response.addHeader("Cache-Control","no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(json);
            return true;
        } catch (Exception e) {
            LOGGER.error("writer to response exception",e);
            return false;
        }

    }

}
