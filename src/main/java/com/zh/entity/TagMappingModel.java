package com.zh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hxx
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagMappingModel extends DeviceMapping {

    public String tagKey;


    public String tagId;


    public String tagName;


    public String tagUnit;

}