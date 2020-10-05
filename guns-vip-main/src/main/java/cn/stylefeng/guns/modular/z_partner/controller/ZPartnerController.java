package cn.stylefeng.guns.modular.z_partner.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_partner.entity.ZPartner;
import cn.stylefeng.guns.modular.z_partner.model.params.ZPartnerParam;
import cn.stylefeng.guns.modular.z_partner.service.ZPartnerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 合作伙伴表控制器
 *
 * @author Hua
 * @Date 2020-09-29 11:42:59
 */
@Controller
@RequestMapping("/zPartner")
public class ZPartnerController extends BaseController {

    private String PREFIX = "/modular/zPartner";

    @Autowired
    private ZPartnerService zPartnerService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zPartner.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zPartner_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zPartner_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZPartnerParam zPartnerParam) {
        this.zPartnerService.add(zPartnerParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZPartnerParam zPartnerParam) {
        this.zPartnerService.update(zPartnerParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZPartnerParam zPartnerParam) {
        this.zPartnerService.delete(zPartnerParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZPartnerParam zPartnerParam) {
        ZPartner detail = this.zPartnerService.getById(zPartnerParam.getPartnerId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZPartnerParam zPartnerParam) {
        return this.zPartnerService.findPageBySpec(zPartnerParam);
    }

}


