package cn.stylefeng.guns.modular.zj_position_name.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 工作职位名称表
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
@Data
public class ZjPositionNameResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新人
     */
    private Long updateUser;

}
