package com.mrpan.wechat.pay.redpack;

import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.results.WechatResult;
import com.mrpan.wechat.pay.handler.PayHandler;
import com.mrpan.wechat.pay.request.RedBackRequest;
import com.mrpan.wechat.pay.response.UnifiedOrderResponse;

public class RedpackConn {

	//发送普通红包
	private static final String SENDREDPACK="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	//发放裂变红包
	private static final String SENDGROUPREDPACK="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
	//查询红包记录
	private static final String GETHBINFO="https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
	
	/**
	 * 发送普通红包
	 * @param request
	 * @return
	 */
	public static WechatResult sendRedPack(RedBackRequest request) {
		WechatResult wechatResult=new WechatResult();
		Connection connection= Connection.getInstance("pay");
		String data = PayHandler.redpack(request);
		String result = connection.HttpsDefaultExecute("POST", SENDREDPACK,null,data);
		UnifiedOrderResponse response = PayHandler.unifiedOrderResponse(result);
		wechatResult.setMsg(result);
		wechatResult.setSuccess(true);
		wechatResult.setObj(response);
		return wechatResult;
	}
	
	/**
	 * 发送裂变红包
	 * @param request
	 * @return
	 */
	public static WechatResult  sendGroupRedPack(RedBackRequest request) {
		WechatResult wechatResult=new WechatResult();
		Connection connection=Connection.getInstance("pay");
		String data = PayHandler.redpack(request);
		String result = connection.HttpsDefaultExecute("POST", SENDGROUPREDPACK,null,data);
		UnifiedOrderResponse response = PayHandler.unifiedOrderResponse(result);
		wechatResult.setMsg(result);
		wechatResult.setSuccess(true);
		wechatResult.setObj(response);
		return wechatResult;
	}
	
	/**
	 * 获取红包列表
	 * @return
	 */
	public static WechatResult  getHbInfo() {
		return null;
	}
}
