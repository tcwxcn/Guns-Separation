package cn.stylefeng.guns.modular.zj_pos_type.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_type.entity.ZjPosType;
import cn.stylefeng.guns.modular.zj_pos_type.mapper.ZjPosTypeMapper;
import cn.stylefeng.guns.modular.zj_pos_type.model.params.ZjPosTypeParam;
import cn.stylefeng.guns.modular.zj_pos_type.model.result.ZjPosTypeResult;
import  cn.stylefeng.guns.modular.zj_pos_type.service.ZjPosTypeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 职位类型表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
@Service
public class ZjPosTypeServiceImpl extends ServiceImpl<ZjPosTypeMapper, ZjPosType> implements ZjPosTypeService {

    @Override
    public void add(ZjPosTypeParam param){
        ZjPosType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZjPosTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjPosTypeParam param){
        ZjPosType oldEntity = getOldEntity(param);
        ZjPosType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjPosTypeResult findBySpec(ZjPosTypeParam param){
        return null;
    }

    @Override
    public List<ZjPosTypeResult> findListBySpec(ZjPosTypeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjPosTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjPosTypeParam param){
        return param.getPosTypeId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjPosType getOldEntity(ZjPosTypeParam param) {
        return this.getById(getKey(param));
    }

    private ZjPosType getEntity(ZjPosTypeParam param) {
        ZjPosType entity = new ZjPosType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
