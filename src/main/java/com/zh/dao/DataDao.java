package com.zh.dao;

import com.zh.entity.BoxModel;
import com.zh.entity.CellModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
    BoxModel GetCellBoxes(CellModel cellModel);

    @Select("SELECT substring_index(`position`, '-', 1) cellMapString,round(sum(`backwidth`) / 1000 , 3) totalThick,COUNT(*) totalBoxes FROM f_box GROUP BY cellMapString")
    public static void updateCellTickCache() {

    }

}
