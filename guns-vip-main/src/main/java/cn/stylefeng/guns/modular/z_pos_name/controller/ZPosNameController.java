package cn.stylefeng.guns.modular.z_pos_name.controller;

import cn.stylefeng.guns.base.auth.annotion.Permission;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_name.entity.ZPosName;
import cn.stylefeng.guns.modular.z_pos_name.model.params.ZPosNameParam;
import cn.stylefeng.guns.modular.z_pos_name.service.ZPosNameService;
import cn.stylefeng.guns.sys.core.constant.dictmap.DeptDict;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.factory.LayuiTreeFactory;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


/**
 * 职位名称表控制器
 *
 * @author Hua
 * @Date 2020-09-30 10:24:59
 */
@Controller
@RequestMapping("/zPosName")
public class ZPosNameController extends BaseController {

    private String PREFIX = "/modular/zPosName";

    @Autowired
    private ZPosNameService zPosNameService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zPosName.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zPosName_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zPosName_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZPosNameParam zPosNameParam) {
        this.zPosNameService.add(zPosNameParam);
        return ResponseData.success();
    }

    /**
     * .新增职位名称接口
     * @param zPosName
     * @return
     */
    @RequestMapping(value = "/addPosName")
    @Permission
    @ResponseBody
    public ResponseData addPosName(@Valid ZPosName zPosName) {
        this.zPosNameService.addPosName(zPosName);
        return SUCCESS_TIP;
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZPosNameParam zPosNameParam) {
        this.zPosNameService.update(zPosNameParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZPosNameParam zPosNameParam) {
        this.zPosNameService.delete(zPosNameParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZPosNameParam zPosNameParam) {
        ZPosName detail = this.zPosNameService.getById(zPosNameParam.getPosNameId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-30
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZPosNameParam zPosNameParam) {
        return this.zPosNameService.findPageBySpec(zPosNameParam);
    }

    /**
     * 获取职位名称的tree列表，layuiTree格式
     * @return
     */
    @RequestMapping(value = "/layuiTree")
    @ResponseBody
    public List<LayuiTreeNode> layuiTree() {

        List<LayuiTreeNode> list = this.zPosNameService.layuiTree();
        list.add(LayuiTreeFactory.createRoot());

        DefaultTreeBuildFactory<LayuiTreeNode> treeBuildFactory = new DefaultTreeBuildFactory<>();
        treeBuildFactory.setRootParentId("-1");
        return treeBuildFactory.doTreeBuild(list);
    }

}


