package com.zh.service.impl;

import com.zh.dao.DataDao;
import com.zh.entity.*;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hxx
 */
@Service
public class DataService {
    @Autowired
    private DataDao dataDao;
    public static BoxModel GetBoxDetailInfoByIdAndPosition(String id, String cellPos) {
        BoxModel resModel = new BoxModel();
        resModel.setBoxId(id);
        DataEntity.dicCellMapping.forEach((key, value) -> {
            if (value.cellMapString.equals(cellPos)) {
                var item = DataEntity.dicCellMapping.get(cellPos);
                resModel.buildingId = item.buildingId;
                resModel.floorId = item.floorId;
                resModel.roomId = item.roomId;
                resModel.cabinetId = item.cabinetId;
                resModel.cellId = item.cellId;
            }
        });


        return resModel;
    }

    public static ResModel GetDocumentsbyBoxId(ListQueryModel query) {
        ResModel res = new ResModel();
        res.setCode(200);
        long boxid = 0;
        int tCount = 0;
        try {

            if (query.whereConditions.length > 0) {
                for (WhereCondition condition : query.whereConditions) {
                    if (condition.field == "boxId" && condition.field != null) {
                        boxid = Long.parseLong(condition.value.toString());
                    }


                    //dataDao.GetDocumentsbyBoxId(boxid,query.pageIndex,query.pageItemCount,tCount);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}