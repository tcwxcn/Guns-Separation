package cn.stylefeng.guns.modular.z_pos_info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author Hua
 * @since 2020-09-27
 */
@TableName("z_pos_info")
public class ZPosInfo implements Serializable {

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
     * 职位名称
     */
    @TableField("pos_name")
    private String posName;

    /**
     * 工作内容
     */
    @TableField("content")
    private String content;

    /**
     * 工作要求
     */
    @TableField("request")
    private String request;

    /**
     * 工作日期
     */
    @TableField("date")
    private String date;

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
     * 需求人数
     */
    @TableField("demand_num")
    private String demandNum;

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
     * 福利
     */
    @TableField("welfare")
    private String welfare;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;

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

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(String demandNum) {
        this.demandNum = demandNum;
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

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        return "ZPosInfo{" +
        "posInfoId=" + posInfoId +
        ", title=" + title +
        ", posName=" + posName +
        ", content=" + content +
        ", request=" + request +
        ", date=" + date +
        ", time=" + time +
        ", location=" + location +
        ", demandNum=" + demandNum +
        ", salary=" + salary +
        ", settlementInterval=" + settlementInterval +
        ", welfare=" + welfare +
        ", contact=" + contact +
        ", notice=" + notice +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
