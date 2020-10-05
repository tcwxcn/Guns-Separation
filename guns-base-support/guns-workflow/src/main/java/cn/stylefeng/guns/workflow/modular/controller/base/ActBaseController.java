package cn.stylefeng.guns.workflow.modular.controller.base;

import cn.stylefeng.guns.sys.core.util.FileDownload;
import cn.stylefeng.guns.workflow.core.consts.ActConst;
import cn.stylefeng.guns.workflow.core.consts.BpmsActivityTypeEnum;
import cn.stylefeng.guns.workflow.core.util.DelFileUtil;
import cn.stylefeng.guns.workflow.core.util.FileUpload;
import cn.stylefeng.guns.workflow.core.util.PathUtil;
import cn.stylefeng.guns.workflow.core.util.UtilMisc;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * 流程管理总类
 *
 * @author fengshuonan
 * @Date 2019/8/5 22:29
 */
public class ActBaseController extends BaseController {

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
     * 添加流程模型并返回modelId
     *
     * @param processId     流程唯一标识key
     * @param processAuthor 流程作者
     * @param name          流程名称
     * @param modelname     模型名称
     * @param description   模型描述
     * @param category      模型分类
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String createModel(String processId, String processAuthor, String name, String modelname, String description, String category) throws UnsupportedEncodingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvs");
        editorNode.put("resourceId", "canvs");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();

        //命名空间(禁止修改)
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");

        //流程节点作者
        stencilSetNode.put("author", "");
        editorNode.set("stencilset", stencilSetNode);
        ObjectNode propertiesNode = objectMapper.createObjectNode();

        //流程唯一标识
        propertiesNode.put("process_id", processId);

        //流程作者
        propertiesNode.put("process_author", processAuthor);

        //流程名称
        propertiesNode.put("name", name);
        editorNode.set("properties", propertiesNode);

        ObjectNode modelObjectNode = objectMapper.createObjectNode();

        //模型名称
        modelObjectNode.put("name", modelname);

        //模型版本
        modelObjectNode.put("revision", 1);

        //模型描述
        modelObjectNode.put("description", description);
        Model modelData = repositoryService.newModel();

        //模型分类
        modelData.setCategory(category);
        modelData.setDeploymentId(null);
        modelData.setKey(null);
        modelData.setMetaInfo(modelObjectNode.toString());

        //模型名称
        modelData.setName(modelname);
        modelData.setTenantId("");
        modelData.setVersion(1);

        //保存模型,存储数据到表：act_re_model 流程设计模型部署表
        repositoryService.saveModel(modelData);

        //保存资源,存储数据到表：act_ge_bytearray 二进制数据表
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

        return modelData.getId();
    }

    /**
     * 从流程定义映射模型
     *
     * @param processDefinitionId 流程定义ID
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
    protected Model saveModelFromPro(String processDefinitionId) throws Exception {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();

        //唯一标识
        modelData.setKey(processDefinition.getKey());

        //名称
        modelData.setName(processDefinition.getName() + "(反射)");

        //分类，默认行政审批分类
        modelData.setCategory("00102");
        modelData.setDeploymentId(processDefinition.getDeploymentId());

        //版本
        modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count() + 1)));

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        //保存模型
        repositoryService.saveModel(modelData);

        //保存资源
        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

        return modelData;
    }

    /**
     * 通过模型ID获取流程基本信息
     *
     * @param modelId 流程ID
     * @return
     * @throws JsonProcessingException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    protected Map<String, String> getProcessProperties(String modelId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                new String(repositoryService.getModelEditorSource(modelId), "utf-8")).get("properties");
        Map<String, String> map = new HashMap<>();

        //流程唯一标识(KEY)
        map.put("process_id", editorJsonNode.get("process_id").toString());

        //流程作者
        map.put("process_author", editorJsonNode.get("process_author").toString());

        //流程名称
        map.put("name", editorJsonNode.get("name").toString());
        return map;
    }

    /**
     * 删除模型
     *
     * @param modelId 模型ID
     */
    protected void deleteModel(String modelId) {

        //act_re_model 和  act_ge_bytearray 两张表中相关数据都删除
        repositoryService.deleteModel(modelId);
    }

    /**
     * 根据模型ID导出xml文件
     *
     * @param modelId 模型ID
     * @throws Exception
     */
    protected void exportXmlFromModelId(HttpServletResponse response, String modelId) throws Exception {
        Model modelData = repositoryService.getModel(modelId);
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(modelNode);
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
        ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
        //IOUtils.copy(in, response.getOutputStream());
        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
		/*response.setHeader("Content-Disposition", "attachment; filename=" + filename);  
		response.flushBuffer(); */
        FileUpload.copyFile(in, PathUtil.getProjectpath() + ActConst.FILEACTIVITI, filename);

        //把文件上传到文件目录里面
        FileDownload.fileDownload(response, PathUtil.getProjectpath() + ActConst.FILEACTIVITI + filename, filename);
        in.close();
    }

    /**
     * 根据模型ID预览xml文件
     *
     * @param modelId 模型ID
     * @throws Exception
     */
    protected String viewXmlFromModelId(String modelId) throws Exception {
        Model modelData = repositoryService.getModel(modelId);
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(modelNode);
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
        ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuffer xmlContent = new StringBuffer();
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            xmlContent.append(lineTxt);
            xmlContent.append("\n");
        }
        isr.close();
        return xmlContent.toString();
    }

    /**
     * 判断能否正常根据模型ID导出xml文件(当没有画流程图的时候会报异常)
     *
     * @param response
     * @param modelId  模型ID
     * @throws Exception
     */
    protected void isCanexportXmlFromModelId(HttpServletResponse response, String modelId) throws Exception {
        Model modelData = repositoryService.getModel(modelId);
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(modelNode);
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        xmlConverter.convertToXML(bpmnModel);
    }

    /**
     * 部署流程定义(根据modelId部署)
     *
     * @param modelId 模型ID
     * @return 部署ID
     */
    protected String deploymentProcessDefinitionFromModelId(String modelId) throws Exception {
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes = null;
        BpmnJsonConverter BpmnJsonConverter = new BpmnJsonConverter();
        BpmnModel model = BpmnJsonConverter.convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        String processName = modelData.getName() + ".bpmn20.xml";

        //部署名称
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name(modelData.getName());

        //完成部署
        Deployment deployment = deploymentBuilder.addString(processName, new String(bpmnBytes, "utf-8")).deploy();

        //部署ID
        return deployment.getId();
    }

    /**
     * 部署流程定义(从Classpath)
     *
     * @param name    部署名称
     * @param xmlpath xml文件路径
     * @param pngpath png文件路径
     * @return 部署ID
     */
    protected String deploymentProcessDefinitionFromClasspath(String name, String xmlpath, String pngpath) {

        //创建部署对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

        //部署名称
        deploymentBuilder.name(name);

        //从文件中读取xml资源
        deploymentBuilder.addClasspathResource(xmlpath);

        //从文件中读取png资源
        deploymentBuilder.addClasspathResource(pngpath);

        //完成部署
        Deployment deployment = deploymentBuilder.deploy();

        //部署ID
        return deployment.getId();
    }

    /**
     * 部署流程定义(从zip压缩包)
     *
     * @param name    部署名称
     * @param zippath zip文件路径
     * @return 部署ID
     * @throws FileNotFoundException
     */
    protected String deploymentProcessDefinitionFromZip(String name, String zippath) throws Exception {
        File outfile = new File(zippath);
        FileInputStream inputStream = new FileInputStream(outfile);
        ZipInputStream ipInputStream = new ZipInputStream(inputStream);

        //创建部署对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

        //部署名称
        deploymentBuilder.name(name);
        deploymentBuilder.addZipInputStream(ipInputStream);

        //完成部署
        Deployment deployment = deploymentBuilder.deploy();
        ipInputStream.close();
        inputStream.close();

        //部署ID
        return deployment.getId();
    }

    /**
     * 部署流程定义(从InputStream)
     *
     * @param name     部署名称
     * @param filepath 文件路径
     * @return 部署ID
     * @throws FileNotFoundException
     */
    protected String deploymentProcessDefinitionFromInputStream(String name, String filepath) throws Exception {

        if (ToolUtil.isEmpty(name)) {
            name = "无名称流程";
        }

        //末尾需要加上.bpmn20.xml
        name = name + ".bpmn20.xml";

        InputStream inputStream = new FileInputStream(filepath);

        //创建部署对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

        //部署名称
        deploymentBuilder.name(name);
        deploymentBuilder.addInputStream(name, inputStream);

        //完成部署
        Deployment deployment = deploymentBuilder.deploy();
        inputStream.close();

        //部署ID
        return deployment.getId();
    }

    /**
     * 根据流程定义的部署ID生成XML和PNG
     *
     * @param deploymentId 部署ID
     * @throws IOException
     */
    protected void createXmlAndPng(String deploymentId) throws IOException {

        //生成先清空之前生成的文件
        DelFileUtil.delFolder(PathUtil.getProjectpath() + "uploadFiles/activitiFile");

        List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
        for (String name : names) {
            if (name.indexOf("zip") != -1) {
                continue;
            }
            InputStream in = repositoryService.getResourceAsStream(deploymentId, name);

            //把文件上传到文件目录里面
            FileUpload.copyFile(in, PathUtil.getProjectpath() + ActConst.FILEACTIVITI, name);
            in.close();
        }
    }

    /**
     * 删除部署的流程
     *
     * @param deploymentId 部署ID
     * @throws IOException
     */
    protected void deleteDeployment(String deploymentId) throws Exception {

        //不带级联的删除，此删除只能删除没有启动的流程，否则抛出异常 .act_re_deployment，act_re_procdef 和  act_ge_bytearray 三张表中相关数据都删除
        //repositoryService.deleteDeployment(DEPLOYMENT_ID_);

        //级联删除，不管流程是否启动，都可以删除
        repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 激活流程定义
     *
     * @param deploymentId 流程定义ID
     */
    protected void activateProcessDefinitionById(String deploymentId) {
        repositoryService.activateProcessDefinitionById(deploymentId, true, null);
    }

    /**
     * 挂起流程定义
     *
     * @param deploymentId 流程定义ID
     */
    protected void suspendProcessDefinitionById(String deploymentId) {
        repositoryService.suspendProcessDefinitionById(deploymentId, true, null);
    }

    /**
     * 获取发起人
     *
     * @param procInstId 流程实例ID
     */
    protected String getInitiator(String procInstId) {

        //获取历史流程实例
        HistoricProcessInstance hip = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();

        //获取流程中已经执行的节点，按照执行先后顺序排序
        List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInstId)
                .orderByHistoricActivityInstanceId().asc().list();

        // 获取bpmnModel
        BpmnModel bpmnModel = repositoryService.getBpmnModel(hip.getProcessDefinitionId());

        //全部活动实例
        List<FlowNode> historicFlowNodeList = new LinkedList<>();
        for (HistoricActivityInstance hai : hais) {
            historicFlowNodeList.add((FlowNode) bpmnModel.getMainProcess().getFlowElement(hai.getActivityId(), true));
            if (hai.getAssignee() != null) {

                //不为空的第一个节点办理人就是发起人
                return hai.getAssignee();
            }
        }
        return null;
    }

    /**
     * 设置流程变量(绑定任务)用Map形式
     *
     * @param taskId 任务ID
     */
    protected void setVariablesByTaskIdAsMap(String taskId, Map<String, Object> map) {
        taskService.setVariablesLocal(taskId, map);
    }

    /**
     * 指派任务的代理人
     *
     * @param Assignee 代理人
     * @param taskId   任务ID
     */
    protected void setAssignee(String taskId, String Assignee) {
        taskService.setAssignee(taskId, Assignee);
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
     * 完成任务
     *
     * @param taskId 任务ID
     */
    protected void completeMyPersonalTask(String taskId) {
        taskService.complete(taskId);
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
     * 获取流程变量
     *
     * @param taskId 任务ID
     * @param key    键
     */
    protected Object getVariablesByTaskIdAsMap(String taskId, String key) {
        return taskService.getVariable(taskId, key);
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

        //把文件上传到文件目录里面
        InputStream in = null;
        try {
            in = getResourceDiagramInputStream(procInstId);
            FileUpload.copyFile(in, PathUtil.getProjectpath() + ActConst.FILEACTIVITI, filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
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

            //获取流程中已经执行的节点，按照执行先后顺序排序
            List<HistoricActivityInstance> hai = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInstId)
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
     * 作废流程
     *
     * @param processId 流程实例ID
     * @param reason    作废原因
     * @throws Exception
     */
    protected void deleteProcessInstance(String processId, String reason) {
        runtimeService.deleteProcessInstance(processId, reason);
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
     * 删除历史流程
     *
     * @param procInstId 流程实例ID
     */
    protected void deleteHiProcessInstance(String procInstId) {
        historyService.deleteHistoricProcessInstance(procInstId);
    }
}