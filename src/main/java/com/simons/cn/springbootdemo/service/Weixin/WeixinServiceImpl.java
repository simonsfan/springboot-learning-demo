package com.simons.cn.springbootdemo.service.Weixin;

import com.simons.cn.springbootdemo.Enum.ConstantEnum;
import com.simons.cn.springbootdemo.Enum.WeiXinEnum;
import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.controller.BaseController;
import com.simons.cn.springbootdemo.dao.system.MovieMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    @Autowired
    private MovieMapper movieMapper;

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
                if (content.equals(ConstantEnum.ZIMU.getMsg())) {  //回复字幕
                    replymsg = appendMsg(xmlMap, ConstantEnum.ZIMUREPLY.getMsg());
                } else if (content.equals(ConstantEnum.SECRETERROR.getMsg())) {
                    replymsg = appendMsg(xmlMap, ConstantEnum.SECRETERRORREPLY.getMsg());
                } else {
                    List<Movie> movies = movieMapper.findByName(content.trim() + "%");
                    if (CollectionUtils.isNotEmpty(movies)) {
                        for (Movie movie : movies) {
                            replymsg = replymsg + movie.getLink()+"\n\n";
                        }
                        replymsg.substring(0,replymsg.lastIndexOf("\n\n"));
                    replymsg=appendMsg(xmlMap,replymsg);
                    } else {  //未找到匹配项
                        replymsg = appendMsg(xmlMap, ConstantEnum.SUBSCRIBEREPLY.getMsg());
                    }
                }
            } else if (WeiXinEnum.MESSAGE_EVENT.getContentType().equals(msgType)) {  //取消/关注事件类型
                String event = xmlMap.get("Event");  //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
                if (event.equals("subscribe")) {
                    replymsg = appendMsg(xmlMap, ConstantEnum.SUBSCRIBEREPLY.getMsg());
                }
            } else {
                replymsg = appendMsg(xmlMap, ConstantEnum.NOSUPPORTREPLY.getMsg());
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
