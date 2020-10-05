package cn.stylefeng.guns.modular.zj_user_resumes.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_user_resumes.entity.ZjUserResumes;
import cn.stylefeng.guns.modular.zj_user_resumes.model.params.ZjUserResumesParam;
import cn.stylefeng.guns.modular.zj_user_resumes.service.ZjUserResumesService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 个人简历信息表控制器
 *
 * @author Hua
 * @Date 2020-09-18 14:18:30
 */
@Controller
@RequestMapping("/zjUserResumes")
public class ZjUserResumesController extends BaseController {

    private String PREFIX = "/modular/zjUserResumes";

    @Autowired
    private ZjUserResumesService zjUserResumesService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjUserResumes.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjUserResumes_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjUserResumes_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjUserResumesParam zjUserResumesParam) {
        this.zjUserResumesService.add(zjUserResumesParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZjUserResumesParam zjUserResumesParam) {
        this.zjUserResumesService.update(zjUserResumesParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZjUserResumesParam zjUserResumesParam) {
        this.zjUserResumesService.delete(zjUserResumesParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZjUserResumesParam zjUserResumesParam) {
        ZjUserResumes detail = this.zjUserResumesService.getById(zjUserResumesParam.getResumesId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZjUserResumesParam zjUserResumesParam) {
        return this.zjUserResumesService.findPageBySpec(zjUserResumesParam);
    }

}


