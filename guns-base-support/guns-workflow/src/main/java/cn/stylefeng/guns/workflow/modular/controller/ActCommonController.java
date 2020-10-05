package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.sys.modular.system.entity.Role;
import cn.stylefeng.guns.sys.modular.system.mapper.RoleMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.UserMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Act通用Controller
 *
 * @author stylefeng
 * @date 2019/8/27 - 17:34
 */
@Controller
@RequestMapping("/common")
public class ActCommonController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 设置用户页面
     *
     * @author yaopu
     * @date 2019/8/30 - 09:34
     */
    @RequestMapping(value = "/toSetUserPage")
    public String toSetUserPage() {
        return "/modular/act/model/user.html";
    }

    /**
     * 设置角色页面
     *
     * @author yaopu
     * @date 2019/8/30 - 09:34
     */
    @RequestMapping(value = "/toSetRolePage")
    public String toSetRolePage() {
        return "/modular/act/model/role.html";
    }

    /**
     * 显示用户列表(弹窗选择用)
     *
     * @author stylefeng
     * @date 2019/8/27 - 17:34
     */
    @RequestMapping(value = "/listUsersForWindow")
    @ResponseBody
    public Object listUsersForWindow(@RequestParam("currentPage") Integer currentPage,
                                     @RequestParam("showCount") Integer showCount,
                                     @RequestParam(value = "KEYWORDS", required = false) String keywords,
                                     @RequestParam("ROLE_ID") String roleId,
                                     @RequestParam(value = "STRARTTIME", required = false) String strarttime) {

        Page page = new Page();
        page.setCurrent(currentPage);
        if (showCount == -1) {
            showCount = 10;
        }
        page.setSize(showCount);

        //查询人员列表信息
        Page<Map<String, Object>> mapPage = userMapper.selectUsersByRole(page, keywords, strarttime, null, roleId);
        List<Map<String, Object>> records = mapPage.getRecords();

        //查询角色信息
        Page rolePage = new Page();
        rolePage.setSize(9999);
        IPage<Map<String, Object>> listRole = roleMapper.listRole(rolePage, null);
        List<Map<String, Object>> roleList = listRole.getRecords();

        //设置前端所需参数
        if (ToolUtil.isNotEmpty(records) && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                records.get(i).put("USERNAME", records.get(i).get("account"));
                records.get(i).put("NAME", records.get(i).get("name"));

                String roleIdItem = (String) records.get(i).get("roleId");
                String[] split = roleIdItem.split(",");
                List<Role> roles = roleMapper.selectBatchIds(Arrays.asList(split));
                StringBuilder roleNameString = new StringBuilder("");
                for (int j = 0; j < roles.size(); j++) {
                    if (j == roles.size() - 1) {
                        roleNameString.append(roles.get(j).getName());
                        break;
                    }
                    roleNameString.append(roles.get(j).getName()).append(",");
                }
                records.get(i).put("ROLE_NAME", roleNameString);
            }
        }
        if (ToolUtil.isNotEmpty(roleList) && roleList.size() > 0) {
            for (int i = 0; i < roleList.size(); i++) {
                roleList.get(i).put("role_NAME", roleList.get(i).get("name"));
                roleList.get(i).put("role_ID", roleList.get(i).get("id"));
            }
        }

        //分页信息
        int totalResult = (int) page.getTotal();
        int totalPage = (int) page.getPages();
        int currentResult = (currentPage - 1) * showCount;
        if (currentResult < 0) {
            currentResult = 0;
        }

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("showCount", showCount);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalResult", totalResult);
        pageInfo.put("currentPage", currentPage);
        pageInfo.put("currentResult", currentResult);
        pageInfo.put("entityOrField", true);
        pageInfo.put("pageStr", getPageStr(totalResult, currentPage, totalPage, showCount));
        pageInfo.put("pageStrSimplify", getPageStrSimplify(totalResult, currentPage, totalPage));
        pageInfo.put("pageStrSimplify2", getPageStrSimplify2(totalResult, currentPage, totalPage));

        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userList", records);
        resultMap.put("result", "success");
        resultMap.put("roleList", roleList);
        resultMap.put("page", pageInfo);
        resultMap.put("pd", "");
        return resultMap;
    }

    /**
     * 选择角色(弹窗选择用)
     *
     * @author stylefeng
     * @date 2019/8/28 - 13:10
     */
    @RequestMapping(value = "/roleListWindow")
    @ResponseBody
    public Object roleListWindow(@RequestParam("currentPage") Integer currentPage,
                                 @RequestParam("showCount") Integer showCount,
                                 @RequestParam("KEYWORDS") String keywords) {

        Page page = new Page();
        page.setCurrent(currentPage);
        if (showCount == -1) {
            showCount = 10;
        }
        page.setSize(showCount);

        //查询角色列表信息
        Page<Map<String, Object>> mapPage = roleMapper.selectRoles(page, keywords);
        List<Map<String, Object>> records = mapPage.getRecords();

        //设置前端所需参数
        if (ToolUtil.isNotEmpty(records) && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                records.get(i).put("RNUMBER", records.get(i).get("roleId"));
                records.get(i).put("ROLE_NAME", records.get(i).get("name"));
            }
        }

        //分页信息
        int totalResult = (int) page.getTotal();
        int totalPage = (int) page.getPages();
        int currentResult = (currentPage - 1) * showCount;
        if (currentResult < 0) {
            currentResult = 0;
        }

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("showCount", showCount);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalResult", totalResult);
        pageInfo.put("currentPage", currentPage);
        pageInfo.put("currentResult", currentResult);
        pageInfo.put("entityOrField", true);
        pageInfo.put("pageStr", getPageStr(totalResult, currentPage, totalPage, showCount));
        pageInfo.put("pageStrSimplify", getPageStrSimplify(totalResult, currentPage, totalPage));
        pageInfo.put("pageStrSimplify2", getPageStrSimplify2(totalResult, currentPage, totalPage));

        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList", records);
        resultMap.put("result", "success");
        resultMap.put("page", pageInfo);
        return resultMap;
    }

    /**
     * 拼接分页 页面及JS函数
     *
     * @author fengshuonan
     * @Date 2019-08-29 14:10
     */
    private String getPageStr(int totalResult, int currentPage, int totalPage, int showCount) {
        StringBuffer sb = new StringBuffer();
        if (totalResult > 0) {
            sb.append("	<ul class=\"pagination pull-right no-margin\">\n");
            if (currentPage == 1) {
                sb.append("	<li style=\"padding-top:10px;\"><a>共").append(totalResult).append("条&nbsp;</a></li>\n");
                sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:36px;padding-top:5px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom: 1px solid #dbdbdb\" /></li>\n");
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.toTZ();\" >&nbsp;跳转&nbsp;</a></li>\n");
                sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;首页 &nbsp;</a></li>\n");
                sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;上页&nbsp;</a></li>\n");
            } else {
                sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;共").append(totalResult).append("条&nbsp;</a></li>\n");
                sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:36px;padding-top:5px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom: 1px solid #dbdbdb\" /></li>\n");
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.toTZ();\" >&nbsp;跳转&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.nextPage(1)\">&nbsp;首页&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.nextPage(").append(currentPage - 1).append(")\">&nbsp;上页&nbsp;</a></li>\n");
            }

            //分页标签显示数量
            int showTag = 5;
            int startTag = 1;
            if (currentPage > showTag) {
                startTag = currentPage - 1;
            }
            int endTag = startTag + showTag - 1;
            for (int i = startTag; i <= totalPage && i <= endTag; i++) {
                if (currentPage == i) {
                    sb.append("<li style=\"padding-top:10px;\" class=\"active\"><a><font color=\"#3F4D67\">&nbsp;<b>").append(i).append("</b></font></a></li>\n");

                } else {
                    sb.append("	<li style=\"padding-top:10px;\" style=\"cursor:pointer;\"><a style=\"cursor:pointer;\" onclick=\"vm.nextPage(").append(i).append(")\">&nbsp;").append(i).append("</a></li>\n");
                }

            }
            if (currentPage == totalPage) {
                sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;下页&nbsp;</a></li>\n");
                sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;尾页&nbsp;</a></li>\n");
            } else {
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.nextPage(").append(currentPage + 1).append(")\">&nbsp;下页&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;padding-top:10px;\"><a onclick=\"vm.nextPage(").append(totalPage).append(")\">&nbsp;尾页&nbsp;</a></li>\n");
            }
            sb.append("	<li style=\"padding-top:10px;\"><a>&nbsp;共").append(totalPage).append("页&nbsp;</a></li>\n");
            sb.append("	<li style=\"padding-top:10px;\"><select title='显示条数' style=\"cursor:pointer;width:39px;float:left;margin-top:0px;border:none;background-color: rgba(255,255,255,0.6);\" onchange=\"vm.changeCount(this.value)\">\n");
            sb.append("	<option value='").append(showCount).append("'>").append(showCount).append("</option>\n");
            if (10 != showCount) {
                sb.append("	<option value='10'>10</option>\n");
            }
            sb.append("	<option value='20'>20</option>\n");
            sb.append("	<option value='30'>30</option>\n");
            sb.append("	<option value='40'>40</option>\n");
            sb.append("	<option value='50'>50</option>\n");
            sb.append("	<option value='60'>60</option>\n");
            sb.append("	<option value='70'>70</option>\n");
            sb.append("	<option value='80'>80</option>\n");
            sb.append("	<option value='90'>90</option>\n");
            sb.append("	<option value='99'>99</option>\n");
            sb.append("	</select>\n");
            sb.append("	</li>\n");
            sb.append("</ul>\n");
        }
        return sb.toString();
    }

    /**
     * 拼接分页 页面及JS函数
     *
     * @author fengshuonan
     * @Date 2019-08-29 14:10
     */
    private String getPageStrSimplify(int totalResult, int currentPage, int totalPage) {
        StringBuilder buffer = new StringBuilder();
        if (totalResult > 0) {
            buffer.append("	<ul class=\"pagination pull-right no-margin\">\n");
            if (currentPage == 1) {
                buffer.append("	<li><a>共").append(totalResult).append("条&nbsp;</a></li>\n");
                buffer.append("	<li><a>&nbsp;首页 &nbsp;</a></li>\n");
                buffer.append("	<li><a>&nbsp;上页&nbsp;</a></li>\n");
            } else {
                buffer.append("	<li><a>&nbsp;共").append(totalResult).append("条&nbsp;</a></li>\n");
                buffer.append("	<li style=\"cursor:pointer;\"><a onclick=\"vm.nextPage(1)\">&nbsp;首页&nbsp;</a></li>\n");
                buffer.append("	<li style=\"cursor:pointer;\"><a onclick=\"vm.nextPage(").append(currentPage - 1).append(")\">&nbsp;上页&nbsp;</a></li>\n");
            }

            //分页标签显示数量
            int showTag = 2;
            int startTag = 1;
            if (currentPage > showTag) {
                startTag = currentPage - 1;
            }
            int endTag = startTag + showTag - 1;
            for (int i = startTag; i <= totalPage && i <= endTag; i++) {
                if (currentPage == i) {
                    buffer.append("<li class=\"active\"><a><font color=\"#3F4D67\">&nbsp;<b>").append(i).append("</b></font></a></li>\n");
                } else {
                    buffer.append("	<li style=\"cursor:pointer;\"><a style=\"cursor:pointer;\" onclick=\"vm.nextPage(").append(i).append(")\">&nbsp;").append(i).append("</a></li>\n");
                }
            }
            if (currentPage == totalPage) {
                buffer.append("	<li><a>&nbsp;下页&nbsp;</a></li>\n");
                buffer.append("	<li><a>&nbsp;尾页&nbsp;</a></li>\n");
            } else {
                buffer.append("	<li style=\"cursor:pointer;\"><a onclick=\"vm.nextPage(").append(currentPage + 1).append(")\">&nbsp;下页&nbsp;</a></li>\n");
                buffer.append("	<li style=\"cursor:pointer;\"><a onclick=\"vm.nextPage(").append(totalPage).append(")\">&nbsp;尾页&nbsp;</a></li>\n");
            }
            buffer.append("	<li><a>&nbsp;共").append(totalPage).append("页&nbsp;</a></li>\n");
            buffer.append("</ul>\n");
        }
        return buffer.toString();
    }

    /**
     * 拼接分页 页面及JS函数
     *
     * @author fengshuonan
     * @Date 2019-08-29 14:11
     */
    public String getPageStrSimplify2(int totalResult, int currentPage, int totalPage) {
        StringBuilder sb = new StringBuilder();
        if (totalResult > 0) {
            sb.append("	<ul class=\"pagination pull-right no-margin\">\n");
            if (currentPage == 1) {
                sb.append("	<li><a>共").append(totalResult).append("条&nbsp;</a></li>\n");
                sb.append("	<li><a>&nbsp;首页 &nbsp;</a></li>\n");
                sb.append("	<li><a>&nbsp;上页&nbsp;</a></li>\n");
            } else {
                sb.append("	<li><a>&nbsp;共").append(totalResult).append("条&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">&nbsp;首页&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(").append(currentPage - 1).append(")\">&nbsp;上页&nbsp;</a></li>\n");
            }

            //分页标签显示数量
            int showTag = 2;
            int startTag = 1;
            if (currentPage > showTag) {
                startTag = currentPage - 1;
            }
            int endTag = startTag + showTag - 1;
            for (int i = startTag; i <= totalPage && i <= endTag; i++) {
                if (currentPage == i) {
                    sb.append("<li class=\"active\"><a><font color=\"#3F4D67\">&nbsp;<b>").append(i).append("</b></font></a></li>\n");
                } else {
                    sb.append("	<li style=\"cursor:pointer;\"><a style=\"cursor:pointer;\" onclick=\"nextPage(").append(i).append(")\">&nbsp;").append(i).append("</a></li>\n");

                }
            }
            if (currentPage == totalPage) {
                sb.append("	<li><a>&nbsp;下页&nbsp;</a></li>\n");
                sb.append("	<li><a>&nbsp;尾页&nbsp;</a></li>\n");
            } else {
                sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(").append(currentPage + 1).append(")\">&nbsp;下页&nbsp;</a></li>\n");
                sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(").append(totalPage).append(")\">&nbsp;尾页&nbsp;</a></li>\n");
            }
            sb.append("	<li><a>&nbsp;共").append(totalPage).append("页&nbsp;</a></li>\n");
            sb.append("</ul>\n");
        }
        return sb.toString();
    }
}
