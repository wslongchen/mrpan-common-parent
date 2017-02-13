package com.mrpan.user.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mrpan on 2017/2/13.
 */
@Entity
@Table(name="ann_game")
public class Ann_Game implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "UpdateDate")
    private Date updateDate;
    @Column(name = "WechatId")
    private String wechatId;
    @Column(name = "type")
    private Integer type;
    @Column(name = "score")
    private Integer score;
    @Column(name = "level")
    private Integer level;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
