package com.zh.dao;

import com.zh.entity.DocumentModel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Hxx
 */
@Repository
public interface DataDao {

    /**
     * 获取盒全部文档信息
     *
     * @param boxId
     * @param pageIndex
     * @param pageItemCount
     * @param tCount
     * @return
     */

    @Select("select * from f_document where boxid=@boxid order by doccode limit @itemStart,@itemCount;")
    @Results(id = "doc", value = {
            @Result(property = "boxid", column = ""),
            @Result(property = "", column = ""),
            @Result(property = "", column = ""),
            @Result(property = "", column = ""),
            @Result(property = "", column = ""),

            })
    public DocumentModel[] GetDocumentsByBoxId(long boxId, int pageIndex, int pageItemCount, int tCount);


}
