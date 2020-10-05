package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Procinst;
import cn.stylefeng.guns.workflow.modular.model.params.ProcinstParam;
import cn.stylefeng.guns.workflow.modular.model.params.TaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcinstResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
public interface HistoryProcessService extends IService<Procinst> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    void add(ProcinstParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    void update(ProcinstParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    ProcinstResult findBySpec(ProcinstParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<ProcinstResult> findListBySpec(ProcinstParam param);

    /**
     * 历史流程
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    LayuiPageInfo findPageBySpec(TaskParam param);

    /**
     * 历史流程相关变量
     *
     * @author fengshuonan
     * @Date 2019-08-28 16:04
     */
    Map<String, Object> hivarList(String procInstId);

}
