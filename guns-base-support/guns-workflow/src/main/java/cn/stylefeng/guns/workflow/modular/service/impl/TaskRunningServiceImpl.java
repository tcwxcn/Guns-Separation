package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.RuTask;
import cn.stylefeng.guns.workflow.modular.mapper.TaskRunningMapper;
import cn.stylefeng.guns.workflow.modular.model.params.RuTaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.RuTaskResult;
import cn.stylefeng.guns.workflow.modular.service.TaskRunningService;
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
 * @since 2019-08-22
 */
@Service
public class TaskRunningServiceImpl extends ServiceImpl<TaskRunningMapper, RuTask> implements TaskRunningService {

    @Override
    public void add(RuTaskParam param){
        RuTask entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RuTaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RuTaskParam param){
        RuTask oldEntity = getOldEntity(param);
        RuTask newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RuTaskResult findBySpec(RuTaskParam param){
        return null;
    }

    @Override
    public List<RuTaskResult> findListBySpec(RuTaskParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(RuTaskParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageMapList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(RuTaskParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private RuTask getOldEntity(RuTaskParam param) {
        return this.getById(getKey(param));
    }

    private RuTask getEntity(RuTaskParam param) {
        RuTask entity = new RuTask();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
