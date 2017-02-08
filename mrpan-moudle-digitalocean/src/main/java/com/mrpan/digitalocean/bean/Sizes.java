package com.mrpan.digitalocean.bean;

import java.util.List;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Sizes extends BaseEntity {
    private String slug;
    private boolean available;
    private Integer transfer;
    private Double price_monthly;
    private Double price_hourly;
    private Double memory;
    private Double vcpus;
    private Double disk;
    private List<String> regions;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getTransfer() {
        return transfer;
    }

    public void setTransfer(Integer transfer) {
        this.transfer = transfer;
    }

    public Double getPrice_monthly() {
        return price_monthly;
    }

    public void setPrice_monthly(Double price_monthly) {
        this.price_monthly = price_monthly;
    }

    public Double getPrice_hourly() {
        return price_hourly;
    }

    public void setPrice_hourly(Double price_hourly) {
        this.price_hourly = price_hourly;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getVcpus() {
        return vcpus;
    }

    public void setVcpus(Double vcpus) {
        this.vcpus = vcpus;
    }

    public Double getDisk() {
        return disk;
    }

    public void setDisk(Double disk) {
        this.disk = disk;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}
