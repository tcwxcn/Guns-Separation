package cn.stylefeng.guns.workflow.modular.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-06
 */
@Data
public class ModelResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    private Integer rev;

    private String name;

    private String key;

    private String category;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer version;

    private String metaInfo;

    private String deploymentId;

    private String editorSourceValueId;

    private String editorSourceExtraValueId;

    private String tenantId;

}
