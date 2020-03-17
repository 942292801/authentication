package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * mqtt_wpapsk
 *
 * @author bianj
 * @version 1.0.0 2020-03-12
 */
@TableName(value = "mqtt_wpapsk")
@Data
public class MqttWpapskEntity implements Serializable {

    /** id */
    private Integer id;

    /** serverName */
    private String serverName;

    /** wpapsk */
    private String wpapsk;

    /** publicKey */
    private String publicKey;

    /** privateKey */
    private String privateKey;


}
