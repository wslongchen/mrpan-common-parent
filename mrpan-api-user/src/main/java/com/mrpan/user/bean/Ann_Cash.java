package com.mrpan.user.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mrpan on 2017/2/9.
 */
@Entity
@Table(name="ann_cash")
public class Ann_Cash implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CreateDate")
    private Date createDate;
    @Column(name="direction")
    private Integer direction;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "type")
    private Integer type;
    @Column(name = "description")
    private String description;
    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
