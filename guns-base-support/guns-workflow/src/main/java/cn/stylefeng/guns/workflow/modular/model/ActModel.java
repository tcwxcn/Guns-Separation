package cn.stylefeng.guns.workflow.modular.model;

import lombok.Data;

/**
 * 流程模型
 *
 * @author fengshuonan
 * @Date 2019/8/6 21:07
 */
@Data
public class ActModel {

    /**
     * 模型id
     */
    private String modelId;

    /**
     * 流程作者
     */
    private String processAuthor;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程标识
     */
    private String processId;

    /**
     * 模型名称
     */
    private String modelname;

    /**
     * 模型描述
     */
    private String description;

    /**
     * 模型分类
     */
    private String category;

}
