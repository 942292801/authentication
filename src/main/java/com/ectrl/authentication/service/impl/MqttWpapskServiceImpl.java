package com.ectrl.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ectrl.authentication.commons.ConstantUtil;
import com.ectrl.authentication.commons.RSAUtil;
import com.ectrl.authentication.domain.MqttWpapskEntity;
import com.ectrl.authentication.mapper.MqttWpapskMapper;
import com.ectrl.authentication.service.MqttWpapskService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;




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
        queryWrapper.eq(ConstantUtil.MYSQL_SERVER_NAME,ConstantUtil.SERVER_NAME_AUTHENTI);
        MqttWpapskEntity mqttWpapskEntity = this.getOne(queryWrapper);
        return mqttWpapskEntity;
    }

    @SneakyThrows
    @Override
    public Boolean updateKey() {
        QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ConstantUtil.MYSQL_SERVER_NAME,ConstantUtil.SERVER_NAME_AUTHENTI);
        RSAUtil.KeyPairInfo keyPairInfo = RSAUtil.getKeyPair();
        MqttWpapskEntity mqttWpapskEntity = new MqttWpapskEntity();
        mqttWpapskEntity.setPublicKey(keyPairInfo.getPublicKey());
        mqttWpapskEntity.setPrivateKey(keyPairInfo.getPrivateKey());
        return update(mqttWpapskEntity,queryWrapper);

    }

    @Override
    public String encryptString(String content) throws Exception {
        try {

            //加密
            QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
            //构造数据库语句
            queryWrapper.eq(ConstantUtil.MYSQL_SERVER_NAME,ConstantUtil.SERVER_NAME_AUTHENTI);
            //获取实体类privatekey和publickey
            MqttWpapskEntity mqttWpapskEntity =  this.getOne(queryWrapper);
            //用公钥加密
            return RSAUtil.encipher(content.replaceAll(" +", "+"),mqttWpapskEntity.getPublicKey());
        }catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public String decryptString(String content) throws Exception {
        try {
            //解密
            QueryWrapper<MqttWpapskEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ConstantUtil.MYSQL_SERVER_NAME, ConstantUtil.SERVER_NAME_AUTHENTI);
            //获取实体类privatekey和publickey
            MqttWpapskEntity mqttWpapskEntity = this.getOne(queryWrapper);
            //用私钥解密
            return RSAUtil.decipher(content.replaceAll(" +", "+"), mqttWpapskEntity.getPrivateKey());
        }catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }
    }



}
