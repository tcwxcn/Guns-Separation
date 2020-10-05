package cn.stylefeng.guns.modular.zj_pos_info.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_info.entity.ZjPosInfo;
import cn.stylefeng.guns.modular.zj_pos_info.model.params.ZjPosInfoParam;
import cn.stylefeng.guns.modular.zj_pos_info.service.ZjPosInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 职业信息表控制器
 *
 * @author Hua
 * @Date 2020-09-17 11:33:54
 */
@Controller
@RequestMapping("/zjPosInfo")
public class ZjPosInfoController extends BaseController {

    private String PREFIX = "/modular/zjPosInfo";

    @Autowired
    private ZjPosInfoService zjPosInfoService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjPosInfo.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjPosInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjPosInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjPosInfoParam zjPosInfoParam) {
        this.zjPosInfoService.add(zjPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZjPosInfoParam zjPosInfoParam) {
        this.zjPosInfoService.update(zjPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZjPosInfoParam zjPosInfoParam) {
        this.zjPosInfoService.delete(zjPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZjPosInfoParam zjPosInfoParam) {
        ZjPosInfo detail = this.zjPosInfoService.getById(zjPosInfoParam.getPosInfoId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZjPosInfoParam zjPosInfoParam) {
        return this.zjPosInfoService.findPageBySpec(zjPosInfoParam);
    }

}


