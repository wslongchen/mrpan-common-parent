package com.mrpan.wechat.auth.request;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class AuthTokenRequest extends Base implements Serializable{

	private String appid;
	private String secret;
	private String code;
	private String grant_type = "authorization_code";
	
	public AuthTokenRequest() {
		super();
		this.appid = appid;
		this.secret = secret;
		this.code = code;
		this.grant_type = grant_type;
	}
	
	@Override
	public Map<String, String> getParams() throws Exception {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("appid", this.appid);
		params.put("secret", this.secret);
		params.put("code", this.code);
		params.put("grant_type", this.grant_type);
		return params;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
}
