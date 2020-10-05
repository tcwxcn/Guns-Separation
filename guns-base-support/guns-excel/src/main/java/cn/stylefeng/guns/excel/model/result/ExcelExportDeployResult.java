package cn.stylefeng.guns.excel.model.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * excel导出配置
 * </p>
 *
 * @author York
 * @since 2019-11-26
 */
@Data
public class ExcelExportDeployResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * excel导出配置名称
	 */
	private String name;

	/**
	 * 文件名称
	 */
	private String title;

	/**
	 * 唯一标识
	 */
	private String nid;

	/**
	 * 模版路径
	 */
	private String template;

	/**
	 * 数据源
	 */
	private String dataSource;

	/**
	 * 0开启1关闭
	 */
	private Integer status;

	public String getStatusName() {
		if (status == null) {
			return null;
		}
		switch (status) {
		case 0:
			return "开启";
		case 1:
			return "关闭";
		default:
			return "未定义";
		}
	}
}
