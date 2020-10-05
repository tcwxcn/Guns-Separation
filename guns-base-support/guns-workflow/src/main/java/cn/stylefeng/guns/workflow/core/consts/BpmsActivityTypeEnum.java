package cn.stylefeng.guns.workflow.core.consts;

import lombok.Getter;

/**
 * 枚举类型
 *
 * @author fengshuonan
 * @Date 2019-08-27 18:32
 */
@Getter
public enum BpmsActivityTypeEnum {

    /**
     * 开始事件
     */
    START_EVENT("startEvent", "开始事件"),

    /**
     * 结束事件
     */
    END_EVENT("endEvent", "结束事件"),

    /**
     * 用户任务
     */
    USER_TASK("userTask", "用户任务"),

    /**
     * 排他网关
     */
    EXCLUSIVE_GATEWAY("exclusiveGateway", "排他网关"),

    /**
     * 并行网关
     */
    PARALLEL_GATEWAY("parallelGateway", "并行网关"),

    /**
     * 包含网关
     */
    INCLUSIVE_GATEWAY("inclusiveGateway", "包含网关");

    private String type;

    private String name;

    BpmsActivityTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
