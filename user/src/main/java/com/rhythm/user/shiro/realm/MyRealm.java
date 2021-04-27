package com.rhythm.user.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.user.entity.User;
import com.rhythm.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * 自定义shiro域
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户名
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        log.info("-----------------------------------");
        log.info(principals.toString());
        log.info("" + SecurityUtils.getSubject().getSession().getAttribute("user"));
        log.info("-----------------------------------");
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        // 给该用户设置角色
        authorizationInfo.setRoles(userService.getRoles(user.getId()));
        // 给该用户设置权限
        authorizationInfo.setStringPermissions(new HashSet<>());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 根据 Token 获取用户名，如果您不知道该 Token 怎么来的，先可以不管，下文会解释
        String username = (String) token.getPrincipal();
        // 根据用户名从数据库中查询该用户
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if(user != null) {
            // 把当前用户存到 Session 中
            try {
                SecurityUtils.getSubject().getSession().setAttribute("user", objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            SecurityUtils.getSubject().getSession().setAttribute("userId", user.getId());
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
