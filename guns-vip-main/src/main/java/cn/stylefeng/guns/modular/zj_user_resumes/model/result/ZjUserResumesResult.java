package cn.stylefeng.guns.modular.zj_user_resumes.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 个人简历信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-18
 */
@Data
public class ZjUserResumesResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long resumesId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 电话
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 最高学历
     */
    private String education;

    /**
     * 现居住地
     */
    private String currentAddress;

    /**
     * 户口所在地
     */
    private String permanentAddress;

    /**
     * 求职状态
     */
    private String statusJob;

    /**
     * 到岗时间
     */
    private String arrivalTime;

    /**
     * 期望月薪
     */
    private String expectedMonthlySalary;

    /**
     * 期望工作地点
     */
    private String expectedWorkplace;

    /**
     * 期望职位
     */
    private String expectedPosition;

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
