package com.mrpan.manage.web.sec;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.mrpan.common.core.utils.MyMD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by mrpan on 2016/11/3.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Object accountCredentials = getCredentials(info);
		boolean access = false;
		try {
			access = MyMD5Util.validPassword(
					String.valueOf(token.getPassword()),
					String.valueOf(accountCredentials));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return access;
	}
}
