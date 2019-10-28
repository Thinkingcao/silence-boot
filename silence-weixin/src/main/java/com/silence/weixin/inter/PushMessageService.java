package com.silence.weixin.inter;

/**
 * <pre>
 * @Description:模板消息推送服务接口
 * @Aouth: cao_wencao
 * @Date: 2019-02-15 10:39
 * </pre>
 */
public interface PushMessageService {


    /**
     * <pre>
     * 订单结算通知
     * @auther: cao_wencao
     * @date: 2019/2/15 10:40
     * </pre>
     */
    public String orderPay(String openId, Object object);
}
