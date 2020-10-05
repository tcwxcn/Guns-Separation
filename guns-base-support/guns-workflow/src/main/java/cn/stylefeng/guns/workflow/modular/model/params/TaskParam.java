package cn.stylefeng.guns.workflow.modular.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
@Data
public class TaskParam implements Serializable, BaseValidatingParam {

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

    private String keywords;

    private String lastStart;

    private String lastEnd;

    private String USERNAME;

    @Override
    public String checkParam() {
        return null;
    }

}
