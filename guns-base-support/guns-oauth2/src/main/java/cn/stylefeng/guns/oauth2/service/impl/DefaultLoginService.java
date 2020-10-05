package cn.stylefeng.guns.oauth2.service.impl;

import cn.stylefeng.guns.base.auth.service.AuthService;
import cn.stylefeng.guns.base.oauth2.service.OauthUserInfoService;
import cn.stylefeng.guns.oauth2.service.LoginService;
import cn.stylefeng.guns.sys.core.exception.oauth.OAuthExceptionEnum;
import cn.stylefeng.guns.sys.core.exception.oauth.OAuthLoginException;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.guns.base.oauth2.entity.OauthUserInfo;
import cn.stylefeng.guns.oauth2.factory.OAuthUserInfoFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 默认第三方登录逻辑
 *
 * @author fengshuonan
 * @Date 2019/6/9 18:16
 */
@Service
public class DefaultLoginService implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private OauthUserInfoService oauthUserInfoService;

    @Autowired
    private AuthService authService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String oauthLogin(AuthUser oauthUser) {

        if (oauthUser == null) {
            throw new OAuthLoginException(OAuthExceptionEnum.OAUTH_RESPONSE_ERROR);
        }

        //当前无登录用户，创建用户或根据已有绑定用户的账号登录
        String account = getOauthUserAccount(oauthUser);

        //执行原有系统登录逻辑
        return authService.login(account);
    }


    /**
     * 绑定当前用户的source和openId
     *
     * @author fengshuonan
     * @Date 2019/6/9 18:51
     */
    private void bindOAuthUser(Long userId, AuthUser oauthUser) {

        //先判断当前系统这个openId有没有人用
        QueryWrapper<OauthUserInfo> queryWrapper = new QueryWrapper<OauthUserInfo>()
                .eq("source", oauthUser.getSource().name())
                .and(i -> i.eq("uuid", oauthUser.getUuid()))
                .and(i -> i.ne("user_id", userId));
        List<OauthUserInfo> oauthUserInfos = this.oauthUserInfoService.list(queryWrapper);

        //已有人绑定，抛出异常
        if (oauthUserInfos != null && oauthUserInfos.size() > 0) {
            throw new OAuthLoginException(OAuthExceptionEnum.OPEN_ID_ALREADY_BIND);
        } else {
            //新建一条绑定记录
            OauthUserInfo oAuthUserInfo = OAuthUserInfoFactory.createOAuthUserInfo(userId, oauthUser);
            this.oauthUserInfoService.save(oAuthUserInfo);
        }

    }

    /**
     * 通过第三方登录的信息创建本系统用户
     *
     * @author fengshuonan
     * @Date 2019/6/9 19:07
     */
    private String getOauthUserAccount(AuthUser oauthUser) {

        //先判断当前系统这个openId有没有人用
        QueryWrapper<OauthUserInfo> queryWrapper = new QueryWrapper<OauthUserInfo>()
                .eq("source", oauthUser.getSource().name())
                .and(i -> i.eq("uuid", oauthUser.getUuid()));
        OauthUserInfo oauthUserInfos = this.oauthUserInfoService.getOne(queryWrapper);

        //已有人绑定,直接返回这个人的账号，进行登录
        if (oauthUserInfos != null) {
            Long userId = oauthUserInfos.getUserId();
            return this.userService.getById(userId).getAccount();
        } else {

            //没有人绑定的创建这个人的本系统用户
            User user = OAuthUserInfoFactory.createOAuthUser(oauthUser);
            this.userService.save(user);

            //新建一条oauth2绑定记录
            OauthUserInfo oAuthUserInfo = OAuthUserInfoFactory.createOAuthUserInfo(user.getUserId(), oauthUser);
            this.oauthUserInfoService.save(oAuthUserInfo);

            return user.getAccount();
        }
    }

}
