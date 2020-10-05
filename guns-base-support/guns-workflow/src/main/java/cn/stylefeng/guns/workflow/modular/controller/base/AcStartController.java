package cn.stylefeng.guns.workflow.modular.controller.base;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 启动流程用
 *
 * @author fengshuonan
 * @Date 2019-08-27 18:21
 */
public class AcStartController extends BaseController {

    /**
     * 与正在执行的流程实例和执行对象相关的Service(执行管理，包括启动、推进、删除流程实例等操作)
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 通过KEY启动流程实例(不带变量)
     *
     * @param processInstanceKey 流程定义的KEY
     * @return 返回流程实例ID
     */
    protected String startProcessInstanceByKey(String processInstanceKey) {

        //用流程定义的KEY启动，会自动选择KEY相同的流程定义中最新版本的那个(KEY为模型中的流程唯一标识)
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceKey);

        //返回流程实例ID
        return processInstance.getId();
    }

    /**
     * 通过KEY启动流程实例(带变量)
     *
     * @param processInstanceKey 流程定义的KEY
     * @return 返回流程实例ID
     */
    protected String startProcessInstanceByKeyHasVariables(String processInstanceKey, Map<String, Object> map) {

        //map存储变量 用流程定义的KEY启动，会自动选择KEY相同的流程定义中最新版本的那个(KEY为模型中的流程唯一标识)
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceKey, map);

        //返回流程实例ID
        return processInstance.getId();
    }

    /**
     * 通过ID启动流程实例
     *
     * @param processInstanceId 流程定义的ID
     * @return 返回流程实例ID
     */
    protected String startProcessInstanceById(String processInstanceId) {

        //用流程定义的ID启动
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processInstanceId);

        //返回流程实例ID
        return processInstance.getId();
    }

}
