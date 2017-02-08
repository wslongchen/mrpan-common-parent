package com.mrpan.digitalocean.bean;

import com.mrpan.wechat.auth.request.Base;

import java.util.List;

/**
 * Created by mrpan on 2017/2/8.
 */
public class BlockStorage extends BaseEntity{
    private String id;
    private Region region;
    private String name;
    private String description;
    private Integer size_gigabytes;
    private String created_at;
    private List<String> droplet_ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize_gigabytes() {
        return size_gigabytes;
    }

    public void setSize_gigabytes(Integer size_gigabytes) {
        this.size_gigabytes = size_gigabytes;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<String> getDroplet_ids() {
        return droplet_ids;
    }

    public void setDroplet_ids(List<String> droplet_ids) {
        this.droplet_ids = droplet_ids;
    }
}
