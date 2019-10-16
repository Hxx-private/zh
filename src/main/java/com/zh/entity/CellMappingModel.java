package com.zh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Hxx
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CellMappingModel extends CellModel {

    public String cellMapString;

    public double totalBoxThick;

    public int totalBoxCount;

}