package cn.stylefeng.guns.oauth2.service;

import me.zhyd.oauth.model.AuthUser;

/**
 * 第三方登录服务
 *
 * @author fengshuonan
 * @Date 2019/6/9 17:53
 */
public interface LoginService {

    /**
     * 第三方登录
     *
     * @author fengshuonan
     * @Date 2019/6/9 18:21
     */
    String oauthLogin(AuthUser oauthUser);
}
