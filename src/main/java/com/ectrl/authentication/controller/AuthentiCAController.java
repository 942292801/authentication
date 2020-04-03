package com.ectrl.authentication.controller;


import com.ectrl.authentication.dto.BaseResult;
import com.ectrl.authentication.service.MqttDeviceService;
import com.ectrl.authentication.service.MqttServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenti")
public class AuthentiCAController {

    @Autowired
    private MqttDeviceService mqttDeviceService;

    @Autowired
    private MqttServerService mqttServerService;

    @RequestMapping(value = "/server")
    public BaseResult authentiServerCA(@RequestParam(value = "ca") String enca ,@RequestParam(value = "publickey") String publickey ) throws Exception {

        return mqttServerService.authentiServerCA(enca,publickey);

    }

    @RequestMapping(value = "/device")
    public BaseResult authentiDeviceCA(@RequestParam(value = "ca") String enca ,@RequestParam(value = "publickey") String publickey) throws Exception {
        return mqttDeviceService.authentiDeviceCA(enca,publickey);

    }

}
