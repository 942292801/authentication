package com.ectrl.authentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ectrl.authentication.domain.MqttUserEntity;
import com.ectrl.authentication.mapper.MqttUserMapper;
import com.ectrl.authentication.service.MqttUserService;
import org.springframework.stereotype.Service;

/**
*@Author: Wen zhenwei
*@date: 2020/4/2 11:11
*@Description: 用户账户操作
*@Param:
*@return:
*/
@Service
public class MqttUserServiceImpl extends ServiceImpl<MqttUserMapper, MqttUserEntity> implements MqttUserService {


    @Override
    public MqttUserEntity getEntityById(Long userId) {
        //获取
        MqttUserEntity mqttUserEntity = this.getById(userId);
        return mqttUserEntity;
    }
}
