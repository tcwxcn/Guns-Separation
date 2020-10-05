package cn.stylefeng.guns.modular.zj_position_name.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_position_name.entity.ZjPositionName;
import cn.stylefeng.guns.modular.zj_position_name.mapper.ZjPositionNameMapper;
import cn.stylefeng.guns.modular.zj_position_name.model.params.ZjPositionNameParam;
import cn.stylefeng.guns.modular.zj_position_name.model.result.ZjPositionNameResult;
import  cn.stylefeng.guns.modular.zj_position_name.service.ZjPositionNameService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工作职位名称表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
@Service
public class ZjPositionNameServiceImpl extends ServiceImpl<ZjPositionNameMapper, ZjPositionName> implements ZjPositionNameService {

    @Override
    public void add(ZjPositionNameParam param){
        ZjPositionName entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZjPositionNameParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjPositionNameParam param){
        ZjPositionName oldEntity = getOldEntity(param);
        ZjPositionName newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjPositionNameResult findBySpec(ZjPositionNameParam param){
        return null;
    }

    @Override
    public List<ZjPositionNameResult> findListBySpec(ZjPositionNameParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjPositionNameParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjPositionNameParam param){
        return param.getPositionId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjPositionName getOldEntity(ZjPositionNameParam param) {
        return this.getById(getKey(param));
    }

    private ZjPositionName getEntity(ZjPositionNameParam param) {
        ZjPositionName entity = new ZjPositionName();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
