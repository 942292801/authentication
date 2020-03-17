package com.ectrl.authentication.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
*@Author: Wen zhenwei
*@date: 2020/3/13 11:49
*@Description:
*@Param:
*@return:
*/
@RestController
@RequestMapping("/user")
public class UserController {

    public final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/import")
    public String addUser() {

        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        long beginTime = System.currentTimeMillis();
        logger.info("请求处理结束，耗时：{}毫秒", (System.currentTimeMillis() - beginTime));    //第一种用法
        logger.info("请求处理结束，耗时：" + (System.currentTimeMillis() - beginTime)  + "毫秒");    //第二种用法
        return "123";

    }

    @RequestMapping("")
    public String getHello() {
        return "hello7";
    }

}
