package com.rhythm.user.shiro.configuration;

import com.rhythm.user.shiro.realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * 输入自定义的域
     */
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        return new DefaultWebSecurityManager(myRealm());
    }

    /**
     * 注入 Shiro 过滤器
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置自定义的 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilterFactoryBean.setLoginUrl("/page/loginPage");
        // 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/page/success");
        // 设置未授权界面，权限认证失败会访问该 URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/nopermission");

        // LinkedHashMap 是有序的，进行顺序拦截器配置
        Map<String,String> filterChainMap = new LinkedHashMap<>();
        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        // filterChainMap.put("/templates/**", "anon");
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/fonts/**", "anon");
        filterChainMap.put("/images/**", "anon");
        filterChainMap.put("/layui/**", "anon");
        filterChainMap.put("/less/**", "anon");
        filterChainMap.put("/lib/**", "anon");
        // 登录 URL 放行
        filterChainMap.put("/page/loginPage", "anon");
        filterChainMap.put("/user/login", "anon");

        // 以“/user/admin” 开头的用户需要身份认证，authc 表示要进行身份认证
        //filterChainMap.put("/user/admin*", "authc");
        // “/user/student” 开头的用户需要角色认证，是“admin”才允许
        //filterChainMap.put("/user/student*/**", "authc, roles[admin]");
        // “/user/teacher” 开头的用户需要权限认证，是“user:create”才允许
        //filterChainMap.put("/user/teacher*/**", "authc, perms[user:create]");

        // 配置 logout 过滤器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/page/loginPage");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("logout", logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);
        filterChainMap.put("/page/logout", "logout");

        filterChainMap.put("/**", "authc");
        // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
