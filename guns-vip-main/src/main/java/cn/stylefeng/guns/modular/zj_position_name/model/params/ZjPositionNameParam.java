package cn.stylefeng.guns.modular.zj_position_name.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class ZjPositionNameParam implements Serializable, BaseValidatingParam {

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

    @Override
    public String checkParam() {
        return null;
    }

}
