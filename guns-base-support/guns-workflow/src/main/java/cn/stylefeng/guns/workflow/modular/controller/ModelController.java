package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.workflow.modular.controller.base.ActBaseController;
import cn.stylefeng.guns.workflow.modular.entity.Model;
import cn.stylefeng.guns.workflow.modular.model.ActModel;
import cn.stylefeng.guns.workflow.modular.model.params.ModelParam;
import cn.stylefeng.guns.workflow.modular.service.ModelService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * 控制器
 *
 * @author stylefeng
 * @Date 2019-08-06 22:03:10
 */
@Controller
@RequestMapping("/model")
public class ModelController extends ActBaseController {

    private String PREFIX = "/modular/act/model";

    @Autowired
    private ModelService modelService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/model.html";
    }

    /**
     * 新增页面
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/model_add.html";
    }

    /**
     * 编辑页面
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/model_edit.html";
    }

    /**
     * 代码查看页面
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/modelView")
    public String modelView() {
        return PREFIX + "/model_view.html";
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ActModel actModel) {
        String modelId = null;
        try {
            modelId = createModel(actModel.getProcessId(),
                    actModel.getProcessAuthor(),
                    actModel.getName(),
                    actModel.getModelname(),
                    actModel.getDescription(),
                    actModel.getCategory());
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
        }
        return new SuccessResponseData(modelId);
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ModelParam modelParam) {
        this.modelService.update(modelParam);
        return ResponseData.success();
    }

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:12
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseData delete(String modelId) {
        deleteModel(modelId);
        return new SuccessResponseData();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ModelParam modelParam) {
        Model detail = this.modelService.getById(modelParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ModelParam modelParam) {
        return this.modelService.findPageBySpec(modelParam);
    }

    /**
     * 获取当前用户
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:58
     */
    @RequestMapping(value = "/getAuthor")
    @ResponseBody
    public Object getAuthor() {
        Map<String, Object> map = new HashMap<>();
        String errInfo = "success";
        map.put("process_author", LoginContextHolder.getContext().getUser().getName());
        map.put("result", errInfo);
        return map;
    }

    /**
     * 部署流程定义
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:13
     */
    @RequestMapping(value = "/deployment")
    @ResponseBody
    public ResponseData deployment(String modelId) {
        try {
            //部署流程定义
            deploymentProcessDefinitionFromModelId(modelId);
        } catch (Exception e) {
            return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getMessage());
        }
        return new SuccessResponseData();
    }

    /**
     * 判断能否正常根据模型ID导出xml文件
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:15
     */
    @RequestMapping(value = "/isCanExportXml")
    @ResponseBody
    public ResponseData isCanexportXml(String modelId, HttpServletResponse response) {

        if (ToolUtil.isEmpty(modelId)) {
            throw new RequestEmptyException();
        }

        try {
            isCanexportXmlFromModelId(response, modelId);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
        }

        return new SuccessResponseData();
    }

    /**
     * 获取预览XML页面code
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:16
     */
    @RequestMapping(value = "/getXmlView")
    @ResponseBody
    public ResponseData getXmlView(String modelId) {
        if (ToolUtil.isEmpty(modelId)) {
            throw new RequestEmptyException();
        }

        String code = null;
        try {
            code = viewXmlFromModelId(modelId);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
        }
        return new SuccessResponseData(code);
    }

    /**
     * 正式根据模型ID导出xml文件
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:18
     */
    @RequestMapping(value = "/exportXml")
    public void exportXml(String modelId, HttpServletResponse response) throws Exception {
        if (ToolUtil.isEmpty(modelId)) {
            throw new RequestEmptyException();
        }

        exportXmlFromModelId(response, modelId);
    }

    /**
     * 去修改类型页面获取数据
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:18
     */
    @RequestMapping(value = "/goEdit")
    @ResponseBody
    public ResponseData goEdit(String modelId) {
        Model model = modelService.getById(modelId);
        return new SuccessResponseData(model);
    }

    /**
     * 修改类型
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:20
     */
    @RequestMapping(value = "/editCategory")
    @ResponseBody
    public ResponseData edit(ActModel actModel) {

        String modelId = actModel.getModelId();

        Model model = modelService.getById(modelId);
        model.setCategory(actModel.getCategory());

        modelService.updateById(model);
        return new SuccessResponseData();
    }

    /**
     * 从流程定义映射模型
     *
     * @author fengshuonan
     * @Date 2019/8/6 21:20
     */
    @RequestMapping(value = "/saveModelFromPro")
    @ResponseBody
    public ResponseData saveModelFrom(String processDefinitionId) {

        //流程定义ID
        if (ToolUtil.isEmpty(processDefinitionId)) {
            throw new RequestEmptyException();
        }

        try {
            super.saveModelFromPro(processDefinitionId);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
        }

        return new SuccessResponseData();
    }

}


