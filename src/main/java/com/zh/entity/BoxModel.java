package com.zh.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString(callSuper = true)
public class BoxModel extends CellModel {
    public String boxId;

    public String boxName;

    public int index;

    public double thick;

    public Map<String, String> boxInfo;

}