/*
package com.zh.service.impl;

import com.zh.entity.CabinetModel;
import com.zh.entity.CellMappingModel;
import com.zh.entity.TagMappingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.zh.dao.DataDao.updateCellTickCache;

@Service
public class DataService {
    public static CellMappingModel[] cellMapping;

    public static Map<String, CellMappingModel> dicCellMapping;

    public static TagMappingModel[] tagMapping;

    public static Map<String, TagMappingModel> dicTagDeviceMapping;

    public static Map<String, List<TagMappingModel>> dicDeviceTagMapping;

    public static CellMappingModel[] GetCellSummary(CabinetModel cabinetModel) throws Exception {
        updateCellTickCache();


        return cellMapping.equals(a -> a.buildingId == cabinetModel.buildingId && a.floorId == cabinetModel.floorId
                && a.roomId == cabinetModel.roomId &&
                a.cabinetId == cabinetModel.cabinetId)
                .ToArray();
    }
}

*/
