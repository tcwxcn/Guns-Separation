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
 * @author stylefeng
 * @since 2019-08-22
 */
@Data
public class RuTaskParam implements Serializable, BaseValidatingParam {

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

    /**
     * 关键字检索
     */
    private String keywords;

    /**
     * 开始时间
     */
    private String lastStart;

    /**
     * 结束时间
     */
    private String lastEnd;

    /**
     * 当前办理人
     */
    private String username;

    @Override
    public String checkParam() {
        return null;
    }

}
