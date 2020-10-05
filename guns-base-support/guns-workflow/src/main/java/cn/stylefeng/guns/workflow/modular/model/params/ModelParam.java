package cn.stylefeng.guns.workflow.modular.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class ModelParam implements Serializable, BaseValidatingParam {

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

    @Override
    public String checkParam() {
        return null;
    }

}
