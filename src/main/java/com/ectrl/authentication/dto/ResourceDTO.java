package com.ectrl.authentication.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
*@Author: Wen zhenwei
*@date:  10:03
*@Description: 认证成功后 返回的订阅主题资源信息
*@Param:
*@return:
*/
@Data
public class ResourceDTO implements Serializable {
    /** username */
    private String username;

    /** password */
    private String passwword;

    /** channel */
    private String channel;

    /** scWpapsk */
    private String scWpapsk;

    /** scPassword */
    private String scPassword;

    /** certid */
    private String certid;

    /** certids */
    private List<String> certids;
}
