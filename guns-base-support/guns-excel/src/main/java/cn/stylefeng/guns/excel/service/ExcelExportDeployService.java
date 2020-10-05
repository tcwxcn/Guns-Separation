package cn.stylefeng.guns.excel.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.excel.entity.ExcelExportDeploy;
import cn.stylefeng.guns.excel.model.params.ExcelExportDeployParam;
import cn.stylefeng.guns.excel.model.result.ExcelExportDeployResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * excel导出配置 服务类
 * </p>
 *
 * @author York
 * @since 2019-11-26
 */
public interface ExcelExportDeployService extends IService<ExcelExportDeploy> {

    /**
     * 新增
     *
     * @author York
     * @Date 2019-11-26
     */
    void add(ExcelExportDeployParam param);

    /**
     * 删除
     *
     * @author York
     * @Date 2019-11-26
     */
    void delete(ExcelExportDeployParam param);

    /**
     * 更新
     *
     * @author York
     * @Date 2019-11-26
     */
    void update(ExcelExportDeployParam param);

    /**
     * 查询单条数据
     *
     * @author York
     * @Date 2019-11-26
     */
    public ExcelExportDeploy findBySpec(ExcelExportDeploy param);

    /**
     * 查询列表，Specification模式
     *
     * @author York
     * @Date 2019-11-26
     */
    List<ExcelExportDeployResult> findListBySpec(ExcelExportDeployParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author York
     * @Date 2019-11-26
     */
    LayuiPageInfo findPageBySpec(ExcelExportDeployParam param);
}
