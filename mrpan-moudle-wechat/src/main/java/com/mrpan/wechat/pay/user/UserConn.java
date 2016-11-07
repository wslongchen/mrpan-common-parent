package com.mrpan.wechat.pay.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.results.JsonResult;
import com.mrpan.wechat.bean.results.WechatResult;
import com.mrpan.wechat.pay.user.entity.WeixinUser;

/**
 * 用户连接
 */
public class UserConn{
	//创建分组			 
	private static String CREATE_GROUP_PATH="https://api.weixin.qq.com/cgi-bin/groups/create";
	//查询所有分组
	private static String QUERY_GROUP_PATH="https://api.weixin.qq.com/cgi-bin/groups/get";
	//查询用户所在分组 
	private static String GETGROUP_GROUP_PATH="https://api.weixin.qq.com/cgi-bin/groups/getid";
	//修改分组名	
	private static String UPDATE_GROUPNAME_PATH="https://api.weixin.qq.com/cgi-bin/groups/update";
	//移动用户分组
	private static String MOVE_USERGROUP_PATH="https://api.weixin.qq.com/cgi-bin/groups/members/update";
	//设置备注名	
	private static String REMARK_USER_PATH="https://api.weixin.qq.com/cgi-bin/user/info/updateremark";
	//批量移动用户分组  
	private static String BATCHUPDATE="https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate";
	//获取用户基本信息(UnionID机制)
	private static String UNIONID_USERINFO_PATH="https://api.weixin.qq.com/cgi-bin/user/info";
	//获取用户列表
	private static String USERLIST_PATH="https://api.weixin.qq.com/cgi-bin/user/get";
	//网页授权获取code  
	private static String AUTH_CODE_PATH="https://open.weixin.qq.com/connect/oauth2/authorize";
	//通过code获取到access_token  
	private static String AUTH_TOKEN_PATH="https://api.weixin.qq.com/sns/oauth2/access_token";
	//刷新access_token  
	private static String REFRESH_TOKEN_PATH="https://api.weixin.qq.com/sns/oauth2/refresh_token";
	//拉取用户信息(需scope为 snsapi_userinfo) 
	private static String SNSAPI_USER_PATH="https://api.weixin.qq.com/sns/userinfo";
	//检验授权凭证（access_token）是否有效  
	private static String TEST_TOKEN_VALID="https://api.weixin.qq.com/sns/auth";
	
	/**
	 * 批量移动用户分组
	 * @param accessToken   授权token
	 * @param openidList    待移入分组的用户openid list集合, 注意size不能超过50
	 * @param groupId		待移入分组的id
	 * @return		JsonResult.errcode==0 && JsonResult.errcode.errmsg表示成功
	 */
	public static JsonResult BatchUpdateGroup(String accessToken,List<String> openidList,int groupId){
		Connection conn = Connection.getInstance("user");
		JsonResult resultObj = null;
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token",accessToken);
		TreeMap<String,Object> postParams = new TreeMap<String,Object>();
		postParams.put("openid_list",openidList);
		postParams.put("to_groupid", groupId);
		String data = JSONObject.toJSONString(postParams);
		String result = conn.HttpsDefaultExecute("POST",BATCHUPDATE,params,data);
		resultObj = JSONObject.parseObject(result, JsonResult.class);
		return resultObj;
	}
	
	
	/**
	 * 创建分组
	 * @param accessToken	授权token
	 * @param name 分组名字（30个字符以内）
	 * @return		
	 */
	public static String createGroup(String accessToken,String name){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", accessToken);
		//此处封装json格式的数据  {"group":{"name":"test"}}
		Map<String,String> map = new HashMap<String,String>();
		map.put("name",name);
		Map<String,Map<String,String>> testMap = new HashMap<String,Map<String,String>>();
		testMap.put("group",map);
		//end
		String paramsData = JSONObject.toJSONString(testMap);
		String jsonResult = conn.HttpsDefaultExecute("POST",CREATE_GROUP_PATH,params,paramsData);
		return jsonResult;
	}
	
	/**
	 * 查询分组
	 * @param asseccToken	授权token
	 * @return	json格式的数据
	 */
	public static String queryGroups(String asseccToken){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token",asseccToken);
		String jsonResult=conn.HttpsDefaultExecute("GET",QUERY_GROUP_PATH,params,"");;
		return jsonResult;
	}
	
	/**
	 * 查询用户所在的用户
	 * @return
	 */
	public static  String getGroupId(String asseccToken,String openid){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", asseccToken);
		Map<String,String> map = new HashMap<String,String>();
		map.put("openid",openid);
		String data = JSONObject.toJSONString(map);
		String jsonResult = conn.HttpsDefaultExecute("POST",GETGROUP_GROUP_PATH,params,data);
		return jsonResult;
	}
	
	/**
	 * 修改分组名
	 * @param asseccToken		授权token
	 * @param groupId			分组id
	 * @param groupName			新的分组名称
	 * @return
	 */
	public static JsonResult updateGroupName(String asseccToken,int groupId,String groupName){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token",asseccToken);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("id", groupId);
		paramsMap.put("name",groupName);
		Map<String,Map<String,Object>> paramset = new HashMap<String,Map<String,Object>>();
		paramset.put("group", paramsMap);
		String data = JSONObject.toJSONString(paramset);
		String jsonResult = conn.HttpsDefaultExecute("POST",UPDATE_GROUPNAME_PATH,params,data);
		JSONObject object = JSONObject.parseObject(jsonResult);
		JsonResult result = JSONObject.toJavaObject(object, JsonResult.class);
		return result;
	}
	
	/**
	 * 移动用户分组
	 * @param access_token		授权token
	 * @param openid			用户openid
	 * @param groupId			需要移动到的分组groupId
	 * @return		jsonResult  errCode==0 and errMsg=ok 表示成功
	 */
	public static JsonResult moveUserGroup(String access_token,String openid,int groupId){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", access_token);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("openid", openid);
		dataMap.put("to_groupid", groupId);
		String data = JSONObject.toJSONString(dataMap);
		String jsonResult=conn.HttpsDefaultExecute("POST",MOVE_USERGROUP_PATH,params,data);
		JSONObject object = JSONObject.parseObject(jsonResult);
		JsonResult result = JSONObject.toJavaObject(object, JsonResult.class);
		return result;
	}
	
	/**
	 * 设置用户备注名
	 * @param assecc_token		授权token
	 * @param openid			用户openid
	 * @param remark			备注名
	 * @return
	 */
	public static JsonResult remarkUserName(String assecc_token,String openid,String remark){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token",assecc_token);
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("openid", openid);
		dataMap.put("remark", remark);
		String data = JSONObject.toJSONString(dataMap);
		String jsonResult=conn.HttpsDefaultExecute("POST",REMARK_USER_PATH,params,data);
		JSONObject object = JSONObject.parseObject(jsonResult);
		JsonResult result = JSONObject.toJavaObject(object, JsonResult.class);
		return 	result;
	}
	
	/**
	 * 获取用户基本信息(UnionID机制)
	 * @param accessToken		授权token
	 * @param openid			普通用户的标识，对当前公众号唯一
	 * @param lang				(默认为简体中文)国家地区语言版本，zh_CN 简体，zh_TW 繁体，en英语
	 * @return
	 */
	public static WechatResult unionIDUserInfo(String accessToken, String openid, String lang){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", (lang!=null&&!"".equals(lang)?lang:"zh_CN"));
		String jsonResult=conn.HttpsDefaultExecute("POST",UNIONID_USERINFO_PATH,params,"");
		return converWeixinUser(jsonResult);
	}
	/**
	 * 获取用户列表
	 * @param accessToken	授权token
	 * @param next_openid	第一个拉取的OPENID，不填默认从头开始拉取
	 * @return
	 */
	public static String getUserList(String accessToken,String next_openid){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", accessToken);
		if(next_openid!=null&&!"".equals(next_openid)){
			params.put("next_openid", next_openid);
		}
		String jsonResult = conn.HttpsDefaultExecute("GET",USERLIST_PATH,params,"");	
		return jsonResult;
	}
	
	/**
	 * 获取授权code的path			(使用场景如:可用自定菜单中)
	 * @param appid			应用密匙
	 * @param redirect_uri	授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * @param scope			应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state			重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值
	 * @return	
	 */
	public static String getAuthCodeUrl(String appid,String redirect_uri,String scope,String state)throws Exception{
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("appid", appid);
		params.put("redirect_uri", redirect_uri);
		params.put("response_type","code");
		params.put("scope",scope);
		params.put("state",state+"#wechat_redirect");
		String url =conn.SetParmas((TreeMap<String,String>)params,AUTH_CODE_PATH,"");
		return url;
	}
	
	/**
	 * 通过code换取网页授权access_token
	 * @param appid	  	公众号的唯一标识
	 * @param secret	公众号的appsecret
	 * @param code		网页授权中code中的参数
	 * @return		AccessToken对象 
	 */
	public static WechatResult getTokenByCode(String appid,String secret,String code){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("appid",appid);
		params.put("secret",secret);
		params.put("code",code);
		params.put("grant_type","authorization_code");
		String jsonResult = conn.HttpsDefaultExecute("GET",AUTH_TOKEN_PATH,params,"");	
		return 	Connection.ConvertToken(jsonResult);
	}
	
	/**
	 * 刷新access_token 
	 * @param appid			公众号的唯一标识
	 * @param refresh_token	填写通过access_token获取到的refresh_token参数
	 * @return  AccessToken对象 
	 */
	public static WechatResult getTokenByRefreshToken(String appid,String refresh_token){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("appid", appid);
		params.put("grant_type","refresh_token");
		params.put("refresh_token", refresh_token);
		String jsonResult = conn.HttpsDefaultExecute("GET",REFRESH_TOKEN_PATH,params,"");
		return Connection.ConvertToken(jsonResult);
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @param access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同(网页授权中获取到access_Token)
	 * @param openid	用户的唯一标识
	 * @param lang	       返回国家地区语言版本，默认为 zh_CN    zh_CN 简体，zh_TW 繁体，en 英语  
	 * @return
	 */
	public static WechatResult getBySnsapiUserInfo(String access_token,String openid,String lang){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", access_token);
		params.put("openid", openid);
		params.put("lang", (lang!=null&&!"".equals(lang)?lang:"zh_CN"));
		String jsonResult = conn.HttpsDefaultExecute("GET",SNSAPI_USER_PATH,params,"");
		return converWeixinUser(jsonResult);
	}
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param access_token	授权token	
	 * @param openid		openid 用户的唯一标识
	 * @return	JsonResultd对象		errcode:0,"errmsg":"ok"表示有效
	 */
	public static JsonResult testTokenValid(String access_token,String openid){
		Connection conn = Connection.getInstance("user");
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token", access_token);
		params.put("openid", openid);
		String jsonResult = conn.HttpsDefaultExecute("GET",TEST_TOKEN_VALID,params,"");
		return conn.ConvertResult(jsonResult);
	}
	/**
	 * 将json格式的数据转换为WeixinUser对象
	 * @param jsonResult
	 * @return
	 */
	private static WechatResult converWeixinUser(String jsonResult){
		WechatResult result = new WechatResult();
		if(jsonResult!=null&&!"".equals(jsonResult)){
			JSONObject json = JSONObject.parseObject(jsonResult);
			if(json.containsKey("openid")){
				WeixinUser user = JSONObject.toJavaObject(json, WeixinUser.class);
				result.setObj(user);
				result.setSuccess(true);
			}
		}
		result.setMsg(jsonResult);
		return result;
	}
}
