package com.mrpan.wechat.auth.response;

/**
 * 授权token的返回
 * 
 * @author Andy
 * 
 */
public class AuthTokenResponse implements java.io.Serializable {
	private String access_token; // 范围授权token
	private int expires_in; // 过时时间
	private String refresh_token;// 刷新token
	private String openid; // 用户的openid
	private String scope; // 范围
	private String unionid;

	public AuthTokenResponse(String access_token, int expires_in,
			String refresh_token, String openid, String scope, String unionid) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.openid = openid;
		this.scope = scope;
		this.unionid = unionid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}