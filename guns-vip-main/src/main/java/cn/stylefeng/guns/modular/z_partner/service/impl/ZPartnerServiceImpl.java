package cn.stylefeng.guns.modular.z_partner.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_partner.entity.ZPartner;
import cn.stylefeng.guns.modular.z_partner.mapper.ZPartnerMapper;
import cn.stylefeng.guns.modular.z_partner.model.params.ZPartnerParam;
import cn.stylefeng.guns.modular.z_partner.model.result.ZPartnerResult;
import cn.stylefeng.guns.modular.z_partner.service.ZPartnerService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 合作伙伴表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
@Service
public class ZPartnerServiceImpl extends ServiceImpl<ZPartnerMapper, ZPartner> implements ZPartnerService {

    @Override
    public void add(ZPartnerParam param){
        ZPartner entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZPartnerParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZPartnerParam param){
        ZPartner oldEntity = getOldEntity(param);
        ZPartner newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZPartnerResult findBySpec(ZPartnerParam param){
        return null;
    }

    @Override
    public List<ZPartnerResult> findListBySpec(ZPartnerParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZPartnerParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZPartnerParam param){
        return param.getPartnerId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZPartner getOldEntity(ZPartnerParam param) {
        return this.getById(getKey(param));
    }

    private ZPartner getEntity(ZPartnerParam param) {
        ZPartner entity = new ZPartner();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
