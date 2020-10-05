package cn.stylefeng.guns.sys.modular.rest.model;

import lombok.Data;

/**
 * 管理员查询的参数
 *
 * @author fengshuonan
 * @Date 2019/11/28 21:07
 */
@Data
public class UserQueryParam {

    String name;

    String timeLimit;

    Long deptId;
}
