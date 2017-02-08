package com.mrpan.digitalocean.bean;

import com.mrpan.wechat.bean.results.utils.StringUtils;

import java.util.List;

/**
 * Created by mrpan on 2017/2/8.
 */
public class Region extends BaseEntity {
    private String slug;
    private String name;
    private List<Sizes> sizes;
    private boolean available;
    private String[] features;
}
