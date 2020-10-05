package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.RuTask;
import cn.stylefeng.guns.workflow.modular.model.params.RuTaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.RuTaskResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-22
 */
public interface TaskRunningMapper extends BaseMapper<RuTask> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    List<RuTaskResult> customList(@Param("paramCondition") RuTaskParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") RuTaskParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    Page<RuTaskResult> customPageList(@Param("page") Page page, @Param("paramCondition") RuTaskParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") RuTaskParam paramCondition);

    /**
     * 历史任务
     *
     * @author fengshuonan
     * @Date 2019-08-22
     */
    List<Map<String, Object>> hitasklist(Page page, @Param("pd") Map<String, Object> map);
}
