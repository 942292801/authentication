package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * mqtt_acl
 *
 * @author bianj
 * @version 1.0.0 2020-03-12
 */
@Data
@TableName(value = "mqtt_acl")
public class MqttAclEntity implements Serializable {
    /** id */
    private Integer id;

    /** 0: deny, 1: allow */
    private Integer allow;

    /** IpAddress */
    private String ipaddr;

    /** Username */
    private String username;

    /** ClientId */
    private String clientid;

    /** 1: subscribe, 2: publish, 3: pubsub */
    private Integer access;

    /** Topic Filter */
    private String topic;



}
