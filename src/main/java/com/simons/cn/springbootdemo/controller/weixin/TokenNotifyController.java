package com.simons.cn.springbootdemo.controller.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fanrx on 2018/6/29.
 */
@Controller
@RequestMapping("/wx")
public class TokenNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(TokenNotifyController.class);

  /*  @GetMapping("/tokencheck")
    @ResponseBody
    public String tokenCheck(@RequestParam(required = false, name = "signature") String signature,
                             @RequestParam(required = false, name = "timestamp") String timestamp,
                             @RequestParam(required = false, name = "nonce") String nonce,
                             @RequestParam(required = false, name = "echostr") String echostr) {
        logger.info("signature=" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr" + echostr);
        String token = "jkw932465";
        List<String> list = Arrays.asList(token, timestamp, nonce);
        Collections.sort(list);
        String shahex = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));
        logger.info("加密后的签名signature={}", shahex);
        if (signature.equals(shahex)) {
            return echostr;
        }
        return "hello";
    }*/

    @PostMapping("/tokencheck")
    @ResponseBody
    public String tokenCheck(@RequestParam(required = false, name = "ToUserName") String ToUserName,
                             @RequestParam(required = false, name = "FromUserName") String FromUserName,
                             @RequestParam(required = false, name = "CreateTime") String CreateTime,
                             @RequestParam(required = false, name = "MsgType") String MsgType,
                             @RequestParam(required = false, name = "Content") String Content) {
        logger.info("ToUserName=" + ToUserName + ",FromUserName=" + FromUserName + ",CreateTime=" + CreateTime + ",MsgType" + MsgType + ",Content" + Content);
        String replymsg = "<xml> <ToUserName>< ![CDATA[" + ToUserName + "] ]></ToUserName> <FromUserName>< ![CDATA[" + FromUserName + "] ]></FromUserName> <CreateTime>" + CreateTime + "</CreateTime> <MsgType>< ![CDATA[" + MsgType + "] ]></MsgType> <Content>< ![CDATA[你好] ]></Content> </xml>";

        return replymsg;
    }

}
