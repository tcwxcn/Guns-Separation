package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Procinst;
import cn.stylefeng.guns.workflow.modular.mapper.HistoryProcessMapper;
import cn.stylefeng.guns.workflow.modular.model.params.ProcinstParam;
import cn.stylefeng.guns.workflow.modular.model.params.TaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcinstResult;
import cn.stylefeng.guns.workflow.modular.service.HistoryProcessService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author fengshuonan
 * @since 2019-08-19
 */
@Service
public class HistoryProcessServiceImpl extends ServiceImpl<HistoryProcessMapper, Procinst> implements HistoryProcessService {

    @Autowired
    private HistoryProcessMapper historyProcessMapper;

    @Override
    public void add(ProcinstParam param) {
        Procinst entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void update(ProcinstParam param) {
        Procinst oldEntity = getOldEntity(param);
        Procinst newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProcinstResult findBySpec(ProcinstParam param) {
        return null;
    }

    @Override
    public List<ProcinstResult> findListBySpec(ProcinstParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TaskParam param) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("keywords", param.getKeywords());
        paramMap.put("lastStart", param.getLastStart());
        paramMap.put("lastEnd", param.getLastEnd());

        Page pageContext = getPageContext();
        List<Map<String, Object>> maps = this.historyProcessMapper.datalistPage(pageContext, paramMap);
        pageContext.setRecords(maps);
        return LayuiPageFactory.createPageInfo(pageContext);
    }

    @Override
    public Map<String, Object> hivarList(String procInstId) {

        HashMap<String, Object> resultMap = new HashMap<>();

        List<Map<String, Object>> maps = historyProcessMapper.hivarList(procInstId);

        for (Map<String, Object> item : maps) {
            resultMap.put((String) item.get("name_"), item.get("text_"));
        }

        return resultMap;
    }

    private Serializable getKey(ProcinstParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Procinst getOldEntity(ProcinstParam param) {
        return this.getById(getKey(param));
    }

    private Procinst getEntity(ProcinstParam param) {
        Procinst entity = new Procinst();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
