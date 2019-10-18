package com.zh.entity;

import lombok.Data;

/**
 * @author Hxx
 */
@Data
public class ListQueryModel {
    public int pageIndex;

    public int pageItemCount;

    public WhereCondition[] whereConditions;

    public OrderCondition[] orderConditions;

    @Data
    static class WhereCondition {
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

    @Data
    class OrderCondition {
        public String field;
        public OrderConditionMethod method;

    }


    enum OrderConditionMethod {
        ASC,
        DESC
    }

}

