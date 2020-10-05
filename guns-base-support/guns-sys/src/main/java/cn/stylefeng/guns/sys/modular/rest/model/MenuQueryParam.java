package cn.stylefeng.guns.sys.modular.rest.model;

import lombok.Data;

/**
 * 菜单查询的参数
 *
 * @author fengshuonan
 * @Date 2019/11/28 21:07
 */
@Data
public class MenuQueryParam {

    String menuName;

    String level;

    Long menuId;

}
