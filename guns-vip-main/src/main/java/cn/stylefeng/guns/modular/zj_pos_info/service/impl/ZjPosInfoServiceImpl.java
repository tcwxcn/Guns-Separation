package cn.stylefeng.guns.modular.zj_pos_info.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_info.entity.ZjPosInfo;
import cn.stylefeng.guns.modular.zj_pos_info.mapper.ZjPosInfoMapper;
import cn.stylefeng.guns.modular.zj_pos_info.model.params.ZjPosInfoParam;
import cn.stylefeng.guns.modular.zj_pos_info.model.result.ZjPosInfoResult;
import  cn.stylefeng.guns.modular.zj_pos_info.service.ZjPosInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 职业信息表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
@Service
public class ZjPosInfoServiceImpl extends ServiceImpl<ZjPosInfoMapper, ZjPosInfo> implements ZjPosInfoService {

    @Override
    public void add(ZjPosInfoParam param){
        ZjPosInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZjPosInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjPosInfoParam param){
        ZjPosInfo oldEntity = getOldEntity(param);
        ZjPosInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjPosInfoResult findBySpec(ZjPosInfoParam param){
        return null;
    }

    @Override
    public List<ZjPosInfoResult> findListBySpec(ZjPosInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjPosInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjPosInfoParam param){
        return param.getPosInfoId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjPosInfo getOldEntity(ZjPosInfoParam param) {
        return this.getById(getKey(param));
    }

    private ZjPosInfo getEntity(ZjPosInfoParam param) {
        ZjPosInfo entity = new ZjPosInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
