package com.silence.weixin.controller;

import com.google.common.collect.Maps;
import com.silence.weixin.service.WXJSAPiService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <pre>
 * @author cao_wencao
 * @date 2018年8月13日 下午6:21:27
 * </pre>
 */
@Controller
@RequestMapping("/wechat/jsApi")
@Slf4j
public class WxMpWeChatController{
    @Autowired
    private WxMpService    wxMpService;
    @Autowired
    private WXJSAPiService wXJSAPiService;

    /**
     * 创建调用jsapi时所需要的签名 传入当前网页的URL，不包含#及其后面部分
     * 
     * @author cao_wencao
     * @param
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/getJsapiSignature")
    public Object getJsapiSignature(HttpServletRequest request, HttpServletResponse response, Model model) throws WxErrorException {
        String url = request.getRequestURL().toString();
        log.info("【创建jsapi-URL】 : {}", url);
        Map<String, String> paramMap = wXJSAPiService.getJSApiData(url);
        return paramMap;    
    }
    
    
    /**
     * 获得jsapi_ticket,不强制刷新jsapi_ticket
     * 
     * @author cao_wencao
     * @param
     * @return
     */
    @GetMapping("/getJsapiTicket")
    public @ResponseBody
    Object getJsapiTicket() {
        String jsapiTicket = null;
        Map<String, String> paramMap = Maps.newHashMap();
        try {
            jsapiTicket = this.wxMpService.getJsapiTicket();
            paramMap.put(jsapiTicket, jsapiTicket);
        }
        catch (WxErrorException e) {
            log.error("获取[jsapi_ticket失败]: {}", e.getMessage());
        }
        return paramMap;

    }

}
