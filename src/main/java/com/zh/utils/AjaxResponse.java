package com.zh.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义响应数据返回格式
 */
@Data
public class AjaxResponse {

    private boolean isSuccess;   //请求是否处理成功
    private int code;          //请求响应状态码（200、400、500）
    private String message;  //请求结果描述信息
    private Object data;  //请求结果数据
    private Map<String, Object> res = new HashMap<>();

    private AjaxResponse() {

    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIssuccess(boolean success) {
        isSuccess = success;
    }

    public static AjaxResponse success() {
        Map<String, Object> result = new HashMap<>();
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIssuccess(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;

    }

    public static AjaxResponse Mapsuccess(Object data) {
        Map<String, Object> result = new HashMap<>();
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIssuccess(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        result.put("isSuccess", resultBean.isSuccess());
        result.put("code", resultBean.getCode());
        result.put("message", resultBean.getMessage());
        result.put("data", data);
        resultBean.setRes(result);
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIssuccess(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse fail() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIssuccess(false);
        resultBean.setCode(500);
        resultBean.setMessage("fail");
        return resultBean;
    }

    public static AjaxResponse fail(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIssuccess(false);
        resultBean.setCode(500);
        resultBean.setMessage("fail");
        resultBean.setData(data);
        return resultBean;
    }


}
