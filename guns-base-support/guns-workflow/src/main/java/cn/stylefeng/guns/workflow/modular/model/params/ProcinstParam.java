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
public class ProcinstParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    private String procInstId;

    private String businessKey;

    private String procDefId;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private String startUserId;

    private String startActId;

    private String endActId;

    private String superProcessInstanceId;

    private String deleteReason;

    private String tenantId;

    private String name;

    @Override
    public String checkParam() {
        return null;
    }

}
