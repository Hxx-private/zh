package com.zh.dao;

import com.zh.entity.BoxModel;
import com.zh.entity.CellModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hxx
 */
@Repository
public interface DataDao {

    /**
     * 获取节全部BOX信息
     * @param cellModel
     * @return
     */
    @Select("SELECT * From f_box WHERE position LIKE @pos;")
    List<BoxModel> getCellBoxes(CellModel cellModel);

}
