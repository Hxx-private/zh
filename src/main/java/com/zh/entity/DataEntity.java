package com.zh.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Hxx
 */
@Data
public class DataEntity {

    public static CellMappingModel[] cellMapping;

    public static Map<Integer, CellMappingModel> dicCellMapping;

    public static TagMappingModel[] tagMapping;

    public static Map<String, TagMappingModel> dicTagDeviceMapping;

    public static Map<String, List<TagMappingModel>> dicDeviceTagMapping;
}
