package com.simons.cn.springbootdemo.controller.weixin;

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
//    @ResponseBody
    public void tokenCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> xmlMap = parseXml(request);
        logger.info("解析后的map=" + xmlMap);

        String replymsg = "<xml><ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName><FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName><CreateTime>" + xmlMap.get("CreateTime") + "</CreateTime><MsgType><![CDATA[" + xmlMap.get("MsgType") + "]]></MsgType><Content><![CDATA[西部链接: https://pan.baidu.com/s/1c347kIG 密码: 25m6]]></Content></xml>";
        logger.info("返回的数据xml格式=" + replymsg);
        response.getWriter().println(replymsg);
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request 封装了请求信息的HttpServletRequest对象
     * @return map 解析结果
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

}
