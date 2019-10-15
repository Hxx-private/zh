package com.zh.entity;

import lombok.Data;

/**
 * @author Hxx
 */
@Data
public class ResModel {
    // 是否成功
    public boolean isSuccess;
    // 消息
    public String message;
    // 数据
    public Object data;
    // 编码
    public int code;
}