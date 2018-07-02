package com.simons.cn.springbootdemo.controller.weixin;

import com.simons.cn.springbootdemo.controller.BaseController;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanrx on 2018/6/29.
 */
@Controller
@RequestMapping("/wx")
public class TokenNotifyController extends BaseController{

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
    }*/

    @RequestMapping("/tokencheck")
    public void tokenCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> xmlMap = this.parseXml(request);
        logger.info("解析后的map=" + xmlMap);

        String replymsg = "<xml><ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName><FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName><CreateTime>" + xmlMap.get("CreateTime") + "</CreateTime><MsgType><![CDATA[" + xmlMap.get("MsgType") + "]]></MsgType><Content><![CDATA[西部链接: https://pan.baidu.com/s/1c347kIG 密码: 25m6]]></Content></xml>";
        logger.info("返回的数据xml格式=" + replymsg);
        response.getWriter().println(replymsg);
    }



}
