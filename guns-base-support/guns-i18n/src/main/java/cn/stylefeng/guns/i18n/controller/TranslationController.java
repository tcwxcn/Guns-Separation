package cn.stylefeng.guns.i18n.controller;

import cn.stylefeng.guns.base.i18n.entity.Translation;
import cn.stylefeng.guns.base.i18n.model.params.TranslationParam;
import cn.stylefeng.guns.base.i18n.service.TranslationService;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 多语言表控制器
 *
 * @author stylefeng
 * @Date 2019-10-17 22:20:54
 */
@Controller
@RequestMapping("/translation")
public class TranslationController extends BaseController {

    private String PREFIX = "/modular/i18n";

    @Autowired
    private TranslationService translationService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/translation.html";
    }

    /**
     * 新增页面
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/translation_add.html";
    }

    /**
     * 编辑页面
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/translation_edit.html";
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TranslationParam translationParam) {
        this.translationService.add(translationParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TranslationParam translationParam) {
        this.translationService.update(translationParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(TranslationParam translationParam) {
        this.translationService.delete(translationParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TranslationParam translationParam) {
        Translation detail = this.translationService.getById(translationParam.getTranId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestParam(value = "condition", required = false) String condition) {
        TranslationParam translationParam = new TranslationParam();
        translationParam.setTranName(condition);
        translationParam.setTranCode(condition);
        return this.translationService.findPageBySpec(translationParam);
    }

}


