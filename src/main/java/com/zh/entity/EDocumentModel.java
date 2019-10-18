package com.zh.entity;

import lombok.Data;

import java.util.Dictionary;

/**
 * @author Hxx
 */
@Data
public class EDocumentModel
    {
        public String eDocId ;

        public String eDocName ;

        public EDocumentType type ;

        public String src ;

        public Dictionary<String,String> eDocInfo ;

    }