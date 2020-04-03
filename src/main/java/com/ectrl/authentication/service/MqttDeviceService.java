package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttDeviceEntity;
import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.dto.ResourceDTO;

import java.util.List;

public interface MqttDeviceService extends IService<MqttDeviceEntity> {

    /**
     *@Author: Wen zhenwei
     *@date: 2020/3/30 14:14
     *@Description: 设备申请证书
     *@Param: [enCA加密的信息 数字证书, publicKey]
     *@return: 状态码 ：+ 账户+密码+通道号+List<主服务器certid>
     */
    BaseResult authentiDeviceCA(String enCA, String publicKey) throws Exception;


    /**
    *@Author: Wen zhenwei
    *@date: 2020/4/2 10:22
    *@Description: 查询的所有等于 userid 的 设备
    *@Param: [userId]
    *@return: java.util.List<com.ectrl.authentication.domain.MqttDeviceEntity>
    */
    List<MqttDeviceEntity> getListByUserId(Long userId);

    /**
    *@Author: Wen zhenwei
    *@date: 2020/4/2 11:43
    *@Description: 通过数字证书查询该证书数据
    *@Param: [CA]
    *@return: com.ectrl.authentication.domain.MqttDeviceEntity
    */
    MqttDeviceEntity getEntityByCA(String CA);

}
