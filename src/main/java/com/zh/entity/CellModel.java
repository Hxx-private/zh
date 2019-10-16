package com.zh.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CellModel extends CabinetModel {

    public String cellId;

    public String cellName;

    public String cellType;

    public double cellWidth;
}