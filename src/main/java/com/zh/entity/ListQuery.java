package com.zh.entity;

import lombok.Data;

/**
 * @author Hxx
 */
@Data
public class ListQuery {
    public int pageIndex;

    public int pageItemCount;

    public WhereCondition[] whereConditions;

    public OrderCondition[] orderConditions;


    class WhereCondition {
        public String field;

        public WhereConditionMethod method;

        public Object value;
    }

    enum WhereConditionMethod {
        EQUAL,
        NOT_EQUAL,
        LIKE,
        LESS_THAN,
        MORE_THAN,
        LESS_OR_EQUAL_THAN,
        MORE_OR_EQUAL_THAN,
        IN,
        BTWEEN_LIST
    }


    class OrderCondition {
        public String field;
        public OrderConditionMethod method;

    }


    enum OrderConditionMethod {
        ASC,
        DESC
    }

}

