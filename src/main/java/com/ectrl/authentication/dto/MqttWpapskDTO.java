package com.ectrl.authentication.dto;

import lombok.Data;

import java.io.Serializable;

/**
*@Author: Wen zhenwei
*@date: 2020/3/13 14:42
*@Description: 公私钥匙传输对象
*@Param:
*@return:
*/
@Data
public class MqttWpapskDTO implements Serializable {
    /** serverName */
    private String serverName;

    /** wpapsk */
    private String wpapsk;

    /** publicKey */
    private String publicKey;


}
