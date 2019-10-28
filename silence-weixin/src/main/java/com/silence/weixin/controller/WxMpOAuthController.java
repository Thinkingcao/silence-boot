package com.silence.weixin.controller;

import com.silence.common.utils.HttpTools;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author cao_wencao
 * @ClassName: WxMpOAuthController
 * @Description: 微信网页授权
 * @date 2018年7月4日 下午1:27:58
 */
@RestController
@RequestMapping("/wechat/oauth")
@Slf4j
public class WxMpOAuthController {

    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/oauthInfo")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String backUrl = request.getParameter("backUrl");
        backUrl = URLEncoder.encode(backUrl, "UTF-8");
        String host = request.getRequestURL().toString();

        host = host.replaceAll("/oauthInfo", "/getInfo") + "?backUrl=" + backUrl;
        // String redirect_uri = URLEncoder.encode(host, "UTF-8");
        // 构建授权URL，方法内会自动encode
        String redirectUri = wxMpService.oauth2buildAuthorizationUrl(host, WxConsts.OAuth2Scope.SNSAPI_USERINFO, "1");
        log.info("redirectUri地址={}", redirectUri);// 日志
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/getInfo")
    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String backUrl = "";
        String code = request.getParameter("code");
        backUrl = request.getParameter("backUrl");
        backUrl = URLDecoder.decode(backUrl, "UTF-8");

        // 通过code换取access token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        String openId = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            // 获取openId
            openId = wxMpOAuth2AccessToken.getOpenId();
            log.info("openId={}", openId);
            //第二个参数为null时默认为zh_CN
            WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openId, null);
            //TODO: 将微信粉丝信息wxMpUser保存到数据库

            log.info("网页授权获取用户信息 ：" + wxMpUser.toString());
            Map<String, Object> params = HttpTools.getUrlParamMap(HttpTools.getUrlParam(backUrl));
            // 重新组装参数
            params.put("curoauth", openId);
            String baseUrl = HttpTools.getUrlBase(backUrl);
            backUrl = baseUrl + "?" + HttpTools.getUrlParamsByMap(params);
            log.info("链接微信授权成功 结束======" + backUrl);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e.getMessage());
            throw new RuntimeException("网页授权失败！");
        }
        response.sendRedirect(backUrl);
    }

}
