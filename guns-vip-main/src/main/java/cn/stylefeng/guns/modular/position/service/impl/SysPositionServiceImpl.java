package cn.stylefeng.guns.modular.position.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.position.entity.SysPosition;
import cn.stylefeng.guns.modular.position.mapper.SysPositionMapper;
import cn.stylefeng.guns.modular.position.model.params.SysPositionParam;
import cn.stylefeng.guns.modular.position.model.result.SysPositionResult;
import  cn.stylefeng.guns.modular.position.service.SysPositionService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
@Service
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPosition> implements SysPositionService {

    @Override
    public void add(SysPositionParam param){
        SysPosition entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SysPositionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SysPositionParam param){
        SysPosition oldEntity = getOldEntity(param);
        SysPosition newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SysPositionResult findBySpec(SysPositionParam param){
        return null;
    }

    @Override
    public List<SysPositionResult> findListBySpec(SysPositionParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(SysPositionParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(SysPositionParam param){
        return param.getPositionId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private SysPosition getOldEntity(SysPositionParam param) {
        return this.getById(getKey(param));
    }

    private SysPosition getEntity(SysPositionParam param) {
        SysPosition entity = new SysPosition();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
