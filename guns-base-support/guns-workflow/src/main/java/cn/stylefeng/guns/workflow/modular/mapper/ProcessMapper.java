package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.Procdef;
import cn.stylefeng.guns.workflow.modular.model.params.ProcdefParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcdefResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-11
 */
public interface ProcessMapper extends BaseMapper<Procdef> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    List<ProcdefResult> customList(@Param("paramCondition") ProcdefParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ProcdefParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    Page<ProcdefResult> customPageList(@Param("page") Page page, @Param("paramCondition") ProcdefParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ProcdefParam paramCondition);

    /**
     * 激活or挂起任务(指定某个流程的所有任务)
     *
     * @author fengshuonan
     * @Date 2019/8/12 22:09
     */
    void onoffAllTask(@Param("id") String id, @Param("status") Integer status);

    /**
     * 激活or挂起任务(指定某个流程的一个任务)
     *
     * @author fengshuonan
     * @Date 2019/8/27 10:48
     */
    void onoffTask(@Param("id") String id, @Param("status") Integer status);

    /**
     * 流程变量列表
     *
     * @author fengshuonan
     * @Date 2019/8/21 14:08
     */
    List<Map<String, Object>> varList(String procInstId);

    /**
     * 历史任务节点列表
     *
     * @author fengshuonan
     * @Date 2019/8/21 14:08
     */
    List<Map<String, Object>> historyTaskList(String procInstId);

    /**
     * 历史流程变量列表
     *
     * @author fengshuonan
     * @Date 2019/8/21
     */
    List<Map<String, Object>> historyVariableList(String procInstId);
}
