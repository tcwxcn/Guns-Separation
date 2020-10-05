package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Procdef;
import cn.stylefeng.guns.workflow.modular.model.params.ProcdefParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcdefResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-11
 */
public interface ProcessService extends IService<Procdef> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    void add(ProcdefParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    void delete(ProcdefParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    void update(ProcdefParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    ProcdefResult findBySpec(ProcdefParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    List<ProcdefResult> findListBySpec(ProcdefParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    LayuiPageInfo findPageBySpec(ProcdefParam param);

    /**
     * 激活or挂起任务(指定某个流程的所有任务)
     *
     * @author fengshuonan
     * @Date 2019/8/12 22:08
     */
    void onoffAllTask(String id, Integer status);

    /**
     * 激活or挂起任务(指定某个流程的一个任务)
     *
     * @author fengshuonan
     * @Date 2019/8/27 10:49
     */
    void onoffTask(String id, Integer status);

    /**
     * 流程变量列表
     *
     * @author fengshuonan
     * @Date 2019/8/21 14:08
     */
    Map<String, Object> varList(String procInstId);

    /**
     * 历史任务节点列表
     *
     * @author fengshuonan
     * @Date 2019/8/21 14:08
     */
    List<Map<String, Object>> hitoryTaskList(String procInstId);

}
