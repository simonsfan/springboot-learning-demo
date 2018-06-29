package com.simons.cn.springbootdemo.controller;

import com.simons.cn.springbootdemo.bean.ActivitySystemVariable;
import com.simons.cn.springbootdemo.bean.UrlInfo;
import com.simons.cn.springbootdemo.dao.system.ActivitySystemVariableMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：springbootdemo
 * 类名称：IndexController
 * 类描述：测试springboot修改项目属性
 * 创建人：simonsfan
 * 创建时间：2018/6/25 17:33
 */
@Controller
public class IndexController {

    @Autowired
    private UrlInfo urlInfo1;

    @Autowired
    private ActivitySystemVariableMapper activitySystemVariableMapper;

    @GetMapping(value = "/index")
    @ResponseBody
    public String index() {
        ActivitySystemVariable systemVariable = activitySystemVariableMapper.selectByPrimaryKey(1);
        return systemVariable.toString();
    }

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        if (true){
            throw new IllegalArgumentException("我发生异常啦……");
        }
        return "hello";
    }















    @RequestMapping("/")
    public String doDefaultView(Model model, HttpServletResponse response) {
        model.addAttribute("name", "simonsfan");
        return "/index";
    }

}
