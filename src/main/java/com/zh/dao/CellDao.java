package com.zh.dao;

import com.zh.entity.CellMappingModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CellDao {
    @Select(value = "select * from f_box where f_box.position like '${cell.cellMapString}%'")
    public List<Map<String,Object>> getCellBoxes(@Param("cell") CellMappingModel cellMapString);
    
    
    @Select("SELECT id,boxcode,position,backwidth From f_box WHERE position LIKE '${cell.cellMapString}%'")
    @Results(id="BoxMap", value={
            @Result(column="id", property="boxId"),
            @Result(column="boxcode", property="boxName"),
            @Result(column="position", property="index"),
            @Result(column="backwidth", property="thick")

    })
    public List<Map<String,Object>> getCellBoxestoBoxModelS(@Param("cell")CellMappingModel cellMapString);
}
