package com.goodtime.user.controller.security;

import com.github.api.code.AjaxCode;
import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.goodtime.base.exception.BizException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户身份验证,授权 Realm 组件
 * Created by zhongcy on 2016/8/17.
 **/
@Component("securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录验证
     * 分为管理员登录的验证和用户跳转的验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String userId = String.valueOf(token.getPrincipal());
        String password = String.valueOf((char[]) token.getCredentials());

        User authentication = userInfoService.loginIn(Integer.valueOf(userId), password);
        if (authentication == null) {
            throw new BizException(AjaxCode.PARAM_ERROR, "用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(userId, password, getName());
    }
}
