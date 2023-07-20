package com.example.vueblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.example.vueblog.entity.User;
import com.example.vueblog.service.UserService;
import com.example.vueblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    //继承AuthorizingRealm，重写方法
    //为了让realm支持jwt的凭证校验token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //会到这里处理，强转成JwtToken
        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getCredentials()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if(user == null){throw new UnknownAccountException("账户不存在");}
        if(user.getStatus() == -1){
            throw new LockedAccountException("账户被锁定");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);  //传递数据

        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
