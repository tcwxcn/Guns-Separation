package cn.stylefeng.guns.tenant.controller;

import cn.stylefeng.guns.base.db.entity.DatabaseInfo;
import cn.stylefeng.guns.base.db.service.DatabaseInfoService;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.tenant.entity.TenantInfo;
import cn.stylefeng.guns.base.tenant.model.params.TenantInfoParam;
import cn.stylefeng.guns.base.tenant.service.TenantInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 租户表控制器
 *
 * @author stylefeng
 * @Date 2019-06-16 20:57:22
 */
@Controller
@RequestMapping("/tenantInfo")
public class TenantInfoController extends BaseController {

    private String PREFIX = "/modular/tenantInfo";

    @Autowired
    private TenantInfoService tenantInfoService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/tenantInfo.html";
    }

    /**
     * 新增页面
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/add")
    public String add(Model model) {

        //获取数据源信息
        DatabaseInfoService databaseInfoService = null;
        try {
            databaseInfoService = SpringContextHolder.getBean(DatabaseInfoService.class);
        } catch (Exception e) {
            throw new ServiceException(500, "请先开启数据源容器模块！");
        }

        List<DatabaseInfo> all = databaseInfoService.list(new QueryWrapper<>());
        model.addAttribute("dataSources", all);

        return PREFIX + "/tenantInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/edit")
    public String edit(Model model) {

        //获取数据源信息
        DatabaseInfoService databaseInfoService = null;
        try {
            databaseInfoService = SpringContextHolder.getBean(DatabaseInfoService.class);
        } catch (Exception e) {
            throw new ServiceException(500, "请先开启数据源容器模块！");
        }

        List<DatabaseInfo> all = databaseInfoService.list(new QueryWrapper<>());
        model.addAttribute("dataSources", all);

        return PREFIX + "/tenantInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.add(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.update(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.delete(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TenantInfoParam tenantInfoParam) {
        TenantInfo detail = this.tenantInfoService.getById(tenantInfoParam.getTenantId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TenantInfoParam tenantInfoParam) {
        return this.tenantInfoService.findPageBySpec(tenantInfoParam);
    }

    /**
     * 获取租户列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    @ResponseBody
    @RequestMapping("/listTenants")
    public ResponseData listTenants() {
        List<TenantInfo> list = this.tenantInfoService.list(new QueryWrapper<TenantInfo>().select("name,code"));
        return ResponseData.success(list);
    }

}


