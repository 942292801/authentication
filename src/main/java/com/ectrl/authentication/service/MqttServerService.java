package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttServerEntity;

public interface MqttServerService extends IService<MqttServerEntity> {
    /**
     *@Author: Wen zhenwei
     *@date: 2020/3/26 15:36
     *@Description: 服务器申请证书
     *@Param: [enCertId 加密的信息 sn(18)+mac(12) +crc16,, publicKey 公钥]
     *@return: java.lang.String
     */
    String applyServerCA(String enCertId,String publicKey) throws Exception;



}
