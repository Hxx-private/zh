package com.zh.test;

import com.zh.dao.impl.DataDaoImpl;
import com.zh.entity.*;
import com.zh.readexcel.readExcel;
import com.zh.service.impl.DataService;
import com.zh.utils.JwtUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class MyTest {
    @Autowired
    private Alarm alarm;
    @Autowired
    private ListQueryModel query;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CellModel cellModel;
@Autowired
private DataDaoImpl dataDao;
    @Test
    public void Token() {

        //存入JWT的payload中生成token
        Map claims = new HashMap<String, Integer>();
        claims.put("admin_username", 111);
        String subject = "admin";
        String token = null;
        try {
            //该token过期时间为12h
            token = JwtUtils.createJWT(claims, subject, 1000 * 60 * 60 * 12);
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }

        System.out.println("token:" + token);

    }

    @Test
    public void ConvertLongToDateTime() {
        Instant ins = Instant.now(); //默认获取的是UTC时区
        System.out.println(ins);
        System.out.println(ins.toEpochMilli()); //ms

        Instant timestamp = Instant.ofEpochMilli(ins.toEpochMilli());
        ZonedDateTime losAngelesTime = timestamp.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(losAngelesTime.toLocalDateTime());


    }
@Test
    public void test() throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");
    System.out.println("jinll");
        if (null!=DataService.cellMapping) {
        System.out.println("kasih");
        map.forEach((key, value) -> {
            if (value.buildingId == cellModel.buildingId && value.floorId == cellModel.floorId
                    && value.roomId == cellModel.roomId &&
                    value.cabinetId == cellModel.cabinetId &&
                    value.cellId == cellModel.cellId) {
                CellMappingModel mappingModel=value;
                System.out.println("******"+mappingModel);
            }

        });

    }

}

}
