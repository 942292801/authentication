package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttDeviceEntity;

public interface MqttDeviceService extends IService<MqttDeviceEntity> {

    /**
     *@Author: Wen zhenwei
     *@date: 2020/3/30 14:14
     *@Description: 设备申请证书
     *@Param: [enCertId加密的信息 sn(16)+mac(12) +crc16, publicKey]
     *@return: java.lang.String
     */
    String applyDeviceCA(String enCertId,String publicKey) throws Exception;
}
