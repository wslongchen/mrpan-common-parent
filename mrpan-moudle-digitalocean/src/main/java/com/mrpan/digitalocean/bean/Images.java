package com.mrpan.digitalocean.bean;

import java.util.List;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Images extends BaseEntity {
    private Integer id;
    private String name;
    private String type;
    private String distribution;
    private String slug;
    private boolean _public;
    private List<String> regions;
    private Integer min_disk_size;
    private Integer size_gigabytes;
    private String created_at;
}
