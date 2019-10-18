package com.zh.controller;

import com.zh.entity.*;
import com.zh.readexcel.readExcel;
import com.zh.service.impl.DataService;
import com.zh.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Hxx
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class Datacontroller {
    @Autowired
    private DataService dataService;
    @PostMapping(value = "/data/GetCellBoxes", produces = "application/json")
    public static BoxModel[] GetCellBoxes(@RequestBody CellModel cellModel, HttpServletResponse response) throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");

        if (null != DataEntity.cellMapping) {
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

    @PostMapping(value = "/data/GetBoxDetailInfoByIdAndPosition", produces = "application/json")
    public static ResModel GetBoxDetailInfoByIdAndPosition(@RequestBody BoxInfo rawBoxInfo,HttpServletResponse response)
    {
        String token= TokenUtils.getToken("rawBoxInfo.id");
        //设置请求头
        response.setHeader("authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "authorization");
        ResModel res = new ResModel();
        res.setCode(200);

        if (null == rawBoxInfo)
        {
            res.message = "未提供有效的数据";
        }

        if (0==rawBoxInfo.id || null==rawBoxInfo.position)
        {
            System.out.println(rawBoxInfo.position);
            System.out.println(rawBoxInfo.id);
            System.out.println(rawBoxInfo.backwidth);
            System.out.println(rawBoxInfo.boxcode);
            res.message = "必须提供档案盒 id 和 位置信息 (position)";
            return res;
        }

        try
        {
            int len = rawBoxInfo.position.lastIndexOf('-');

            if (rawBoxInfo.position.length() <= 2&& len<0)
            {

                res.message = "位置信息 (position) 格式不正确 [" + rawBoxInfo.position + "]";
                return res;
            }


            String cellPos = rawBoxInfo.position.substring(0,len);

            DataEntity.dicCellMapping.forEach((key,value)->{
                if (!value.cellMapString.equals(cellPos))
                {
                    res.message = "cell 位置信息 (cellMapString) 信息不存在 [" + cellPos + "]";
                }
            });

            res.data = DataService.GetBoxDetailInfoByIdAndPosition(String.valueOf(rawBoxInfo.id), cellPos);
            res.message = "成功获取数据.";
            res.isSuccess = true;
            return res;
        }
        catch (Exception ex)
        {
            res.message = ex.getMessage();
            res.isSuccess = false;
            ex.printStackTrace();
        }

        return res;
    }
}

