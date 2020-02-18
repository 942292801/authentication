package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "mqtt_acl")
public class MqttAcl implements Serializable {
    private int id;
    private int allow;
    private String ipaddr;
    private String username;
    private String clientid;
    private int access;
    private  String topic;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllow() {
        return allow;
    }

    public void setAllow(int allow) {
        this.allow = allow;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "MqttAcl{" +
                "id=" + id +
                ", allow=" + allow +
                ", ipaddr='" + ipaddr + '\'' +
                ", username='" + username + '\'' +
                ", clientid='" + clientid + '\'' +
                ", access=" + access +
                ", topic='" + topic + '\'' +
                '}';
    }

}
