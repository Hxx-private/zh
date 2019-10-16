package com.zh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Alarm extends TagMappingModel {
    public String title;
    public String eventTime;
    public String state;
    public int priority;
    public AlarmRaw rawData;

    public Alarm(AlarmRaw rawData, TagMappingModel tagMapping) {
        this.rawData = rawData;
        this.title = rawData.TagName + "（报警类型：" + rawData.AlarmType + " , "
                + "报警值：" + rawData.EventValue + " , "
                + "报警限值：" + rawData.LimitValue + "）";
        //this.eventTime = MongoHelper.ConvertLongToDateTime(rawData.EventTime).ToString("yyyy-MM-dd HH:mm:ss");
        this.state = rawData.AlarmState;
        this.priority = rawData.AlarmPriority;
        this.buildingId = tagMapping.buildingId;
        this.floorId = tagMapping.floorId;
        this.roomId = tagMapping.roomId;
        this.deviceId = tagMapping.deviceId;
        this.deviceName = tagMapping.deviceName;
        this.deviceType = tagMapping.deviceType;
        this.deviceMapString = tagMapping.deviceMapString;
        this.tagId = tagMapping.tagId;
        this.tagKey = tagMapping.tagKey;
        this.tagName = tagMapping.tagName;
        this.tagUnit = tagMapping.tagUnit;

    }

}