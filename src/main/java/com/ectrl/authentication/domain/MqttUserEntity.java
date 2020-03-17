package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * mqtt_user
 *
 * @author bianj
 * @version 1.0.0 2020-03-12
 */
@Data
@TableName(value = "mqtt_user")
public class MqttUserEntity implements Serializable {
    /** id */
    private Integer id;

    /** sn */
    private String sn;

    /** username */
    private String username;

    /** password */
    private String password;

    /** salt */
    private String salt;

    /** isSuperuser */
    private Integer isSuperuser;

    /** created */
    private Date created;

    /** phone */
    private Integer phone;

    /** email */
    private String email;

    /** scWpapsk */
    private String scWpapsk;

    /** scPassword */
    private String scPassword;

    /** csWpapsk */
    private String csWpapsk;

    /** csPassword */
    private String csPassword;

    /** note */
    private String note;
}
