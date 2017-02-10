package com.mrpan.user.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mrpan on 2017/1/23.
 */
@Entity
@Table(name="ann_vpn")
public class Ann_Vpn implements Serializable {
    @Id
    @Column(name = "VpnId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vpnId;
    @Column(name="CreateDate")
    private Date createDate;
    @Column(name="WechatId")
    private String wechatId;
    @Column(name = "port")
    private String port;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "remark")
    private String remark;

    public Integer getVpnId() {
        return vpnId;
    }

    public void setVpnId(Integer vpnId) {
        this.vpnId = vpnId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
