package com.ecarinfo.traffic.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.ecarinfo.traffic.persist.po.RalResource;
import com.ecarinfo.traffic.persist.po.RalRole;
import com.ecarinfo.traffic.persist.po.RalUser;
import com.ecarinfo.traffic.service.RalService;
import com.ecarinfo.traffic.util.SessionUtil;
import com.ecarinfo.traffic.vo.StaticConstant;
import com.ecarinfo.traffic.vo.security.RalUserVO;

public class GradRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(GradRealm.class);

	@Resource
	private RalService ralService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) getAvailablePrincipal(principals);
		// 通过用户名获得用户的所有资源，并把资源存入info中
		RalUserVO user = this.ralService.findUserInfo(userName);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();

		if (user.getRoleSet() != null) {
			for (RalRole role : user.getRoleSet()) {
				roles.add(role.getName());
			}
		}

		if (user.getResourceSet() != null) {
			for (RalResource res : user.getResourceSet()) {
				permissions.add(res.getUrl());
			}
		}
		info.addRoles(roles);
		info.addStringPermissions(permissions);
		return info;
	}

	/**
	 * 登录时调用
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token中储存着输入的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 获得用户名与密码
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		// 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
		RalUser user = this.ralService.findUserInfo(username);

		// 用户不存在
		if (user == null) {
			logger.error("登陆验证用户[" + username + "]不存在!");
		} else {
			// 用户名、密码正确
			if (user.getPassword().equals(password)) {
				Session session = SessionUtil.getShiroSession();
				// 设置登陆成功的用户信息
				session.setAttribute(StaticConstant.USER_SESSION, user);

				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
				return info;
			}
			// 密码错误
			else if (!user.getPassword().equals(password)) {
				logger.error("登陆验证用户[" + username + "]密码错误!");
			}
		}
		return null;
	}
}
