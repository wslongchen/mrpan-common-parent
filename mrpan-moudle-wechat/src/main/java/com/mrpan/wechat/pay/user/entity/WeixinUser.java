package com.mrpan.wechat.pay.user.entity;

import java.util.Date;

/**
 * 微信用户
 */
public class WeixinUser {
	private String openid; // openid
	private String nickname; // 用户昵称
	private int sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province; // 用户个人资料填写的省份
	private String city; // 普通用户个人资料填写的城市
	private String country; // 国家，如中国为CN
	private String headimgurl; // 用户头像，
	private String privilege; // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String unionid; // unionid
	private String remark; // 备注
	private int subscribe; // 是否关注
	private Date subscribe_time; // 关注时间
	private String language; //

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
