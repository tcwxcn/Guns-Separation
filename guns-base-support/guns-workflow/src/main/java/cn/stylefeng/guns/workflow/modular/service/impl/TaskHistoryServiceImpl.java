package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.core.util.TimeCalcUtil;
import cn.stylefeng.guns.workflow.modular.entity.RuTask;
import cn.stylefeng.guns.workflow.modular.mapper.TaskHistoryMapper;
import cn.stylefeng.guns.workflow.modular.mapper.TaskRunningMapper;
import cn.stylefeng.guns.workflow.modular.service.TaskHistoryService;
import cn.stylefeng.guns.workflow.modular.service.TaskWaitingService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 历史任务
 *
 * @author fengshuonan
 * @Date 2019-08-28 17:54
 */
@Service
public class TaskHistoryServiceImpl extends ServiceImpl<TaskRunningMapper, RuTask> implements TaskHistoryService {

    @Autowired
    private TaskHistoryMapper ruprocdefMapper;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskWaitingService taskService;

    @Override
    public LayuiPageInfo historyTaskListPage(Map<String, Object> map) {

        map.put("username", LoginContextHolder.getContext().getUser().getAccount());

        String userRoleString = taskService.getUserRoleString();

        map.put("rnumbers", userRoleString);

        Page pageContext = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> list = this.baseMapper.hitasklist(pageContext, map);

        for (int i = 0; i < list.size(); i++) {
            Long ztime = Long.parseLong(list.get(i).get("duration_").toString());
            list.get(i).put("ztime", TimeCalcUtil.calc(ztime));

            //流程申请人
            list.get(i).put("initator", getInitiator(String.valueOf(list.get(i).get("proc_inst_id_"))));
        }

        pageContext.setRecords(list);
        return LayuiPageFactory.createPageInfo(pageContext);
    }

    @Override
    public List<Map<String, Object>> historyTaskList(String procInstId) {
        return ruprocdefMapper.historyTaskList(procInstId);
    }

    /**
     * 获取发起人
     *
     * @author fengshuonan
     * @Date 2019-8-23 17:37
     */
    private String getInitiator(String procInstId) {

        //获取历史流程实例
        HistoricProcessInstance hip = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInstId)

                //获取流程中已经执行的节点，按照执行先后顺序排序
                .orderByHistoricActivityInstanceId().asc().list();

        // 获取bpmnModel
        BpmnModel bpmnModel = repositoryService.getBpmnModel(hip.getProcessDefinitionId());

        //全部活动实例
        List<FlowNode> historicFlowNodeList = new LinkedList<FlowNode>();
        for (HistoricActivityInstance hai : hais) {
            historicFlowNodeList.add((FlowNode) bpmnModel.getMainProcess().getFlowElement(hai.getActivityId(), true));
            if (hai.getAssignee() != null) {

                //不为空的第一个节点办理人就是发起人
                return hai.getAssignee();
            }
        }

        return null;
    }
}
