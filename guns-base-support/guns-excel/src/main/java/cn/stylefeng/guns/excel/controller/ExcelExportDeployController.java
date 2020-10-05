package cn.stylefeng.guns.excel.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.util.FileDownload;
import cn.stylefeng.guns.excel.entity.ExcelExportDeploy;
import cn.stylefeng.guns.excel.model.params.ExcelExportDeployParam;
import cn.stylefeng.guns.excel.service.ExcelExportDeployService;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.model.UploadResult;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import static cn.stylefeng.guns.excel.consts.ExcelConstants.EXCEL_FILE_TEMPLATE_PATH;

/**
 * excel导出配置控制器
 *
 * @author York
 * @Date 2019-11-26 16:52:02
 */
@Controller
@RequestMapping("/excelExportDeploy")
public class ExcelExportDeployController extends BaseController {

    private String PREFIX = "/modular/excel";

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private ExcelExportDeployService excelExportDeployService;

    /**
     * 跳转到主页面
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/excelExportDeploy.html";
    }

    /**
     * 新增页面
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/excelExportDeploy_add.html";
    }

    /**
     * 编辑页面
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/excelExportDeploy_edit.html";
    }

    /**
     * 新增接口
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ExcelExportDeployParam excelExportDeployParam) {
        this.excelExportDeployService.add(excelExportDeployParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ExcelExportDeployParam excelExportDeployParam) {
        this.excelExportDeployService.update(excelExportDeployParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ExcelExportDeployParam excelExportDeployParam) {
        this.excelExportDeployService.delete(excelExportDeployParam);
        return ResponseData.success();
    }

    /**
     * 上传模版文件
     *
     * @return
     */
    @RequestMapping("/uploadTemplate")
    @ResponseBody
    public ResponseData uploadTemplate(@RequestPart("file") MultipartFile file) {
        try {
            if (file == null) {
                return ResponseData.error("请选择要上传的模版文件");
            }

            //上传路径设置
            String fileSavePath = ConstantsContext.getFileUploadPath();
            fileSavePath = fileSavePath + EXCEL_FILE_TEMPLATE_PATH;

            UploadResult uploadResult = fileInfoService.uploadFile(file, fileSavePath);
            if (!uploadResult.getOriginalFilename().contains(".xls")) {
                return ResponseData.error("上传的模版文件必须为2003版的excel文件");
            }

            return ResponseData.success(EXCEL_FILE_TEMPLATE_PATH + uploadResult.getFinalName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(e.getMessage());
        }

    }

    /**
     * 下载模板文件
     *
     * @author fengshuonan
     * @Date 2019-2-23 10:48:29
     */
    @RequestMapping(path = "/download/{fileFinalName}")
    public void download(@PathVariable String fileFinalName, HttpServletResponse httpServletResponse) {

        //上传路径设置
        String fileSavePath = ConstantsContext.getFileUploadPath();
        fileSavePath = fileSavePath + EXCEL_FILE_TEMPLATE_PATH;

        //查找文件信息
        FileInfo fileInfo = fileInfoService.getByFinalName(fileFinalName);

        try {
            FileDownload.fileDownload(httpServletResponse, fileSavePath + fileFinalName, fileInfo.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查看详情接口
     *
     * @author York
     * @Date 2019-11-26
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ExcelExportDeployParam excelExportDeployParam) {
        ExcelExportDeploy detail = this.excelExportDeployService.getById(excelExportDeployParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author York
     * @Date 2019-11-26
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ExcelExportDeployParam excelExportDeployParam) {
        return this.excelExportDeployService.findPageBySpec(excelExportDeployParam);
    }

}
