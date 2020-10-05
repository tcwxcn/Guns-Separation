package cn.stylefeng.guns.modular.z_pos_info.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_info.entity.ZPosInfo;
import cn.stylefeng.guns.modular.z_pos_info.model.params.ZPosInfoParam;
import cn.stylefeng.guns.modular.z_pos_info.service.ZPosInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 职位信息表控制器
 *
 * @author Hua
 * @Date 2020-09-27 15:45:19
 */
@Controller
@RequestMapping("/zPosInfo")
public class ZPosInfoController extends BaseController {

    private String PREFIX = "/modular/zPosInfo";

    @Autowired
    private ZPosInfoService zPosInfoService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zPosInfo.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zPosInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zPosInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZPosInfoParam zPosInfoParam) {
        this.zPosInfoService.add(zPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZPosInfoParam zPosInfoParam) {
        this.zPosInfoService.update(zPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZPosInfoParam zPosInfoParam) {
        this.zPosInfoService.delete(zPosInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZPosInfoParam zPosInfoParam) {
        ZPosInfo detail = this.zPosInfoService.getById(zPosInfoParam.getPosInfoId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-27
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZPosInfoParam zPosInfoParam) {
        return this.zPosInfoService.findPageBySpec(zPosInfoParam);
    }
    /**
     * 通过职位名称表职位名称id或职位地址或职位福利查询职位信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPosInfoByPosNameId")
    public LayuiPageInfo findPosInfoByPosNameId(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) String location,
                                                @RequestParam(required = false) String welfare){
        return this.zPosInfoService.findPosInfoByPosNameId(id,location,welfare);
    }


}


