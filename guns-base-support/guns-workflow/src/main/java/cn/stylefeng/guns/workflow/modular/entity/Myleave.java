package cn.stylefeng.guns.workflow.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
@TableName("oa_myleave")
public class Myleave implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "myleave_id", type = IdType.UUID)
    private String myleaveId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 开始时间
     */
    @TableField("starttime")
    private String starttime;

    /**
     * 结束时间
     */
    @TableField("endtime")
    private String endtime;

    /**
     * 时长
     */
    @TableField("whenlong")
    private String whenlong;

    /**
     * 事由
     */
    @TableField("reason")
    private String reason;


    public String getMyleaveId() {
        return myleaveId;
    }

    public void setMyleaveId(String myleaveId) {
        this.myleaveId = myleaveId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getWhenlong() {
        return whenlong;
    }

    public void setWhenlong(String whenlong) {
        this.whenlong = whenlong;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Myleave{" +
        "myleaveId=" + myleaveId +
        ", username=" + username +
        ", type=" + type +
        ", starttime=" + starttime +
        ", endtime=" + endtime +
        ", whenlong=" + whenlong +
        ", reason=" + reason +
        "}";
    }
}
