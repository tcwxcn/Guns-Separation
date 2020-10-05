package cn.stylefeng.guns.modular.zj_relation_resumes.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 个人简历信息和用户信息关联表
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
@Data
public class ZjRelationResumesResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long relationId;

    /**
     * 个人简历信息id
     */
    private Long resumesId;

    /**
     * 用户信息id
     */
    private Long userInfoId;

}
