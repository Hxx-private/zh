package com.zh.entity;

import lombok.Data;

@Data
public class CellModel extends CabinetModel {

    public String cellId;

    public String cellName;

    public String cellType;

    public double cellWidth;
}