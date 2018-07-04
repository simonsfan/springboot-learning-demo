package com.simons.cn.springbootdemo.controller.weixin;

import com.simons.cn.springbootdemo.service.Weixin.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fanrx on 2018/6/29.
 */
@Controller
@RequestMapping("/wx")
public class TokenNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(TokenNotifyController.class);

    @Autowired
    private WeixinService weixinService;

    @RequestMapping("/tokencheck")
    public void tokenCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        weixinService.notify(request, response);
    }

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
}
