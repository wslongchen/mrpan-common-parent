package com.mrpan.digitalocean.bean;

import com.mrpan.wechat.auth.request.Base;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Account extends BaseEntity{
    private Integer droplet_limit;
    private Integer floating_ip_limit;
    private String email;
    private String uuid;
    private boolean email_verified;
    private String status;
    private String status_message;

    public Integer getDroplet_limit() {
        return droplet_limit;
    }

    public void setDroplet_limit(Integer droplet_limit) {
        this.droplet_limit = droplet_limit;
    }

    public Integer getFloating_ip_limit() {
        return floating_ip_limit;
    }

    public void setFloating_ip_limit(Integer floating_ip_limit) {
        this.floating_ip_limit = floating_ip_limit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
