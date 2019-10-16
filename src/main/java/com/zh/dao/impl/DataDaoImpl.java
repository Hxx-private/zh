package com.zh.dao.impl;

import com.zh.dao.DataDao;
import com.zh.entity.BoxModel;
import com.zh.entity.CellModel;

import java.util.List;

public class DataDaoImpl implements DataDao {
    /**
     * 获取节全部BOX信息
     *
     * @param cellModel
     * @return
     */
    @Override
    public List<BoxModel> getCellBoxes(CellModel cellModel) {
        System.out.println("dao*******"+cellModel);
        return (List<BoxModel>) cellModel;
    }
}
