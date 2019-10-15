package com.zh.entity;

import lombok.Data;

/**
 * @author Hxx
 */
@Data
public class PagedData {
    public int pageIndex;
    public int pageItemCount;
    public int totalCount;
    public Object data;
}