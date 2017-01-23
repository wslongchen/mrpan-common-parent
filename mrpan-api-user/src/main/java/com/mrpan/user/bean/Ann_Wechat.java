package com.mrpan.user.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mrpan on 2017/1/23.
 */
@Entity
@Table(name="ann_wechat")
public class Ann_Wechat implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "OpenId")
    private String openId = "";
    @Column(name="secret")
    private String secret;
    @Column(name="token")
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
