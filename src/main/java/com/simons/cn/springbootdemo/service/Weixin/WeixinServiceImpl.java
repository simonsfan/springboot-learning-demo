package com.simons.cn.springbootdemo.service.Weixin;

import com.simons.cn.springbootdemo.Enum.ConstantEnum;
import com.simons.cn.springbootdemo.Enum.WeiXinEnum;
import com.simons.cn.springbootdemo.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.service.Weixin
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/3 10:11
 */
@Service
public class WeixinServiceImpl extends BaseController implements WeixinService {

    private static final Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        String fromUserName = "";
        String toUserName = "";
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Map<String, String> xmlMap = this.parseXml(request);
            logger.info("解析后的map=" + xmlMap);

            String msgType = xmlMap.get("MsgType");
            fromUserName = xmlMap.get("FromUserName");
            toUserName = xmlMap.get("ToUserName");
            String content = xmlMap.get("Content").trim();  //用户发送的内容
            String replymsg = "";
            xmlMap.put("MsgType", WeiXinEnum.MESSAGE_TEXT.getContentType());
            if (WeiXinEnum.MESSAGE_TEXT.getContentType().equals(msgType)) {  //文本类型
                if(content.equals(ConstantEnum.ZIMU.getMsg())){  //回复字幕

                }


                replymsg = appendMsg(xmlMap, "西部链接: https://pan.baidu.com/s/1c347kIG 密码: 25m6");
            } else if (WeiXinEnum.MESSAGE_EVENT.getContentType().equals(msgType)) {  //取消/关注事件类型
                String event = xmlMap.get("Event");  //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
                if (event.equals("subscribe")) {
                    replymsg = appendMsg(xmlMap, "小福利：打开支付宝首页搜索“516277305”，即可领红包\n1、字母请用小写，汉字请打准确，特殊符号请省略掉。剧名要准确无误。\n\n3、重要：请保证剧名准确无误。中文译名优先，英文尽量不用。繁体不支持，简称不支持。\n\n感谢您的关注哦 么么哒~~");
                }
            } else {
                replymsg = appendMsg(xmlMap, "暂不支持回复此类型消息哦~");
            }
            logger.info("返回的数据xml格式=" + replymsg);
            response.getWriter().println(replymsg);
        } catch (Exception e) {
            logger.error("FromUserName=" + fromUserName + ",ToUserName=" + toUserName + ", token notify exception=" + e);
        }
    }

    /**
     * 拼接回复消息，注意节点间一定不能有空格存在
     *
     * @param xmlMap
     * @param content
     * @return
     */
    public static String appendMsg(Map xmlMap, String content) {
        String replymsg = "<xml><ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName><FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName><CreateTime>" + xmlMap.get("CreateTime") + "</CreateTime><MsgType><![CDATA[" + xmlMap.get("MsgType") + "]]></MsgType><Content><![CDATA[" + content + "]]></Content></xml>";
        return replymsg;
    }


}
