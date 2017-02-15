package com.mrpan.user.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mrpan on 2017/2/14.
 */
@Entity
@Table(name="ann_bike")
public class Ann_Bike implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CarNo")
    private String cardNo;
    @Column(name = "CarPwd")
    private String carPwd;
    @Column(name = "CreateDate")
    private Date createDate;
    @Column(name = "CreateId")
    private String createId;
    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCarPwd() {
        return carPwd;
    }

    public void setCarPwd(String carPwd) {
        this.carPwd = carPwd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
