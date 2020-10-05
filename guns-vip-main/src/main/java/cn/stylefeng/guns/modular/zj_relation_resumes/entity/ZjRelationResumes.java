package cn.stylefeng.guns.modular.zj_relation_resumes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 个人简历信息和用户信息关联表
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
@TableName("zj_relation_resumes")
public class ZjRelationResumes implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "relation_id", type = IdType.ID_WORKER)
    private Long relationId;

    /**
     * 个人简历信息id
     */
    @TableField("resumes_id")
    private Long resumesId;

    /**
     * 用户信息id
     */
    @TableField("user_info_id")
    private Long userInfoId;


    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getResumesId() {
        return resumesId;
    }

    public void setResumesId(Long resumesId) {
        this.resumesId = resumesId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
        return "ZjRelationResumes{" +
        "relationId=" + relationId +
        ", resumesId=" + resumesId +
        ", userInfoId=" + userInfoId +
        "}";
    }
}
