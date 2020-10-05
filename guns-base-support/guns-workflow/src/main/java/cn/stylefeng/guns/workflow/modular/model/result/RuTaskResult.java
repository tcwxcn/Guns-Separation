package cn.stylefeng.guns.workflow.modular.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-22
 */
@Data
public class RuTaskResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    private Integer rev;

    private String executionId;

    private String procInstId;

    private String procDefId;

    private String name;

    private String parentTaskId;

    private String description;

    private String taskDefKey;

    private String owner;

    private String assignee;

    private String delegation;

    private Integer priority;

    private Date createTime;

    private Date dueDate;

    private String category;

    private Integer suspensionState;

    private String tenantId;

    private String formKey;

    private Date claimTime;

}
