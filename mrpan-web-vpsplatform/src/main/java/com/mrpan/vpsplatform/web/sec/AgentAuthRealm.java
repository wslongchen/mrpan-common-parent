package com.mrpan.vpsplatform.web.sec;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.service.Ann_UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AgentAuthRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(AgentAuthRealm.class);

	@Autowired
	private Ann_UserService ann_UserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 本系统采用代理商或者商户的工号登录
		try {
			Ann_User user = this.ann_UserService.findUser(userName, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> roles = new HashSet<String>();
//		roles.add(user.getRoleId() + "");
//		authorizationInfo.setRoles(roles);
		// 本系统采用代理商的所拥有的菜单的url作为权限进行验证
//		Set<String> menus = this.sema_OperatorService.getMenuByUserName(userName);
//		authorizationInfo.setStringPermissions(menus);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String userName = usernamePasswordToken.getUsername();
		// User user = userService.getByName(username);
		try {
			Ann_User user = this.ann_UserService.findUser(userName,null);
			if (user != null) {
				System.out.println("===============================" + user.getPassword());

				// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						user.getUserName(),
						user.getPassword(),
						// MyMD5Util.getEncryptedPwd(user.getPassword()),
						// ByteSource.Util.bytes(user.getSalt()),// salt
						getName() // realm name
				);
				return authenticationInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
