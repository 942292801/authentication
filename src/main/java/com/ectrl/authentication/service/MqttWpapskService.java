package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttWpapskEntity;

public interface MqttWpapskService extends IService<MqttWpapskEntity> {

    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/17 11:44
    *@Description: 获取服务器公私钥
    *@Param: []
    *@return: com.ectrl.authentication.domain.MqttWpapskEntity
    */
    MqttWpapskEntity getKey();

    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/17 11:23
    *@Description: 更新公钥私钥
    *@Param: [server_name] 服务器名称
    *@return: java.lang.Boolean
    */
    Boolean updateKey();


    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/26 13:53
    *@Description: RSA加密 数据库获取publickey
    *@Param: [msg]
    *@return: java.lang.String
    */
    String encryptString(String content) throws Exception;

    /**
    *@Author: Wen zhenwei
    *@date: 2020/3/26 14:04
    *@Description: RSA解密 数据库获取privatekey
    *@Param: [content 加密内容]
    *@return: java.lang.String
    */
    String decryptString(String content) throws Exception;



}
