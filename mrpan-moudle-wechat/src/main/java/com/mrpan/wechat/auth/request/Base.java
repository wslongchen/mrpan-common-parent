package com.mrpan.wechat.auth.request;

import java.util.Map;

public abstract class Base {
		
	/**����
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String,String> getParams() throws Exception;
}
