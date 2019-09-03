package com.silence.web.controller.business;

import com.google.common.collect.Maps;
import com.silence.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @desc:  测试的Controller
 * @author: cao_wencao
 * @date: 2019-08-28 16:48
 */
@Slf4j
@RestController
@Api(value = "业务测试Controller")
@RequestMapping(value = "/business")
public class BusinessContoller {
    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
    @ApiOperation(value = "查询List集合",notes = "查询集合数据",response = AjaxResult.class, httpMethod = "GET")
    public AjaxResult queryList(){
        Map<String,String> mapData = Maps.newHashMap();
        mapData.put("name", "曹");
        mapData.put("sex", "男");
        mapData.put("age","20");
        return AjaxResult.success("查询用户集合成功",mapData);
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @ApiOperation(value = "查询User",notes = "查询用户",response = AjaxResult.class, httpMethod = "GET")
    public String getUser(){
        return "宁浩";
    }
}
