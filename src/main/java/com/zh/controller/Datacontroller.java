package com.zh.controller;

import com.zh.entity.BoxModel;
import com.zh.entity.CellMappingModel;
import com.zh.entity.CellModel;
import com.zh.readexcel.readExcel;
import com.zh.service.impl.DataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Hxx
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class Datacontroller {
    @PostMapping(value = "/data/GetCellBoxes", produces = "application/json")
    public static BoxModel[] GetCellBoxes(@RequestBody CellModel cellModel, HttpServletResponse response) throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");

        if (null != DataService.cellMapping) {
            map.forEach((key, value) -> {
                if (value.buildingId.equals(cellModel.buildingId) && value.floorId == cellModel.floorId
                        && value.roomId.equals(cellModel.roomId) &&
                        value.cabinetId.equals(cellModel.cabinetId) &&
                        value.cellId.equals(cellModel.cellId)) {
                    CellMappingModel mappingModel = value;
                    System.out.println(mappingModel);
                    if (mappingModel==null){
                        try {
                            throw (new Exception("无法在配置中找到指定CELL, 需检查配置文件."));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
        }
        return null;
    }
}

