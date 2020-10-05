package cn.stylefeng.guns.modular.z_case_show.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_case_show.entity.ZCaseShow;
import cn.stylefeng.guns.modular.z_case_show.mapper.ZCaseShowMapper;
import cn.stylefeng.guns.modular.z_case_show.model.params.ZCaseShowParam;
import cn.stylefeng.guns.modular.z_case_show.model.result.ZCaseShowResult;
import cn.stylefeng.guns.modular.z_case_show.service.ZCaseShowService;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.spi.SyncResolver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 案例展示表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
@Service
public class ZCaseShowServiceImpl extends ServiceImpl<ZCaseShowMapper, ZCaseShow> implements ZCaseShowService {

    @Override
    public void add(ZCaseShowParam param) {
        ZCaseShow entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZCaseShowParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZCaseShowParam param) {
        ZCaseShow oldEntity = getOldEntity(param);
        ZCaseShow newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZCaseShowResult findBySpec(ZCaseShowParam param) {
        return null;
    }

    @Override
    public List<ZCaseShowResult> findListBySpec(ZCaseShowParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZCaseShowParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public List<byte[]> viewPics() {
        List<ZCaseShow> zCaseShows = this.list();
        List<byte[]> list = new LinkedList<>();

        for (ZCaseShow zCaseShow : zCaseShows) {
            String picAddressId = zCaseShow.getPicAddress();
            FileInfo fileInfo = fileInfoService.getById(picAddressId);
            if (fileInfo != null) {
                try {
                    String filePath = fileInfo.getFilePath();
                    byte[] bytes = IoUtil.readBytes(new FileInputStream(filePath));
                    list.add(bytes);
                } catch (FileNotFoundException e) {
                    log.error("图片未找到！", e);
                    return null;
                }
            }
        }
        return list;
    }

    @Override
    public List<Map<String,String>> findAllPicturePath() {
        List<ZCaseShow> zCaseShows = this.list();
        Map<String, String> filePaths = new HashMap<>();
        Map<String, String> fileTitles = new HashMap<>();
        List list = new LinkedList();

        for (ZCaseShow zCaseShow : zCaseShows) {
            String picAddressId = zCaseShow.getPicAddress();
            FileInfo fileInfo = fileInfoService.getById(picAddressId);

            String filePath = fileInfo.getFilePath();
            String fileTitle = zCaseShow.getTitle();
            filePaths.put("filePath", filePath);
            fileTitles.put("fileTitle", fileTitle);
            list.add(filePaths);
            list.add(fileTitles);
        }

        return list;
    }

    private Serializable getKey(ZCaseShowParam param) {
        return param.getCaseShowId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZCaseShow getOldEntity(ZCaseShowParam param) {
        return this.getById(getKey(param));
    }

    private ZCaseShow getEntity(ZCaseShowParam param) {
        ZCaseShow entity = new ZCaseShow();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
