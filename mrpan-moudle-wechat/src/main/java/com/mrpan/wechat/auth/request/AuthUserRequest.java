package com.mrpan.wechat.auth.request;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class AuthUserRequest extends Base implements Serializable{

	private String accessToken;
	private String openid;
	private String lang;
	
	public AuthUserRequest(String accessToken,String openid,String lang) {
		this.accessToken=accessToken;
		this.openid=openid;
		this.lang=lang;
	}
	
	@Override
	public Map<String, String> getParams() throws Exception {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("access_token",this.accessToken);
		params.put("openid",this.openid);
		params.put("lang",this.lang);
		return params;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	

}
