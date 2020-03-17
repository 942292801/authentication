package com.ectrl.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ectrl.authentication.domain.MqttUserEntity;
import com.ectrl.authentication.mapper.MqttAclMapper;
import com.ectrl.authentication.mapper.MqttUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SQLTset {

    @Autowired
    private MqttUserMapper mqttUserMapper;

    @Autowired
    private MqttAclMapper mqttAclMapper;

    @Test
    public void SQLSelect() {
        /*System.out.println(("----- selectAll method test1 ------"));
        List<MqttUser> userList = mqttUserMapper.selectList(null);
        userList.forEach(System.out::println);

        System.out.println(("----- selectAll method test2 ------"));
        MqttAcl aclList = mqttAclMapper.selectById(1);
        System.out.println(aclList.toString());*/

        System.out.println(("----- selectAll method test3 ------"));
        QueryWrapper<MqttUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","ww");
        MqttUserEntity mqttUser = mqttUserMapper.selectOne(queryWrapper);
        System.out.println(mqttUser.toString());

        /*System.out.println(("----- selectAll method test4 ------"));
        QueryWrapper<MqttAcl> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(MqttAcl::getUsername, "wzw");
        List<MqttAcl> mqttAclList = mqttAclMapper.selectList(queryWrapper2);
        mqttAclList.forEach(System.out::println);*/
    }



}
