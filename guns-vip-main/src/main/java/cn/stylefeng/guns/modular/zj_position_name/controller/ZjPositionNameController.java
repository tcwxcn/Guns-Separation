package cn.stylefeng.guns.modular.zj_position_name.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_position_name.entity.ZjPositionName;
import cn.stylefeng.guns.modular.zj_position_name.model.params.ZjPositionNameParam;
import cn.stylefeng.guns.modular.zj_position_name.service.ZjPositionNameService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 工作职位名称表控制器
 *
 * @author Hua
 * @Date 2020-09-21 13:56:44
 */
@Controller
@RequestMapping("/zjPositionName")
public class ZjPositionNameController extends BaseController {

    private String PREFIX = "/modular/zjPositionName";

    @Autowired
    private ZjPositionNameService zjPositionNameService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjPositionName.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjPositionName_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjPositionName_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjPositionNameParam zjPositionNameParam) {
        this.zjPositionNameService.add(zjPositionNameParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZjPositionNameParam zjPositionNameParam) {
        this.zjPositionNameService.update(zjPositionNameParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZjPositionNameParam zjPositionNameParam) {
        this.zjPositionNameService.delete(zjPositionNameParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZjPositionNameParam zjPositionNameParam) {
        ZjPositionName detail = this.zjPositionNameService.getById(zjPositionNameParam.getPositionId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZjPositionNameParam zjPositionNameParam) {
        return this.zjPositionNameService.findPageBySpec(zjPositionNameParam);
    }

}


