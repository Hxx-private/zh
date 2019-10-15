package com.zh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hxx
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CellMappingModel extends CellModel {

    public String cellMapString;

    public double totalBoxThick;

    public int totalBoxCount;

}