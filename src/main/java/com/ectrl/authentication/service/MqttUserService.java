package com.ectrl.authentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ectrl.authentication.domain.MqttUserEntity;

public interface MqttUserService extends IService<MqttUserEntity> {

    /**
    *@Author: Wen zhenwei
    *@date: 2020/4/2 10:26
    *@Description: 查询id为userid的账户
    *@Param: [userId]
    *@return: com.ectrl.authentication.domain.MqttUserEntity
    */
    MqttUserEntity getEntityById(Long userId);
}
