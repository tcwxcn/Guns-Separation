package cn.stylefeng.guns.workflow.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("act_hi_procinst")
public class Procinst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_", type = IdType.ID_WORKER)
    private String id;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("BUSINESS_KEY_")
    private String businessKey;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("START_TIME_")
    private Date startTime;

    @TableField("END_TIME_")
    private Date endTime;

    @TableField("DURATION_")
    private Long duration;

    @TableField("START_USER_ID_")
    private String startUserId;

    @TableField("START_ACT_ID_")
    private String startActId;

    @TableField("END_ACT_ID_")
    private String endActId;

    @TableField("SUPER_PROCESS_INSTANCE_ID_")
    private String superProcessInstanceId;

    @TableField("DELETE_REASON_")
    private String deleteReason;

    @TableField("TENANT_ID_")
    private String tenantId;

    @TableField("NAME_")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartActId() {
        return startActId;
    }

    public void setStartActId(String startActId) {
        this.startActId = startActId;
    }

    public String getEndActId() {
        return endActId;
    }

    public void setEndActId(String endActId) {
        this.endActId = endActId;
    }

    public String getSuperProcessInstanceId() {
        return superProcessInstanceId;
    }

    public void setSuperProcessInstanceId(String superProcessInstanceId) {
        this.superProcessInstanceId = superProcessInstanceId;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Procinst{" +
        "id=" + id +
        ", procInstId=" + procInstId +
        ", businessKey=" + businessKey +
        ", procDefId=" + procDefId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", duration=" + duration +
        ", startUserId=" + startUserId +
        ", startActId=" + startActId +
        ", endActId=" + endActId +
        ", superProcessInstanceId=" + superProcessInstanceId +
        ", deleteReason=" + deleteReason +
        ", tenantId=" + tenantId +
        ", name=" + name +
        "}";
    }
}
