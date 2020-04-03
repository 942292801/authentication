package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * mqtt_device
 *
 * @author bianj
 * @version 1.0.0 2020-03-12
 */
@Data
@TableName(value = "mqtt_device")
public class MqttDeviceEntity implements Serializable {
    /** id */
    private Long id;

    /** sn */
    private String sn;

    /** mac */
    private String mac;

    /** crc */
    private String crc;

    /** created */
    private Date created;

    /** expired */
    private Date expired;

    /** random */
    private Integer random;

    /** sign */
    private String sign;

    /** userId */
    private Long userId;

}
