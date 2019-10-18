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

}

