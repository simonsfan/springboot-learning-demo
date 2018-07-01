package com.simons.cn.springbootdemo.controller.weixin;

import com.simons.cn.springbootdemo.bean.RequestTextMessage;
import com.simons.cn.springbootdemo.util.ReadxmlByDom;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by fanrx on 2018/6/29.
 */
@Controller
@RequestMapping("/wx")
public class TokenNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(TokenNotifyController.class);

/*    @RequestMapping("/tokencheck")
    @ResponseBody
    public String tokenCheck(@RequestParam(required = false, name = "signature") String signature,
                             @RequestParam(required = false, name = "timestamp") String timestamp,
                             @RequestParam(required = false, name = "nonce") String nonce,
                             @RequestParam(required = false, name = "echostr") String echostr,
                             HttpServletRequest request) throws IOException {
        logger.info("signature=" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr" + echostr);
        String method = request.getMethod();

        String token = "jkw932465";
        List<String> list = Arrays.asList(token, timestamp, nonce);
        Collections.sort(list);
        String shahex = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));
        logger.info("加密后的签名signature={}", shahex);
        return echostr;
        String replymsg = "<xml> <ToUserName>< ![CDATA[" + "simons" + "] ]></ToUserName> <FromUserName>< ![CDATA[" + FromUserName + "] ]></FromUserName> <CreateTime>" + CreateTime + "</CreateTime> <MsgType>< ![CDATA[" + MsgType + "] ]></MsgType> <Content>< ![CDATA[你好] ]></Content> </xml>";
    }*/

    @RequestMapping("/tokencheck")
    @ResponseBody
    public String tokenCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String wxMsgXml = IOUtils.toString(request.getInputStream(), "utf-8");
        logger.info("获取的数据信息>>>>>" + wxMsgXml);
        List<RequestTextMessage> msgList = ReadxmlByDom.getBooks(wxMsgXml);
        RequestTextMessage rtm = msgList.get(0);
        String replymsg = "<xml><ToUserName>" + rtm.getToUserName() + "</ToUserName><FromUserName>" + rtm.getFromUserName() + "</FromUserName><CreateTime>" + rtm.getCreateTime() + "</CreateTime><MsgType>" + rtm.getMsgType() + "</MsgType><Content>你好] </Content></xml>";
        logger.info("返回的数据xml格式="+replymsg);
        return replymsg;
    }


}
