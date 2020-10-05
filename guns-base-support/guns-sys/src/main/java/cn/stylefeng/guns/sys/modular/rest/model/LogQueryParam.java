package cn.stylefeng.guns.sys.modular.rest.model;

import lombok.Data;

/**
 * 日志查询的参数
 *
 * @author fengshuonan
 * @Date 2019/11/28 21:07
 */
@Data
public class LogQueryParam {
    String beginTime;
    String endTime;
    String logName;

    /**
     * 1:业务日志   2：异常日志
     */
    Integer logType;
}
