package com.ectrl.authentication.controller;

import com.ectrl.authentication.domain.MqttWpapskEntity;
import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.dto.MqttWpapskDTO;
import com.ectrl.authentication.service.MqttWpapskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
*@Author: Wen zhenwei
*@date: 2020/3/13 11:51
*@Description: 获取RSA公私钥 更新密钥
*@Param:
*@return:
*/
@RestController
@RequestMapping("/request")
@Api(value = "Wpapsk信息安全的相关接口",tags = "Wpapsk信息安全的相关接口")
public class WpapskController {

    @Autowired
    private MqttWpapskService mqttWpapskService;

    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/26 9:58
    *@Description: 获取公钥
    *@Param: []
    *@return: com.ectrl.authentication.dto.BaseResult
    */
    @RequestMapping(value = "/publickey",method = RequestMethod.GET)
    @ApiOperation(value = "获取RSA公钥",notes = "获取该服务器RSA公钥信息,无参数",httpMethod = "GET")
    public BaseResult getPublicKey(){
        MqttWpapskEntity mqttWpapskEntity = mqttWpapskService.getKey();
        if (mqttWpapskEntity == null){
            return BaseResult.fail("获取公钥失败");
        }
        else {
            MqttWpapskDTO mqttWpapskDTO = new MqttWpapskDTO();
            BeanUtils.copyProperties(mqttWpapskEntity,mqttWpapskDTO);
            return BaseResult.success("获取公钥成功",mqttWpapskDTO);
        }
    }


    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/26 9:59
    *@Description: 更新替换公私钥  一般不开放
    *@Param: []
    *@return: com.ectrl.authentication.dto.BaseResult
    */
    /*@RequestMapping("/updatekey")
    public BaseResult setKey()  {
        if(mqttWpapskService.updateKey()){
            return BaseResult.success("更新公钥成功");

        }else {
            return BaseResult.fail("更新公钥失败");
        }

    }*/


    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/30 10:16
    *@Description: 加密数据
    *@Param: [content]
    *@return: com.ectrl.authentication.dto.BaseResult
    */
    @RequestMapping(value ="/encrypt" ,method = RequestMethod.POST)
    @ApiOperation(value = "RSA公钥加密数据",notes = "该服务器RSA公钥加密数据,参数content：需加密内容",httpMethod = "POST")
    public BaseResult encryptStr(@RequestParam(value = "content") String content) throws Exception {
        String encryptStr = mqttWpapskService.encryptString(content);
        if (StringUtils.isEmpty(encryptStr) ){
            return BaseResult.fail("加密数据失败");

        }else {
            return BaseResult.success("加密数据成功",encryptStr);

        }
    }

    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/30 10:16
    *@Description: 解密数据
    *@Param: [content]
    *@return: com.ectrl.authentication.dto.BaseResult
    */
    @RequestMapping(value = "/decrypt",method = RequestMethod.POST)
    @ApiOperation(value = "RSA公钥解密数据",notes = "该服务器RSA公钥解密数据,参数content：需解密内容",httpMethod = "POST")
    public BaseResult decryptStr(@RequestParam(value = "content") String content ) throws Exception {
        String encryptStr = mqttWpapskService.decryptString(content);
        if (StringUtils.isEmpty(encryptStr)){
            return BaseResult.fail("解密数据失败");

        }else
        {
            return BaseResult.success("解密数据成功",encryptStr);

        }
    }


}
