package com.mrpan.digitalocean.bean;

import com.mrpan.wechat.auth.request.Base;

/**
 * Created by mrpan on 2017/2/8.
 */
public class FloatingIPs extends BaseEntity {
    private String ip;
    private Region region;
    private Droplets droplet;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Droplets getDroplet() {
        return droplet;
    }

    public void setDroplet(Droplets droplet) {
        this.droplet = droplet;
    }
}
