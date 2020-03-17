package com.ectrl.authentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ectrl.authentication.commons.RSAEncrypt;
import com.ectrl.authentication.domain.MqttWpapskEntity;
import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.dto.MqttWpapskDTO;
import com.ectrl.authentication.service.MqttWpapskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
*@Author: Wen zhenwei
*@date: 2020/3/13 11:51
*@Description: 获取RSA公私钥
*@Param:
*@return:
*/
@RestController
@RequestMapping("/request")
public class WpapskController {

    @Autowired
    private MqttWpapskService mqttWpapskService;

    @RequestMapping("/publickey")
    public BaseResult getPublicKey(@RequestParam(value = "server_name") String name){

        //条件构造器
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name",name);
        MqttWpapskEntity mqttWpapskEntity = mqttWpapskService.getOne(queryWrapper);
        if (mqttWpapskEntity == null){
            return BaseResult.fail("获取"+name+"公钥失败");
        }
        else {
            MqttWpapskDTO mqttWpapskDTO = new MqttWpapskDTO();
            BeanUtils.copyProperties(mqttWpapskEntity,mqttWpapskDTO);
            return BaseResult.success("获取"+name+"公钥成功",mqttWpapskDTO);
        }
    }

    @RequestMapping("/setkey")
    public BaseResult setKey(@RequestParam(value = "server_name") String name) throws NoSuchAlgorithmException {
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name",name);
        Map<Integer,String> map = RSAEncrypt.genKeyPair();
        MqttWpapskEntity mqttWpapskEntity = new MqttWpapskEntity();
        mqttWpapskEntity.setPublicKey(map.get(0));
        mqttWpapskEntity.setPrivateKey(map.get(1));
        if(mqttWpapskService.update(mqttWpapskEntity,queryWrapper)){
            return BaseResult.success("设置"+name+"公钥成功",mqttWpapskEntity);

        }else {
            return BaseResult.fail("设置"+name+"公钥失败");
        }

    }

    @RequestMapping("/encrypt")
    public BaseResult encryptString(@RequestParam(value = "server_name") String name) throws Exception {
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name",name);
        MqttWpapskEntity mqttWpapskEntity =  mqttWpapskService.getOne(queryWrapper);
        String messageEn = RSAEncrypt.encrypt("www.baidu.com",mqttWpapskEntity.getPublicKey());
        String messageDe = RSAEncrypt.decrypt(messageEn,mqttWpapskEntity.getPrivateKey());


        if(mqttWpapskService.update(mqttWpapskEntity,queryWrapper)){
            return BaseResult.success(name+"公钥加密成功","加密后的字符串为:" + messageEn + "\r\n还原后的字符串为:" + messageDe);

        }else {
            return BaseResult.fail(name+"公钥加密成功");
        }

    }

}
