package com.xin.config;


import com.xin.pojo.User;
import com.xin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义的  UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 通过  认证里的  return new SimpleAuthenticationInfo(user,user.getPwd(),"");
        // 拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        info.addStringPermission(currentUser.getPerms());

        // return info;  因为授权要授权info里的
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        // 用户名 ，密码  数据库中取

        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;

        User user = userService.queryUserByName(usertoken.getUsername());

        if (user==null) {
            return null; // 抛出异常   UnknownAccountException
        }


        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        // 密码认证，shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
