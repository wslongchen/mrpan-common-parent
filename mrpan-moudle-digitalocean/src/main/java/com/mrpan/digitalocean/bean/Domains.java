package com.mrpan.digitalocean.bean;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Domains extends BaseEntity{
    private String name;
    private Integer ttl;
    private String zone_file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getZone_file() {
        return zone_file;
    }

    public void setZone_file(String zone_file) {
        this.zone_file = zone_file;
    }
}
