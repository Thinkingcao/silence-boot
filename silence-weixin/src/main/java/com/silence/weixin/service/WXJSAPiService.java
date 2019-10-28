package com.silence.weixin.service;

import com.google.common.collect.Maps;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @desc: 获取微信jsapi签名
 * @auth: cao_wencao
 * @date: 2019/10/28 17:18
 */
@Service
public class WXJSAPiService {

    @Autowired
    private WxMpService wxMpService;

    public Map<String, String> getJSApiData(String url) throws WxErrorException {
        Map<String, String> paramMap = Maps.newHashMap();
        WxJsapiSignature createJsapiSignature = this.wxMpService.createJsapiSignature(url);
        if (null != createJsapiSignature) {
            paramMap.put("appId", createJsapiSignature.getAppId());
            paramMap.put("nonceStr", createJsapiSignature.getNonceStr());
            paramMap.put("timestamp", String.valueOf(createJsapiSignature.getTimestamp()));
            paramMap.put("signature", createJsapiSignature.getSignature());
            paramMap.put("url", createJsapiSignature.getUrl());
        }
        return paramMap;
    }
}
