package cn.stylefeng.guns.workflow.modular.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 历史任务
 *
 * @author fengshuonan
 * @Date 2019-08-28 18:01
 */
public interface TaskHistoryMapper {

    /**
     * 历史任务列表
     *
     * @author fengshuonan
     * @Date 2019-08-28 18:01
     */
    List<Map<String, Object>> historyTaskList(@Param("PROC_INST_ID_") String procInstId);
    
}
