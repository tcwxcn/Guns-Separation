package cn.stylefeng.guns.modular.zj_pos_type.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_type.entity.ZjPosType;
import cn.stylefeng.guns.modular.zj_pos_type.model.params.ZjPosTypeParam;
import cn.stylefeng.guns.modular.zj_pos_type.service.ZjPosTypeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 职位类型表控制器
 *
 * @author Hua
 * @Date 2020-09-17 11:35:50
 */
@Controller
@RequestMapping("/zjPosType")
public class ZjPosTypeController extends BaseController {

    private String PREFIX = "/modular/zjPosType";

    @Autowired
    private ZjPosTypeService zjPosTypeService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjPosType.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjPosType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjPosType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjPosTypeParam zjPosTypeParam) {
        this.zjPosTypeService.add(zjPosTypeParam);
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
    public ResponseData editItem(ZjPosTypeParam zjPosTypeParam) {
        this.zjPosTypeService.update(zjPosTypeParam);
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
    public ResponseData delete(ZjPosTypeParam zjPosTypeParam) {
        this.zjPosTypeService.delete(zjPosTypeParam);
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
    public ResponseData detail(ZjPosTypeParam zjPosTypeParam) {
        ZjPosType detail = this.zjPosTypeService.getById(zjPosTypeParam.getPosTypeId());
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
    public LayuiPageInfo list(ZjPosTypeParam zjPosTypeParam) {
        return this.zjPosTypeService.findPageBySpec(zjPosTypeParam);
    }

}


