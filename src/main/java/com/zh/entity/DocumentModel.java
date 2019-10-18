package com.zh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Dictionary;
import java.util.List;

/**
 * @author Hxx
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class DocumentModel extends BoxModel
    {
        public String documentId ;

        public List<EDocumentModel> eDocs ;

        public Dictionary<String,String> docInfo ;

    }