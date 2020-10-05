package cn.stylefeng.guns.modular.zj_pos_type.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 职位类型表
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
@Data
public class ZjPosTypeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long posTypeId;

    /**
     * 职位类型名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sort;

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
     * 创建者
     */
    private Long createUser;

    /**
     * 更新者
     */
    private Long updateUser;

}
