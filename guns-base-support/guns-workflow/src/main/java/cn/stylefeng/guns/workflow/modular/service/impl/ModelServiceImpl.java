package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Model;
import cn.stylefeng.guns.workflow.modular.mapper.ModelMapper;
import cn.stylefeng.guns.workflow.modular.model.params.ModelParam;
import cn.stylefeng.guns.workflow.modular.model.result.ModelResult;
import  cn.stylefeng.guns.workflow.modular.service.ModelService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-06
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Override
    public void add(ModelParam param){
        Model entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ModelParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ModelParam param){
        Model oldEntity = getOldEntity(param);
        Model newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ModelResult findBySpec(ModelParam param){
        return null;
    }

    @Override
    public List<ModelResult> findListBySpec(ModelParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ModelParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageMapList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ModelParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Model getOldEntity(ModelParam param) {
        return this.getById(getKey(param));
    }

    private Model getEntity(ModelParam param) {
        Model entity = new Model();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
