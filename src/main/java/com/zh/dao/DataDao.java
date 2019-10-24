package com.zh.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Select("select * from f_document where id=#{doc.boxId} order by doccode limit #{itemStart},#{itemCount};")
    public List<Map<String, Object>> GetDocumentsbyBoxId(long boxId, int pageIndex, int pageItemCount, int tCount);


    @Select("SELECT filepath,savefilename,extension FROM e_record WHERE id = #{id};")
    public  List<Map<String,Object>>  getFilePath(String eDocId);
}