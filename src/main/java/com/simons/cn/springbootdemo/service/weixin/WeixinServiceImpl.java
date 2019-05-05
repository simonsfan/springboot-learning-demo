package com.simons.cn.springbootdemo.service.weixin;

import com.simons.cn.springbootdemo.Enum.ConstantEnum;
import com.simons.cn.springbootdemo.Enum.WeiXinEnum;
import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.bean.MovieInvalid;
import com.simons.cn.springbootdemo.bean.MovieRecord;
import com.simons.cn.springbootdemo.controller.BaseController;
import com.simons.cn.springbootdemo.dao.system.MovieInvalidMapper;
import com.simons.cn.springbootdemo.dao.system.MovieMapper;
import com.simons.cn.springbootdemo.dao.system.MovieRecordMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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

    @Autowired
    private MovieRecordMapper recordMapper;

    @Autowired
    private MovieInvalidMapper invalidMapper;

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        String fromUserName = "";
        String toUserName = "";
        try {
            //  {CreateTime=1530965702, EventKey=, Event=subscribe, ToUserName=gh_330c758fa970, FromUserName=ocLpZ0446RKH1FH05Zn2ZbvPF_so, MsgType=event}

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Map<String, String> xmlMap = this.parseXml(request);
            logger.info("解析后的map=" + xmlMap);

            String msgType = xmlMap.get("MsgType");
            fromUserName = xmlMap.get("FromUserName");
            toUserName = xmlMap.get("ToUserName");
            String replymsg = "";
            xmlMap.put("MsgType", WeiXinEnum.MESSAGE_TEXT.getContentType());
            if (WeiXinEnum.MESSAGE_TEXT.getContentType().equals(msgType)) {  //文本类型
                String content = xmlMap.get("Content").trim();  //用户发送的内容
                if(content.indexOf("失效") !=-1){  //说明用户发送的是失效电影内容
                    invalidMapper.insert(new MovieInvalid(content,new Date()));
                    replymsg = appendMsg(xmlMap, ConstantEnum.INVALID.getMsg());
                    response.getWriter().println(replymsg);
                }
                if (content.equals(ConstantEnum.ZIMU.getMsg())) {
                    replymsg = appendMsg(xmlMap, ConstantEnum.ZIMUREPLY.getMsg());
                } else if (content.equals(ConstantEnum.SECRETERROR.getMsg())) {
                    replymsg = appendMsg(xmlMap, ConstantEnum.SECRETERRORREPLY.getMsg());
                } else if (content.equals(ConstantEnum.GUI_ZE.getMsg())) {
                    replymsg = appendMsg(xmlMap, ConstantEnum.GUIZE.getMsg());
                } else {
                    List<Movie> movies = movieMapper.findByName("%"+content + "%");
                    if (CollectionUtils.isNotEmpty(movies)) {
                        for (Movie movie : movies) {
                            replymsg = replymsg + movie.getLink()+"\n\n";
                        }
                    replymsg=appendMsg(xmlMap,replymsg+"11.15 如果资源失效，请看官以 电影名称+失效 的格式回复一下，会第一时间修复哒~\n\n\"求片或者忘记更新，可以添加QQ群：779594107 反馈~");
                    } else {  //未找到匹配项
                        recordMapper.insert(new MovieRecord(content,new Date()));
                        replymsg = appendMsg(xmlMap, ConstantEnum.NOMATCH.getMsg());
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
     * 拼接回复普通消息，注意节点间一定不能有空格存在
     *
     * @param xmlMap
     * @param content
     * @return
     */
    public static String appendMsg(Map xmlMap, String content) {
        String replymsg = "<xml><ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName><FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName><CreateTime>" + xmlMap.get("CreateTime") + "</CreateTime><MsgType><![CDATA[" + xmlMap.get("MsgType") + "]]></MsgType><Content><![CDATA[" + content + "]]></Content></xml>";
        return replymsg;
    }

    /**
     * 动态拼接回复内容
     *
     * @param xmlMap
     * @param content
     * @return  示例：<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>12345678</CreateTime><MsgType>< ![CDATA[news] ]></MsgType><ArticleCount>2</ArticleCount><Articles><item><Title>< ![CDATA[title1] ]></Title> <Description>< ![CDATA[description1] ]></Description><PicUrl>< ![CDATA[picurl] ]></PicUrl><Url>< ![CDATA[url] ]></Url></item><item><Title>< ![CDATA[title] ]></Title><Description>< ![CDATA[description] ]></Description><PicUrl>< ![CDATA[picurl] ]></PicUrl><Url>< ![CDATA[url] ]></Url></item></Articles></xml>
     */
    public static String appendNewsMsg(Map xmlMap, String content){
        String replyMsg = "<xml><ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName><FromUserName><![CDATA["+xmlMap.get("ToUserName") +"]]></FromUserName><CreateTime>"+xmlMap.get("CreateTime") +"</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[常见问题及解决办法]]></Title><Description><![CDATA["+"大家好啊！我是你们的小饭~特别说明：本号资源一切免费。请仔细阅读下面的文字哦，能解决您遇到的部分问题哦"+"]]></Description><PicUrl><![CDATA[http://sr3.pplive.cn/cms/38/91/689e7cb754738d8bb477e449b8ccbea4.jpg]]></PicUrl><Url><![CDATA[]]></Url></item></Articles></xml>";
        return replyMsg;
    }




}
