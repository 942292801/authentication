package com.ectrl.authentication.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class ApplyCADTO implements Serializable {
    /** sn */
    private String sn;

    /** mac */
    private String mac;

    /** crc */
    private String crc;

    /** publickKey */
    private String publicKey;
}
