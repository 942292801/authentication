package com.ectrl.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ectrl.authentication.commons.MapperUtils;
import com.ectrl.authentication.commons.RSAUtil;
import com.ectrl.authentication.domain.MqttDeviceEntity;
import com.ectrl.authentication.domain.MqttServerEntity;
import com.ectrl.authentication.domain.MqttUserEntity;
import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.dto.ResourceDTO;
import com.ectrl.authentication.mapper.MqttDeviceMapper;
import com.ectrl.authentication.service.MqttDeviceService;
import com.ectrl.authentication.service.MqttServerService;
import com.ectrl.authentication.service.MqttUserService;
import com.ectrl.authentication.service.MqttWpapskService;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
*@Author: Wen zhenwei
*@date: 2020/3/30 14:53
*@Description: 验证设备数字证书
 *@Param:
*@return:
*/
@Service
public class MqttDeviceServiceImpl extends ServiceImpl<MqttDeviceMapper, MqttDeviceEntity> implements MqttDeviceService {

    @Autowired
    MqttWpapskService mqttWpapskService;

    @Autowired
    MqttUserService mqttUserService;

    @Autowired
    MqttServerService mqttServerService;

    @Override
    public BaseResult authentiDeviceCA(String enCA, String publicKey) throws Exception {
        try {
            //解密 获取证书
            String CA = mqttWpapskService.decryptString(enCA);
            if (StringUtils.isEmpty(CA)){
                return BaseResult.fail("参数CA解密失败 请检查该参数");
            }
            //获取证书实体
            MqttDeviceEntity mqttDeviceEntity = this.getEntityByCA(CA);
            if (mqttDeviceEntity == null)
            {
                //证书不存在
                return BaseResult.fail("警告!数字证书错误");
            }
            //证书是否过期
            if (!mqttDeviceEntity.getExpired().after(new Date())){
                //过期 直接返回失败  提示证书过期
                return BaseResult.fail(204,"数字证书已过期 请重新申请");
            }
            //返回的数据结构
            ResourceDTO resourceDTO = new ResourceDTO();
            //查询获取账户密码
            MqttUserEntity mqttUserEntity = mqttUserService.getEntityById(mqttDeviceEntity.getUserId());
            if (mqttUserEntity == null){
                return BaseResult.fail("绑定账户不存在 请检查账户是否正常工作");
            }
            resourceDTO.setUsername(mqttUserEntity.getUsername());
            resourceDTO.setPasswword(mqttUserEntity.getPassword());
            resourceDTO.setChannel(mqttUserEntity.getChannel());
            resourceDTO.setCertid(mqttDeviceEntity.getSn()+mqttDeviceEntity.getMac()+mqttDeviceEntity.getCrc());
            //查询绑定的主服务器
            List<MqttServerEntity> mqttServerEntityList =  mqttServerService.getListByUserId(mqttDeviceEntity.getUserId());
            List<String> tmp = new ArrayList<>() ;
            for (MqttServerEntity entity : mqttServerEntityList) {
                tmp.add(entity.getSn()+entity.getMac()+entity.getCrc());
            }
            resourceDTO.setCertids(tmp);
            String enResource = RSAUtil.encipher(MapperUtils.obj2json(resourceDTO),publicKey.replaceAll(" +","+"));
            if (StringUtils.isEmpty(enResource))
            {
                return BaseResult.fail("参数publicKey错误 请检查该参数");
            }
            return BaseResult.success("数字证书认证成功",enResource);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("发生未知错误！");
        }
    }

    @Override
    public List<MqttDeviceEntity> getListByUserId(Long userId) {
        //查询绑定的设备
        QueryWrapper<MqttDeviceEntity> queryWrapperDev = new QueryWrapper<>();
        queryWrapperDev.eq("user_id",userId);
        List<MqttDeviceEntity> mqttDeviceEntityList =  this.list(queryWrapperDev);
        return mqttDeviceEntityList;
    }

    @Override
    public MqttDeviceEntity getEntityByCA(String CA) {
        List<String> list = Splitter.on("&").splitToList(CA);
        String CertId  = list.get(0).replaceAll("certid=","");
        if (StringUtils.isEmpty(CertId)  ||CertId.length() != 32){
            return null;
        }
        String sn = CertId.substring(0,16);
        String mac = CertId.substring(16,28);
        String crc = CertId.substring(28,32);
        Date created = new Date(Long.valueOf(list.get(1).replaceAll("created=",""))) ;
        Date expired = new Date(Long.valueOf(list.get(2).replaceAll("expired=",""))) ;

        String sign = list.get(3).replaceAll("sign=","");

        QueryWrapper<MqttDeviceEntity> queryWrapper = new QueryWrapper<>();
        //构造数据库语句
        queryWrapper.eq("sn",sn);
        queryWrapper.eq("mac",mac);
        queryWrapper.eq("crc",crc);
        queryWrapper.eq("created",created);
        queryWrapper.eq("expired",expired);
        queryWrapper.eq("sign",sign);
        MqttDeviceEntity mqttDeviceEntity = this.getOne(queryWrapper);
        return mqttDeviceEntity;
    }
}
