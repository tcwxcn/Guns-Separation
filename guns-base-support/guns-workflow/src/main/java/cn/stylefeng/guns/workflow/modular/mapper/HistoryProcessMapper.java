package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.Procinst;
import cn.stylefeng.guns.workflow.modular.model.params.ProcinstParam;
import cn.stylefeng.guns.workflow.modular.model.result.ProcinstResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
public interface HistoryProcessMapper extends BaseMapper<Procinst> {

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<ProcinstResult> customList(@Param("paramCondition") ProcinstParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ProcinstParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    Page<ProcinstResult> customPageList(@Param("page") Page page, @Param("paramCondition") ProcinstParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ProcinstParam paramCondition);

    /**
     * 历史流程列表
     *
     * @author fengshuonan
     * @Date 2019-08-28 16:23
     */
    List<Map<String, Object>> datalistPage(@Param("page") Page page, @Param("pd") HashMap<String, Object> param);

    /**
     * 获取历史流程变量
     *
     * @author fengshuonan
     * @Date 2019-08-28 16:23
     */
    List<Map<String, Object>> hivarList(@Param("PROC_INST_ID_") String procInstId);

}
