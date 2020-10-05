package cn.stylefeng.guns.modular.zj_relation_resumes.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_relation_resumes.entity.ZjRelationResumes;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.params.ZjRelationResumesParam;
import cn.stylefeng.guns.modular.zj_relation_resumes.service.ZjRelationResumesService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 个人简历信息和用户信息关联表控制器
 *
 * @author Hua
 * @Date 2020-09-21 16:47:17
 */
@Controller
@RequestMapping("/zjRelationResumes")
public class ZjRelationResumesController extends BaseController {

    private String PREFIX = "/modular/zjRelationResumes";

    @Autowired
    private ZjRelationResumesService zjRelationResumesService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zjRelationResumes.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zjRelationResumes_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zjRelationResumes_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-21
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZjRelationResumesParam zjRelationResumesParam) {
        this.zjRelationResumesService.add(zjRelationResumesParam);
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
    public ResponseData editItem(ZjRelationResumesParam zjRelationResumesParam) {
        this.zjRelationResumesService.update(zjRelationResumesParam);
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
    public ResponseData delete(ZjRelationResumesParam zjRelationResumesParam) {
        this.zjRelationResumesService.delete(zjRelationResumesParam);
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
    public ResponseData detail(ZjRelationResumesParam zjRelationResumesParam) {
        ZjRelationResumes detail = this.zjRelationResumesService.getById(zjRelationResumesParam.getRelationId());
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
    public LayuiPageInfo list(ZjRelationResumesParam zjRelationResumesParam) {
        return this.zjRelationResumesService.findPageBySpec(zjRelationResumesParam);
    }

}


