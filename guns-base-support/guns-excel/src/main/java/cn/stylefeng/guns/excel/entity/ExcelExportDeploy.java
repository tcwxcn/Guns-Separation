package cn.stylefeng.guns.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * excel导出配置
 * </p>
 *
 * @author York
 * @since 2019-11-26
 */
@TableName("excel_export_deploy")
public class ExcelExportDeploy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * excel导出配置名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件名称
     */
    @TableField("title")
    private String title;

    /**
     * 唯一标识
     */
    @TableField("nid")
    private String nid;

    /**
     * 模版路径
     */
    @TableField("template")
    private String template;

    /**
     * 数据源
     */
    @TableField("data_source")
    private String dataSource;

    /**
     * 0开启1关闭
     */
    @TableField("status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExcelExportDeploy{" + "id=" + id + ", name=" + name + ", title=" + title + ", nid=" + nid
                + ", template=" + template + ", dataSource=" + dataSource + ", status=" + status + "}";
    }
}
