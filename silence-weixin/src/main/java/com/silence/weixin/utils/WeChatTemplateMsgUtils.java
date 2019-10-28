package com.silence.weixin.utils;

import com.silence.weixin.inter.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description: 微信模板消息推送实现
 * @Aouth: cao_wencao
 * @Date: 2019-01-24 14:07
 * </pre>
 */
@Slf4j
@Component
public class WeChatTemplateMsgUtils implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;

    private static String wx_template_id = "消息推送模板ID可从配置参数读取";

    //合作投稿审核通知，发送模板消息
    @Override
    public String orderPay(String openId, Object object) {
        //实例化模板对象
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        //设置模板ID
        wxMpTemplateMessage.setTemplateId(wx_template_id);
        //设置发送给哪个用户
        wxMpTemplateMessage.setToUser(openId);
        //构建消息格式
        List<WxMpTemplateData> listData = Arrays.asList(
                new WxMpTemplateData("first", "恭喜你支付成功！", "#173177"),
                new WxMpTemplateData("keyword1", "曹.", "#173177"),
                new WxMpTemplateData("keyword2", "2019698571200", "#173177"),
                new WxMpTemplateData("keyword3", "￥98.80", "#173177"),
                new WxMpTemplateData("keyword4", "星冰乐（焦糖味） 家乐氏香甜玉米片*2 乐天七彩爱情糖*3", "#173177"),
                new WxMpTemplateData("remark", "如有疑问，请联系客服电话：021-54145323", "#173177")
        );
        //接收发送模板消息结果,就是msgid，if(msgid! = null)即成功
        String wxTemplateResult = null;
        //放进模板对象。准备发送
        wxMpTemplateMessage.setData(listData);
        try {
            //发送模板
            wxTemplateResult = wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("发送模板消息异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return wxTemplateResult;
    }

    /**
     * <pre>
     * 设置所属行业
     * @auther: cao_wencao
     * @date: 2019/2/18 15:17
     * </pre>
     */
    public boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
        Boolean flag = wxMpService.getTemplateMsgService().setIndustry(wxMpIndustry);
        return flag;
    }

    /**
     * <pre>
     * 获取设置的行业信息
     * @auther: cao_wencao
     * @date: 2019/2/18 15:21
     * </pre>
     */
    public WxMpTemplateIndustry getIndustry() throws WxErrorException {
        WxMpTemplateIndustry wxMpTemplateIndustry = wxMpService.getTemplateMsgService().getIndustry();
        return wxMpTemplateIndustry;
    }

    /**
     * <pre>
     * 发送模板消息
     * @auther: cao_wencao
     * @date: 2019/2/18 15:25
     * </pre>
     */
    public String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException {
        String result = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        return result;
    }


    /**
     * <pre>
     * 获得模板ID
     * shortTemplateId: 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @auther: cao_wencao
     * @date: 2019/2/18 15:29
     * </pre>
     */
    public String addTemplate(String shortTemplateId) throws WxErrorException {
        String result = wxMpService.getTemplateMsgService().addTemplate(shortTemplateId);
        return result;
    }

    /**
     * <pre>
     * 获得模板列表
     * @auther: cao_wencao
     * @date: 2019/2/18 15:30
     * </pre>
     */
    List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException {
        List<WxMpTemplate> templateList = wxMpService.getTemplateMsgService().getAllPrivateTemplate();
        return templateList;
    }

    /**
     * <pre>
     * 删除模板
     * templateId: 公众帐号下模板消息ID
     * @auther: cao_wencao
     * @date: 2019/2/18 15:32
     * </pre>
     */
    boolean delPrivateTemplate(String templateId) throws WxErrorException {
        Boolean flag = wxMpService.getTemplateMsgService().delPrivateTemplate(templateId);
        return flag;
    }

}
