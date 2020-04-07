package com.ectrl.authentication.controller;


import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.service.MqttDeviceService;
import com.ectrl.authentication.service.MqttServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenti")
@Api(value = "获取MQTT资源的相关信息接口",tags = "获取MQTT资源的相关信息接口")
public class AuthentiCAController {

    @Autowired
    private MqttDeviceService mqttDeviceService;

    @Autowired
    private MqttServerService mqttServerService;

    @RequestMapping(value = "/server",method = RequestMethod.POST)
    @ApiOperation(value = "主服务器获取MQTT相关资源",notes = "主服务器认证获取资源,参数ca：数字证书，参数publickey：本地公钥",httpMethod = "POST")
    public BaseResult authentiServerCA(@RequestParam(value = "ca") String enca ,@RequestParam(value = "publickey") String publickey ) throws Exception {

        return mqttServerService.authentiServerCA(enca,publickey);

    }

    @RequestMapping(value = "/device",method = RequestMethod.POST)
    @ApiOperation(value = "设备获取MQTT相关资源",notes = "设备认证获取资源,参数ca：数字证书，参数publickey：本地公钥",httpMethod = "POST")
    public BaseResult authentiDeviceCA(@RequestParam(value = "ca") String enca ,@RequestParam(value = "publickey") String publickey) throws Exception {
        return mqttDeviceService.authentiDeviceCA(enca,publickey);

    }

}
