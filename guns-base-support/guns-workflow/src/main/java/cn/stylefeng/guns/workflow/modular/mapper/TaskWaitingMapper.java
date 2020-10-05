package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.Task;
import cn.stylefeng.guns.workflow.modular.model.params.TaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.TaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
public interface TaskWaitingMapper extends BaseMapper<Task> {

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<TaskResult> customList(@Param("paramCondition") TaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    Page<TaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") TaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TaskParam paramCondition);

    /**
     * 待办任务 or正在运行任务列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<Map<String, Object>> datalistPage(@Param("page") Page page, @Param("pd") HashMap<String, Object> param);

    /**
     * 获取当前登录人角色信息
     *
     * @author fengshuonan
     * @Date 2019-08-26
     */
    String getUserRoleString(String account);
}
