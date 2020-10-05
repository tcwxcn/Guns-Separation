package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;

import java.util.List;
import java.util.Map;

/**
 * 历史任务服务
 *
 * @author fengshuonan
 * @Date 2019-08-28 17:52
 */
public interface TaskHistoryService {

    /**
     * 历史任务列表分页
     *
     * @author fengshuonan
     * @Date 2019-08-28 17:53
     */
    LayuiPageInfo historyTaskListPage(Map<String, Object> map);

    /**
     * 历史任务列表
     *
     * @author fengshuonan
     * @Date 2019-08-28 17:53
     */
    List<Map<String, Object>> historyTaskList(String procInstId);

}
