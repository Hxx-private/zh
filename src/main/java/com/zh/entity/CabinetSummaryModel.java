package com.zh.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hxx
 */
@Data
@ToString(callSuper = true)
public class CabinetSummaryModel extends CabinetModel
    {
        public double totalThick ;
        
        public double totalWidth ;
        
        public int totalBoxCount ;
        
    }