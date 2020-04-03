package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttServerEntity;
import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.dto.ResourceDTO;

import java.util.List;

public interface MqttServerService extends IService<MqttServerEntity> {
    /**
     *@Author: Wen zhenwei
     *@date: 2020/3/26 15:36
     *@Description: 服务器申请证书
     *@Param: [enCA加密的信息 数字证书, publicKey 公钥]
     *@return: 状态码 ：+账户+密码+通道号+List<设备certid>
     */
    BaseResult authentiServerCA(String enCA, String publicKey) throws Exception;


    /**
    *@Author: Wen zhenwei
    *@date: 2020/4/2 11:39
    *@Description: 查询userid相同的所有主服务器
    *@Param: [userId]
    *@return: java.util.List<com.ectrl.authentication.domain.MqttServerEntity>
    */
    List<MqttServerEntity> getListByUserId(Long userId);

    /**
    *@Author: Wen zhenwei
    *@date: 2020/4/2 10:49
    *@Description: 通过数字证书查询该证书数据
    *@Param: [CA] 数字证书
    *@return: com.ectrl.authentication.domain.MqttServerEntity
    */
    MqttServerEntity getEntityByCA(String CA);

}
