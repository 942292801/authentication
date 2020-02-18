package com.ectrl.authentication.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "mqtt_user")
public class MqttUser implements Serializable {
    private int id;
    private String username;
    private String password;
    private String salt;
    private int isSuperuser;
    private  String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIssuperuser() {
        return isSuperuser;
    }

    public void setIssuperuser(int issuperuser) {
        this.isSuperuser = issuperuser;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "MqttUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", issuperuser=" + isSuperuser +
                ", created='" + created + '\'' +
                '}';
    }
}
