package cn.stylefeng.guns.modular.zj_pos_info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 职业信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
@TableName("zj_pos_info")
public class ZjPosInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "pos_info_id", type = IdType.AUTO)
    private Long posInfoId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 工作职业
     */
    @TableField("job")
    private String job;

    /**
     * 需求人数
     */
    @TableField("num")
    private String num;

    /**
     * 工作时间
     */
    @TableField("time")
    private String time;

    /**
     * 工作地点
     */
    @TableField("location")
    private String location;

    /**
     * 工作内容
     */
    @TableField("content")
    private String content;

    /**
     * 职位要求
     */
    @TableField("request")
    private String request;

    /**
     * 薪资待遇
     */
    @TableField("salary")
    private String salary;

    /**
     * 结算周期
     */
    @TableField("settlement_interval")
    private String settlementInterval;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;

    /**
     * 顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 注意事项
     */
    @TableField("notice")
    private String notice;

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


    public Long getPosInfoId() {
        return posInfoId;
    }

    public void setPosInfoId(Long posInfoId) {
        this.posInfoId = posInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSettlementInterval() {
        return settlementInterval;
    }

    public void setSettlementInterval(String settlementInterval) {
        this.settlementInterval = settlementInterval;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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
        return "ZjPosInfo{" +
        "posInfoId=" + posInfoId +
        ", title=" + title +
        ", job=" + job +
        ", num=" + num +
        ", time=" + time +
        ", location=" + location +
        ", content=" + content +
        ", request=" + request +
        ", salary=" + salary +
        ", settlementInterval=" + settlementInterval +
        ", contact=" + contact +
        ", sort=" + sort +
        ", notice=" + notice +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
