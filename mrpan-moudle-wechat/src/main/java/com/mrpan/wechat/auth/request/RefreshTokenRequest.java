package com.mrpan.wechat.auth.request;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class RefreshTokenRequest extends Base implements Serializable{
	private String appid;
	private String grant_type = "refresh_token";
	private String refresh_token;
	
	public RefreshTokenRequest(String appid, String grant_type,
			String refresh_token) {
		super();
		this.appid = appid;
		this.grant_type = grant_type;
		this.refresh_token = refresh_token;
	}

	@Override
	public Map<String, String> getParams() throws Exception {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("appid",this.appid);
		params.put("grant_type",this.grant_type);
		params.put("refresh_token",this.refresh_token);
		return params;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
}
