package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Procdef;
import cn.stylefeng.guns.workflow.modular.mapper.ProcessMapper;
import cn.stylefeng.guns.workflow.modular.model.params.ProcdefParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcdefResult;
import cn.stylefeng.guns.workflow.modular.service.ProcessService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-11
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Procdef> implements ProcessService {

    @Override
    public void add(ProcdefParam param) {
        Procdef entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ProcdefParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(ProcdefParam param) {
        Procdef oldEntity = getOldEntity(param);
        Procdef newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProcdefResult findBySpec(ProcdefParam param) {
        return null;
    }

    @Override
    public List<ProcdefResult> findListBySpec(ProcdefParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ProcdefParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void onoffAllTask(String id, Integer status) {
        this.baseMapper.onoffAllTask(id, status);
    }

    @Override
    public void onoffTask(String id, Integer status) {
        this.baseMapper.onoffTask(id, status);
    }

    @Override
    public Map<String, Object> varList(String procInstId) {

        HashMap<String, Object> resultMap = new HashMap<>();

        List<Map<String, Object>> maps = this.baseMapper.varList(procInstId);

        for (Map<String, Object> item : maps) {
            resultMap.put((String) item.get("name_"), item.get("text_"));
        }

        return resultMap;
    }

    @Override
    public List<Map<String, Object>> hitoryTaskList(String procInstId) {
        return this.baseMapper.historyTaskList(procInstId);
    }

    private Serializable getKey(ProcdefParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Procdef getOldEntity(ProcdefParam param) {
        return this.getById(getKey(param));
    }

    private Procdef getEntity(ProcdefParam param) {
        Procdef entity = new Procdef();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
