package cn.stylefeng.guns.workflow.modular.controller.base;

import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.workflow.core.consts.ActConst;
import cn.stylefeng.guns.workflow.core.consts.BpmsActivityTypeEnum;
import cn.stylefeng.guns.workflow.core.util.DelFileUtil;
import cn.stylefeng.guns.workflow.core.util.FileUpload;
import cn.stylefeng.guns.workflow.core.util.PathUtil;
import cn.stylefeng.guns.workflow.core.util.UtilMisc;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 流程业务相关
 *
 * @author fengshuonan
 * @Date 2019/8/22 21:59
 */
public class AcBusinessController extends BaseController {

    /**
     * 流程引擎对象
     */
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 管理流程定义  与流程定义和部署对象相关的Service
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 与正在执行的流程实例和执行对象相关的Service(执行管理，包括启动、推进、删除流程实例等操作)
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 任务管理 与正在执行的任务管理相关的Service
     */
    @Autowired
    private TaskService taskService;

    /**
     * 历史管理(执行完的数据的管理)
     */
    @Autowired
    private HistoryService historyService;

    /**
     * 指派任务的代理人
     *
     * @param assignee 代理人
     * @param taskId   任务ID
     */
    protected void setAssignee(String taskId, String assignee) {
        taskService.setAssignee(taskId, assignee);
    }

    /**
     * 设置流程变量(绑定任务)用Map形式
     *
     * @param taskId //任务ID
     * @param map
     */
    protected void setVariablesByTaskIdAsMap(String taskId, Map<String, Object> map) {
        taskService.setVariablesLocal(taskId, map);
    }

    /**
     * 获取流程变量
     *
     * @param taskId 任务ID
     * @param key    键
     */
    protected Object getVariablesByTaskIdAsMap(String taskId, String key) {
        return taskService.getVariable(taskId, key);
    }

    /**
     * 设置流程变量(不绑定任务)
     *
     * @param taskId 任务ID
     */
    protected void setVariablesByTaskId(String taskId, String key, String value) {
        taskService.setVariable(taskId, key, value);
    }

    /**
     * 移除流程变量(从正在运行中)
     *
     * @param procInstId 流程实例ID
     */
    protected void removeVariablesByPROC_INST_ID_(String procInstId, String key) {
        runtimeService.removeVariable(procInstId, key);
    }

    /**
     * 查询我的任务
     *
     * @param username
     * @return 返回任务列表
     */
    protected List<Task> findMyPersonalTask(String username) {

        //创建查询对象
        return taskService.createTaskQuery()

                //指定办理人
                .taskAssignee(username)

                //读出列表(比如从0到10)
                .list();
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     */
    protected void completeMyPersonalTask(String taskId) {
        taskService.complete(taskId);
    }

    /**
     * 作废流程
     *
     * @param processId 流程实例ID
     * @param reason    作废原因
     * @throws Exception
     */
    protected void deleteProcessInstance(String processId, String reason) throws Exception {
        runtimeService.deleteProcessInstance(processId, reason);
    }

    /**
     * 删除历史流程
     *
     * @param procInstId 流程实例ID
     * @throws Exception
     */
    protected void deleteHiProcessInstance(String procInstId) throws Exception {
        historyService.deleteHistoricProcessInstance(procInstId);
    }

    /**
     * 生成当前任务节点流程图片PNG
     *
     * @param procInstId 流程实例ID
     * @param filename   图片名称
     * @throws IOException
     */
    protected void createXmlAndPngAtNowTask(String procInstId, String filename) throws IOException {

        //生成先清空之前生成的文件
        DelFileUtil.delFolder(PathUtil.getProjectpath() + "uploadFiles/activitiFile");

        InputStream in = null;
        try {
            in = getResourceDiagramInputStream(procInstId);
        } finally {
            IoUtil.close(in);
        }

        //把文件上传到文件目录里面
        FileUpload.copyFile(in, PathUtil.getProjectpath() + ActConst.FILEACTIVITI, filename);
    }

    /**
     * 获取当前任务流程图片的输入流
     *
     * @param procInstId 流程实例ID
     * @return
     */
    private InputStream getResourceDiagramInputStream(String procInstId) {

        try {
            //获取历史流程实例
            HistoricProcessInstance hip = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            List<HistoricActivityInstance> hai = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInstId)

                    //获取流程中已经执行的节点，按照执行先后顺序排序
                    .orderByHistoricActivityInstanceId().asc().list();

            // 构造已执行的节点ID集合
            List<String> executedActivityIdList = new ArrayList<>();
            for (HistoricActivityInstance activityInstance : hai) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }

            // 获取bpmnModel
            BpmnModel bpmnModel = repositoryService.getBpmnModel(hip.getProcessDefinitionId());

            // 获取流程已发生流转的线ID集合
            List<String> flowIds = this.getExecutedFlows(bpmnModel, hai);
            ProcessDiagramGenerator processDiagramGenerator = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
            return processDiagramGenerator.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", null, 2.0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取流程已发生流转的线ID集合
     *
     * @param bpmnModel
     * @param historicActivityInstances 历史流程实例list
     * @return
     */
    private List<String> getExecutedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {

        //流转线ID集合
        List<String> flowIdList = new ArrayList<>();

        //全部活动实例
        List<FlowNode> historicFlowNodeList = new LinkedList<>();

        //已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstanceList = new LinkedList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            historicFlowNodeList.add((FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true));
            if (historicActivityInstance.getEndTime() != null) {
                finishedActivityInstanceList.add(historicActivityInstance);
            }
        }

        //遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        FlowNode currentFlowNode = null;
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstanceList) {

            //获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
            List<SequenceFlow> sequenceFlowList = currentFlowNode.getOutgoingFlows();

            /**
             * 遍历outgoingFlows并找到已流转的
             * 满足如下条件任务已流转：
             * 1.当前节点是并行网关或包含网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最近的流转节点视为有效流转
             */
            FlowNode targetFlowNode = null;
            if (BpmsActivityTypeEnum.PARALLEL_GATEWAY.getType().equals(currentActivityInstance.getActivityType())
                    || BpmsActivityTypeEnum.INCLUSIVE_GATEWAY.getType().equals(currentActivityInstance.getActivityType())) {

                //遍历历史活动节点，找到匹配Flow目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicFlowNodeList.contains(targetFlowNode)) {
                        flowIdList.add(sequenceFlow.getId());
                    }
                }
            } else {
                List<Map<String, String>> tempMapList = new LinkedList<>();

                //遍历历史活动节点，找到匹配Flow目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
                        if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                            tempMapList.add(UtilMisc.toMap("flowId", sequenceFlow.getId(), "activityStartTime", String.valueOf(historicActivityInstance.getStartTime().getTime())));
                        }
                    }
                }
                String flowId = null;
                for (Map<String, String> map : tempMapList) {
                    flowId = map.get("flowId");
                    flowIdList.add(flowId);
                }
            }
        }
        return flowIdList;
    }

    /**
     * 获取发起人
     *
     * @param procInstId 流程实例ID
     * @return
     */
    protected String getInitiator(String procInstId) {

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