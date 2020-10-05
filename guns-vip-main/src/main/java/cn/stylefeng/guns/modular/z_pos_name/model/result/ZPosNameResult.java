package cn.stylefeng.guns.modular.z_pos_name.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 职位名称表
 * </p>
 *
 * @author Hua
 * @since 2020-09-30
 */
@Data
public class ZPosNameResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long posNameId;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 职位名称
     */
    private String name;

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
