package com.zh.dao.impl;

import com.zh.dao.DataDao;
import com.zh.entity.BoxModel;
import com.zh.entity.CellModel;
import com.zh.entity.DocumentModel;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DataDaoImpl implements DataDao {


    /**
     * 获取盒全部文档信息
     *
     * @param boxId
     * @param pageIndex
     * @param pageItemCount
     * @param tCount
     * @return
     */
    @Override
    public DocumentModel[] GetDocumentsByBoxId(long boxId, int pageIndex, int pageItemCount, int tCount) {



        return new DocumentModel[0];
    }
}
