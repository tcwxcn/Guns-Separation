package cn.stylefeng.guns.modular.z_case_show.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_case_show.entity.ZCaseShow;
import cn.stylefeng.guns.modular.z_case_show.model.params.ZCaseShowParam;
import cn.stylefeng.guns.modular.z_case_show.model.result.ZCaseShowResult;
import cn.stylefeng.guns.modular.z_case_show.service.ZCaseShowService;
import cn.stylefeng.guns.sys.modular.system.model.UploadResult;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 案例展示表控制器
 *
 * @author Hua
 * @Date 2020-09-29 11:42:00
 */
@Controller
@RequestMapping("/zCaseShow")
public class ZCaseShowController extends BaseController {

    private String PREFIX = "/modular/zCaseShow";

    @Autowired
    private ZCaseShowService zCaseShowService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/zCaseShow.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/zCaseShow_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/zCaseShow_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ZCaseShowParam zCaseShowParam) {
        this.zCaseShowService.add(zCaseShowParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ZCaseShowParam zCaseShowParam) {
        this.zCaseShowService.update(zCaseShowParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ZCaseShowParam zCaseShowParam) {
        this.zCaseShowService.delete(zCaseShowParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ZCaseShowParam zCaseShowParam) {
        ZCaseShow detail = this.zCaseShowService.getById(zCaseShowParam.getCaseShowId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ZCaseShowParam zCaseShowParam) {
        return this.zCaseShowService.findPageBySpec(zCaseShowParam);
    }

    @Autowired
    FileInfoService fileInfoService;

    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile file, @RequestPart("title") String title) {

        UploadResult uploadResult = this.fileInfoService.uploadFile(file);
        String fileId = uploadResult.getFileId();

        //设置上传图片的id和标题并插入数据
        ZCaseShowParam zCaseShowParam = new ZCaseShowParam();
        zCaseShowParam.setPicAddress(fileId);
        zCaseShowParam.setTitle(title);
        this.zCaseShowService.add(zCaseShowParam);

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", fileId);

        return ResponseData.success(0, "上传成功", map);
    }

    /**
     * 查看案例展示图片
     */
    @RequestMapping("/viewPics")
    @ResponseBody
    public Object viewPics(HttpServletResponse response) {

        //输出图片的文件流
        try {
            response.setContentType("image/jpeg");
//            Map<byte[], String> map = this.zCaseShowService.viewPics();
            List<byte[]> decodes = this.zCaseShowService.viewPics();
            for (byte[] decode : decodes) {

                response.getOutputStream().write(decode);
            }
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }

    /**
     * 获取案例展示图片所以地址和标题
     */
    @RequestMapping("/findAllPicturePath")
    @ResponseBody
    public Object findAllPicturePath(HttpServletResponse response) {

        List<Map<String,String>> map = this.zCaseShowService.findAllPicturePath();
        return map;
    }

}


