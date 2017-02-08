package com.mrpan.digitalocean.bean;

import com.mrpan.wechat.bean.resp.bean.Image;
import com.mrpan.wechat.bean.results.utils.Signature;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Droplets extends BaseEntity {
    private Integer id;
    private String name;
    private Integer memory;
    private Integer vcpus;
    private Integer disk;
    private boolean locked;
    private String created_at;
    private String status;
    private List<String> backup_ids;
    private List<String> snapshot_ids;
    private List<String> features;
    private Region region;
    private Image image;
    private Sizes size;
    private String size_slug;
    private Object networks;
    private Object kernel;
    private Object next_backup_window;
    private List<String> tags;
    private List<String> volume_ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getVcpus() {
        return vcpus;
    }

    public void setVcpus(Integer vcpus) {
        this.vcpus = vcpus;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getBackup_ids() {
        return backup_ids;
    }

    public void setBackup_ids(List<String> backup_ids) {
        this.backup_ids = backup_ids;
    }

    public List<String> getSnapshot_ids() {
        return snapshot_ids;
    }

    public void setSnapshot_ids(List<String> snapshot_ids) {
        this.snapshot_ids = snapshot_ids;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public String getSize_slug() {
        return size_slug;
    }

    public void setSize_slug(String size_slug) {
        this.size_slug = size_slug;
    }

    public Object getNetworks() {
        return networks;
    }

    public void setNetworks(Object networks) {
        this.networks = networks;
    }

    public Object getKernel() {
        return kernel;
    }

    public void setKernel(Object kernel) {
        this.kernel = kernel;
    }

    public Object getNext_backup_window() {
        return next_backup_window;
    }

    public void setNext_backup_window(Object next_backup_window) {
        this.next_backup_window = next_backup_window;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getVolume_ids() {
        return volume_ids;
    }

    public void setVolume_ids(List<String> volume_ids) {
        this.volume_ids = volume_ids;
    }
}
