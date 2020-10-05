package cn.stylefeng.guns.modular.zj_user_resumes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 个人简历信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-18
 */
@TableName("zj_user_resumes")
public class ZjUserResumes implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "resumes_id", type = IdType.ID_WORKER)
    private Long resumesId;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 年龄
     */
    @TableField("age")
    private String age;

    /**
     * 身高
     */
    @TableField("height")
    private String height;

    /**
     * 体重
     */
    @TableField("weight")
    private String weight;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 电子邮件
     */
    @TableField("email")
    private String email;

    /**
     * 最高学历
     */
    @TableField("education")
    private String education;

    /**
     * 现居住地
     */
    @TableField("current_address")
    private String currentAddress;

    /**
     * 户口所在地
     */
    @TableField("permanent_address")
    private String permanentAddress;

    /**
     * 求职状态
     */
    @TableField("status_job")
    private String statusJob;

    /**
     * 到岗时间
     */
    @TableField("arrival_time")
    private String arrivalTime;

    /**
     * 期望月薪
     */
    @TableField("expected_monthly_salary")
    private String expectedMonthlySalary;

    /**
     * 期望工作地点
     */
    @TableField("expected_workplace")
    private String expectedWorkplace;

    /**
     * 期望职位
     */
    @TableField("expected_position")
    private String expectedPosition;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
      @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新人
     */
      @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;


    public Long getResumesId() {
        return resumesId;
    }

    public void setResumesId(Long resumesId) {
        this.resumesId = resumesId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getStatusJob() {
        return statusJob;
    }

    public void setStatusJob(String statusJob) {
        this.statusJob = statusJob;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getExpectedMonthlySalary() {
        return expectedMonthlySalary;
    }

    public void setExpectedMonthlySalary(String expectedMonthlySalary) {
        this.expectedMonthlySalary = expectedMonthlySalary;
    }

    public String getExpectedWorkplace() {
        return expectedWorkplace;
    }

    public void setExpectedWorkplace(String expectedWorkplace) {
        this.expectedWorkplace = expectedWorkplace;
    }

    public String getExpectedPosition() {
        return expectedPosition;
    }

    public void setExpectedPosition(String expectedPosition) {
        this.expectedPosition = expectedPosition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "ZjUserResumes{" +
        "resumesId=" + resumesId +
        ", avatar=" + avatar +
        ", name=" + name +
        ", sex=" + sex +
        ", age=" + age +
        ", height=" + height +
        ", weight=" + weight +
        ", phone=" + phone +
        ", email=" + email +
        ", education=" + education +
        ", currentAddress=" + currentAddress +
        ", permanentAddress=" + permanentAddress +
        ", statusJob=" + statusJob +
        ", arrivalTime=" + arrivalTime +
        ", expectedMonthlySalary=" + expectedMonthlySalary +
        ", expectedWorkplace=" + expectedWorkplace +
        ", expectedPosition=" + expectedPosition +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
