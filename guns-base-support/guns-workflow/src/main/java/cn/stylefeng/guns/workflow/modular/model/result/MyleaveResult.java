package cn.stylefeng.guns.workflow.modular.model.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
@Data
public class MyleaveResult implements Serializable {

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

}
