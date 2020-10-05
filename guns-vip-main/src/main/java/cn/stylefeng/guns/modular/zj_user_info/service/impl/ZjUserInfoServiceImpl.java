package cn.stylefeng.guns.modular.zj_user_info.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_user_info.entity.ZjUserInfo;
import cn.stylefeng.guns.modular.zj_user_info.mapper.ZjUserInfoMapper;
import cn.stylefeng.guns.modular.zj_user_info.model.params.ZjUserInfoParam;
import cn.stylefeng.guns.modular.zj_user_info.model.result.ZjUserInfoResult;
import  cn.stylefeng.guns.modular.zj_user_info.service.ZjUserInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-22
 */
@Service
public class ZjUserInfoServiceImpl extends ServiceImpl<ZjUserInfoMapper, ZjUserInfo> implements ZjUserInfoService {

    @Override
    public void add(ZjUserInfoParam param){
        ZjUserInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZjUserInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjUserInfoParam param){
        ZjUserInfo oldEntity = getOldEntity(param);
        ZjUserInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjUserInfoResult findBySpec(ZjUserInfoParam param){
        ZjUserInfoResult result = baseMapper.findByPhone(param.getPhone());
        return result;
    }

    @Override
    public List<ZjUserInfoResult> findListBySpec(ZjUserInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjUserInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjUserInfoParam param){
        return param.getUserInfoId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjUserInfo getOldEntity(ZjUserInfoParam param) {
        return this.getById(getKey(param));
    }

    private ZjUserInfo getEntity(ZjUserInfoParam param) {
        ZjUserInfo entity = new ZjUserInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
