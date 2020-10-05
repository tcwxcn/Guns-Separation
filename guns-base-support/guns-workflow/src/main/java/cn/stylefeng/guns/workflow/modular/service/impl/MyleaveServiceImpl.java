package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Myleave;
import cn.stylefeng.guns.workflow.modular.mapper.MyleaveMapper;
import cn.stylefeng.guns.workflow.modular.model.params.MyLeaveParam;
import cn.stylefeng.guns.workflow.modular.model.result.MyleaveResult;
import cn.stylefeng.guns.workflow.modular.service.MyleaveService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
@Service
public class MyleaveServiceImpl extends ServiceImpl<MyleaveMapper, Myleave> implements MyleaveService {

    @Autowired
    private MyleaveMapper myleaveMapper;

    @Override
    public void add(MyLeaveParam param) {
        Myleave entity = getEntity(param);
        entity.setUsername(LoginContextHolder.getContext().getUser().getAccount());
        this.myleaveMapper.insert(entity);
    }

    @Override
    public void delete(MyLeaveParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(MyLeaveParam param) {
        Myleave oldEntity = getOldEntity(param);
        Myleave newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public MyleaveResult findBySpec(MyLeaveParam param) {
        return null;
    }

    @Override
    public List<MyleaveResult> findListBySpec(MyLeaveParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(MyLeaveParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.datalistPage(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void batchDeleteByIds(List<String> ids) {
        if (ToolUtil.isNotEmpty(ids)) {
            myleaveMapper.deleteBatchIds(ids);
        }
    }

    private Serializable getKey(MyLeaveParam param) {
        return param.getMyleaveId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Myleave getOldEntity(MyLeaveParam param) {
        return this.getById(getKey(param));
    }

    private Myleave getEntity(MyLeaveParam param) {
        Myleave entity = new Myleave();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public List<Map<String, Object>> getLeaves() {
        return myleaveMapper.getLeaves();
    }
}
