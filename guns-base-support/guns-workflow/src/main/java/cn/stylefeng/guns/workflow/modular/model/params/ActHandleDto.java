package cn.stylefeng.guns.workflow.modular.model.params;

import lombok.Data;

/**
 * 处理代办任务的请求参数
 *
 * @author fengshuonan
 * @date 2019/8/20 - 16:38
 */
@Data
public class ActHandleDto {

    /**
     *
     */
    private String ASSIGNEE_;

    /**
     * 任务id
     */
    private String ID_;

    /**
     *
     */
    private String msg;

    /**
     *
     */
    private String OPINION;

    /**
     *
     */
    private String PROC_INST_ID_;

    /**
     *
     */
    private String tm;
}
