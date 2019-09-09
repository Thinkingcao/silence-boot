package com.silence.web.controller.business;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.silence.common.core.domain.AjaxResult;
import com.silence.weixin.config.WxMpProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @desc: 测试的Controller
 * @author: cao_wencao
 * @date: 2019-08-28 16:48
 */
@Slf4j
@RestController
@Api(value = "业务测试Controller")
@RequestMapping(value = "/business")
public class BusinessContoller {

    @Autowired
    private WxMpProperties wxMpProperties;

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ApiOperation(value = "查询List集合", notes = "查询集合数据", response = AjaxResult.class, httpMethod = "GET")
    public AjaxResult queryList() {
        Map<String, String> mapData = Maps.newHashMap();
        mapData.put("name", "曹");
        mapData.put("sex", "男");
        mapData.put("age", "20");
        return AjaxResult.success("查询用户集合成功", mapData);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ApiOperation(value = "查询User", notes = "查询用户", response = AjaxResult.class, httpMethod = "GET")
    public String getUser() {
        return "宁浩";
    }

    @RequestMapping(value = "/getWxConfig", method = RequestMethod.GET)
    @ApiOperation(value = "查询微信配置", notes = "查询微信数据", response = AjaxResult.class, httpMethod = "GET")
    public AjaxResult getWxConfig() {
        Map<String, String> mapData = Maps.newHashMap();
        List<Map>  result = Lists.newArrayList();
        final List<WxMpProperties.MpConfig> configs = this.wxMpProperties.getConfigs();
        for (WxMpProperties.MpConfig config : configs) {
            mapData.put("appId", config.getAppId());
            mapData.put("secret", config.getSecret());
            mapData.put("token", config.getToken());
            mapData.put("aesKey", config.getAesKey());
            result.add(mapData);
        }
        return AjaxResult.success("查询微信配置成功", result);
    }
}
