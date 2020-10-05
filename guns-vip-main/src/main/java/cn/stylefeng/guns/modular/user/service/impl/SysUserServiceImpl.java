package cn.stylefeng.guns.modular.user.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.user.entity.SysUser;
import cn.stylefeng.guns.modular.user.mapper.SysUserMapper;
import cn.stylefeng.guns.modular.user.model.params.SysUserParam;
import cn.stylefeng.guns.modular.user.model.result.SysUserResult;
import  cn.stylefeng.guns.modular.user.service.SysUserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public void add(SysUserParam param){
        SysUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SysUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SysUserParam param){
        SysUser oldEntity = getOldEntity(param);
        SysUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SysUserResult findBySpec(SysUserParam param){
        return null;
    }

    @Override
    public List<SysUserResult> findListBySpec(SysUserParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SysUserParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SysUserParam param){
        return param.getUserId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SysUser getOldEntity(SysUserParam param) {
        return this.getById(getKey(param));
    }

    private SysUser getEntity(SysUserParam param) {
        SysUser entity = new SysUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
