package com.zh.entity;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author Hxx
 */
@Data
public class AlarmRaw {

    public ObjectId _id;


    public String SerialNo;// 序列号


    public String ServerID;// 服务器


    public String TagID;// 变量id


    public String AlarmType;// 报警类型（详见2.5）


    public String AlarmState;// 报警状态 (详见2.1)


    public long EventTime;// 报警时间


    public String EventValue;// 报警值


    public String LimitValue;// 报警限值


    public String AlarmGroup;// 报警组


    public int AlarmPriority;// 报警优先级（详见2.2）


    public String TagName;// 变量名


    public String TagComment;// 备注


    public String AlarmComment;// 报警备注


    public String GroupName;// 报警组名称


    public String Operator;// 操作员


    public String Reviewer;// 批注人


    public long ReviewTime;// 批注时间


    public String ReviewContent;// 批注内容


    public long AckTime;// 应答时间


    public String AckValue;// 应答值


    public String AckOperator;// 应答人


    public long RetTime;// 消失时间


    public String RetValue;// 消失值


    public String RetOperator;// 消失当前人

}