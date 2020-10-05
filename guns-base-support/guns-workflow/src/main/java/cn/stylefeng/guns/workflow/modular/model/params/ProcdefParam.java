package cn.stylefeng.guns.workflow.modular.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-11
 */
@Data
public class ProcdefParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    private Integer rev;

    private String category;

    private String name;

    private String key;

    private Integer version;

    private String deploymentId;

    private String resourceName;

    private String dgrmResourceName;

    private String description;

    private Integer hasStartFormKey;

    private Integer hasGraphicalNotation;

    private Integer suspensionState;

    private String tenantId;

    private String engineVersion;

    private String keywords;

    private String lastStart;

    private String lastEnd;

    @Override
    public String checkParam() {
        return null;
    }

}
