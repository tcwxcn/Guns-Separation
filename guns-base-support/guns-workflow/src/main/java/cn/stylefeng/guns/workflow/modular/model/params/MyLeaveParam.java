package cn.stylefeng.guns.workflow.modular.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 出差申请参数
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
@Data
public class MyLeaveParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String myleaveId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 类型
     */
    private String type;

    /**
     * 开始时间
     */
    private String starttime;

    /**
     * 结束时间
     */
    private String endtime;

    /**
     * 时长
     */
    private String whenlong;

    /**
     * 事由
     */
    private String reason;

    /**
     * 检索关键词
     */
    private String keywords;

    @Override
    public String checkParam() {
        return null;
    }

}
