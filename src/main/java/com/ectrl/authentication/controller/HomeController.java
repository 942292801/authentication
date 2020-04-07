package com.ectrl.authentication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(value = "页面跳转和重定向",tags = "页面跳转和重定向")
public class HomeController {

    @RequestMapping(value = "/swagger",method = RequestMethod.GET)
    @ApiOperation(value = "swgger2页面跳转",notes = "swgger2页面跳转,无参数",httpMethod = "GET")
    public String index() {
        System.out.println("swagger-ui.html");
        //重定向
        return "redirect:swagger-ui.html";
    }
}

