package cn.stylefeng.guns.modular.zj_pos_info.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 职业信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
@Data
public class ZjPosInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long posInfoId;

    /**
     * 标题
     */
    private String title;

    /**
     * 工作职业
     */
    private String job;

    /**
     * 需求人数
     */
    private String num;

    /**
     * 工作时间
     */
    private String time;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 工作内容
     */
    private String content;

    /**
     * 职位要求
     */
    private String request;

    /**
     * 薪资待遇
     */
    private String salary;

    /**
     * 结算周期
     */
    private String settlementInterval;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 注意事项
     */
    private String notice;

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
