package com.zh.service.impl;

import ch.qos.logback.core.boolex.EvaluationException;
import com.zh.dao.DataDao;
import com.zh.entity.*;
import com.zh.readexcel.readExcel;
import lombok.Data;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Hxx
 */
@Data
@Component
public class DataService {

    public static CellMappingModel[] cellMapping;

    public static Map<Integer, CellMappingModel> dicCellMapping;

    public static TagMappingModel[] tagMapping;

    public static Map<String, TagMappingModel> dicTagDeviceMapping;

    public static Map<String, List<TagMappingModel>> dicDeviceTagMapping;


   /* public BoxModel[] GetCellBoxes(CellModel cellModel) throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");
        if (null != DataService.cellMapping) {
            map.forEach((key, value) -> {
                if (value.buildingId.equals(cellModel.buildingId) && value.floorId == cellModel.floorId
                        && value.roomId.equals(cellModel.roomId) &&
                        value.cabinetId.equals(cellModel.cabinetId) &&
                        value.cellId.equals(cellModel.cellId)) {
                    CellMappingModel mappingModel = value;
                    System.out.println(mappingModel);
                }
                BoxModel boxModel = new BoxModel();
                List<BoxModel> boxModelList=dataDao.getCellBoxes(cellModel);
                Map<String, BoxModel> map1 = (Map<String, BoxModel>) dataDao.getCellBoxes(cellModel);
                boxModel.buildingId = cellModel.buildingId;
                boxModel.floorId = cellModel.floorId;
                boxModel.roomId = cellModel.roomId;
                boxModel.cabinetId = cellModel.cabinetId;
                boxModel.cellId = cellModel.cellId;
                boxModel.thick = 0.05d;
                boxModel.index = -1;
                boxModel.boxInfo = new HashMap<>();
               map1.forEach((key1,value1)->
                       {
                           switch (key1){
                               case "id":
                                   boxModel.boxId = value1.boxId;
                                   break;
                               case "boxcode":
                                   boxModel.boxName = value1.boxName;
                                   break;
                               case "position":
                                   boxModel.index =

                           }
                       }

                       );


        });

    }

        return null;
}
*/

    public static BoxModel GetBoxDetailInfoByIdAndPosition(String id, String cellPos) {
        BoxModel resModel = new BoxModel();
        resModel.setBoxId(id);
        dicCellMapping.forEach((key, value) -> {
            if (value.cellMapString.equals(cellPos)) {
               var item = dicCellMapping.get(cellPos);
                resModel.buildingId = item.buildingId;
                resModel.floorId = item.floorId;
                resModel.roomId = item.roomId;
                resModel.cabinetId = item.cabinetId;
                resModel.cellId = item.cellId;
            }
        });


        return resModel;
    }


}