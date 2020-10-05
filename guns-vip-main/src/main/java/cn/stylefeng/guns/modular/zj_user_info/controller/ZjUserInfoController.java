package cn.stylefeng.guns.modular.zj_user_info.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_user_info.entity.ZjUserInfo;
import cn.stylefeng.guns.modular.zj_user_info.model.params.ZjUserInfoParam;
import cn.stylefeng.guns.modular.zj_user_info.model.result.ZjUserInfoResult;
import cn.stylefeng.guns.modular.zj_user_info.service.ZjUserInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户信息表控制器
 *
 * @author Hua
 * @Date 2020-09-22 14:54:03
 */
@Controller
@RequestMapping("/zjUserInfo")
public class ZjUserInfoController extends BaseController {

    private String PREFIX = "/modular/zjUserInfo";

    @Autowired
    private ZjUserInfoService zjUserInfoService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjUserInfo.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjUserInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjUserInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjUserInfoParam zjUserInfoParam) {
        this.zjUserInfoService.add(zjUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZjUserInfoParam zjUserInfoParam) {
        this.zjUserInfoService.update(zjUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZjUserInfoParam zjUserInfoParam) {
        this.zjUserInfoService.delete(zjUserInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZjUserInfoParam zjUserInfoParam) {
        ZjUserInfo detail = this.zjUserInfoService.getById(zjUserInfoParam.getUserInfoId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZjUserInfoParam zjUserInfoParam) {
        return this.zjUserInfoService.findPageBySpec(zjUserInfoParam);
    }
    @ResponseBody
    @RequestMapping("/getByPhone")
    public ZjUserInfoResult getBySpec(){
        ZjUserInfoParam zjUserInfoParam = new ZjUserInfoParam();
        zjUserInfoParam.setPhone("15215288837");
        ZjUserInfoResult zjUserInfoResult = zjUserInfoService.findBySpec(zjUserInfoParam);
        return zjUserInfoResult;
    }

}


