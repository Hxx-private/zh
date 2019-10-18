package com.zh.entity;

import lombok.Data;

@Data
public class WhereCondition {
        public String field;

        public WhereConditionMethod method;

        public Object value;
    }