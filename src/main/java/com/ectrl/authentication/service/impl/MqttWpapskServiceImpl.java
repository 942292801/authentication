package com.ectrl.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ectrl.authentication.commons.RSAUtil;
import com.ectrl.authentication.domain.MqttWpapskEntity;
import com.ectrl.authentication.mapper.MqttWpapskMapper;
import com.ectrl.authentication.service.MqttWpapskService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
*@Author: Wen zhenwei
*@date: 2020/3/17 10:30
*@Description: RSA数据库操作
*@Param:
*@return:
*/
@Service
public class MqttWpapskServiceImpl extends ServiceImpl<MqttWpapskMapper, MqttWpapskEntity> implements MqttWpapskService {


    @Override
    public MqttWpapskEntity getKey() {
        //条件构造器
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name","authenti");
        MqttWpapskEntity mqttWpapskEntity = this.getOne(queryWrapper);
        return mqttWpapskEntity;


    }

    @SneakyThrows
    @Override
    public Boolean updateKey() {
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name","authenti");
        RSAUtil.KeyPairInfo keyPairInfo = RSAUtil.getKeyPair();
        MqttWpapskEntity mqttWpapskEntity = new MqttWpapskEntity();
        mqttWpapskEntity.setPublicKey(keyPairInfo.getPublicKey());
        mqttWpapskEntity.setPrivateKey(keyPairInfo.getPrivateKey());
        return update(mqttWpapskEntity,queryWrapper);

    }

    @Override
    public String encryptString(String content) throws Exception {
        //加密
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        //构造数据库语句
        queryWrapper.eq("server_name","authenti");
        //获取实体类privatekey和publickey
        MqttWpapskEntity mqttWpapskEntity =  this.getOne(queryWrapper);
        //用公钥加密
        return RSAUtil.encipher(content,mqttWpapskEntity.getPublicKey());
    }

    @Override
    public String decryptString(String content) throws Exception {
        //解密
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_name","authenti");
        //获取实体类privatekey和publickey
        MqttWpapskEntity mqttWpapskEntity =  this.getOne(queryWrapper);
        //用私钥解密
        String str = RSAUtil.decipher(content.replaceAll(" +","+"),mqttWpapskEntity.getPrivateKey());
        return str;
    }







}
