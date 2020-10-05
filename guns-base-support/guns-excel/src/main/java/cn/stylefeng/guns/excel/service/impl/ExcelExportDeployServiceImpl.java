package cn.stylefeng.guns.excel.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.excel.entity.ExcelExportDeploy;
import cn.stylefeng.guns.excel.mapper.ExcelExportDeployMapper;
import cn.stylefeng.guns.excel.model.params.ExcelExportDeployParam;
import cn.stylefeng.guns.excel.model.result.ExcelExportDeployResult;
import cn.stylefeng.guns.excel.service.ExcelExportDeployService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * excel导出配置 服务实现类
 * </p>
 *
 * @author York
 * @since 2019-11-26
 */
@Service
public class ExcelExportDeployServiceImpl extends ServiceImpl<ExcelExportDeployMapper, ExcelExportDeploy> implements ExcelExportDeployService {
    @Override
    public void add(ExcelExportDeployParam param) {
        ExcelExportDeploy entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ExcelExportDeployParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(ExcelExportDeployParam param) {
        ExcelExportDeploy oldEntity = getOldEntity(param);
        ExcelExportDeploy newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ExcelExportDeploy findBySpec(ExcelExportDeploy param) {
        QueryWrapper<ExcelExportDeploy> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(param);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ExcelExportDeployResult> findListBySpec(ExcelExportDeployParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ExcelExportDeployParam param) {
        Page<ExcelExportDeployResult> pageContext = getPageContext();
        IPage<ExcelExportDeployResult> page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ExcelExportDeployParam param) {
        return param.getId();
    }

    private Page<ExcelExportDeployResult> getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ExcelExportDeploy getOldEntity(ExcelExportDeployParam param) {
        return this.getById(getKey(param));
    }

    private ExcelExportDeploy getEntity(ExcelExportDeployParam param) {
        ExcelExportDeploy entity = new ExcelExportDeploy();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }
}
