package cn.stylefeng.guns.workflow.modular.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-06
 */
@TableName("act_re_model")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_", type = IdType.ID_WORKER)
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("NAME_")
    private String name;

    @TableField("KEY_")
    private String key;

    @TableField("CATEGORY_")
    private String category;

    @TableField("CREATE_TIME_")
    private Date createTime;

    @TableField("LAST_UPDATE_TIME_")
    private Date lastUpdateTime;

    @TableField("VERSION_")
    private Integer version;

    @TableField("META_INFO_")
    private String metaInfo;

    @TableField("DEPLOYMENT_ID_")
    private String deploymentId;

    @TableField("EDITOR_SOURCE_VALUE_ID_")
    private String editorSourceValueId;

    @TableField("EDITOR_SOURCE_EXTRA_VALUE_ID_")
    private String editorSourceExtraValueId;

    @TableField("TENANT_ID_")
    private String tenantId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getEditorSourceValueId() {
        return editorSourceValueId;
    }

    public void setEditorSourceValueId(String editorSourceValueId) {
        this.editorSourceValueId = editorSourceValueId;
    }

    public String getEditorSourceExtraValueId() {
        return editorSourceExtraValueId;
    }

    public void setEditorSourceExtraValueId(String editorSourceExtraValueId) {
        this.editorSourceExtraValueId = editorSourceExtraValueId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Model{" +
        "id=" + id +
        ", rev=" + rev +
        ", name=" + name +
        ", key=" + key +
        ", category=" + category +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", version=" + version +
        ", metaInfo=" + metaInfo +
        ", deploymentId=" + deploymentId +
        ", editorSourceValueId=" + editorSourceValueId +
        ", editorSourceExtraValueId=" + editorSourceExtraValueId +
        ", tenantId=" + tenantId +
        "}";
    }
}
