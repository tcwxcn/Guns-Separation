package cn.stylefeng.guns.modular.z_pos_info.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-27
 */
@Data
public class ZPosInfoResult implements Serializable {

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
     * 职位名称
     */
    private String posName;

    /**
     * 工作内容
     */
    private String content;

    /**
     * 工作要求
     */
    private String request;

    /**
     * 工作日期
     */
    private String date;

    /**
     * 工作时间
     */
    private String time;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 需求人数
     */
    private String demandNum;

    /**
     * 薪资待遇
     */
    private String salary;

    /**
     * 结算周期
     */
    private String settlementInterval;

    /**
     * 福利
     */
    private String welfare;

    /**
     * 联系方式
     */
    private String contact;

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

}
