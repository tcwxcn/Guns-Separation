package cn.stylefeng.guns.modular.z_pos_info.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_info.entity.ZPosInfo;
import cn.stylefeng.guns.modular.z_pos_info.mapper.ZPosInfoMapper;
import cn.stylefeng.guns.modular.z_pos_info.model.params.ZPosInfoParam;
import cn.stylefeng.guns.modular.z_pos_info.model.result.ZPosInfoResult;
import cn.stylefeng.guns.modular.z_pos_info.service.ZPosInfoService;
import cn.stylefeng.guns.modular.z_pos_name.entity.ZPosName;
import cn.stylefeng.guns.modular.z_pos_name.service.ZPosNameService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 职位信息表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-27
 */
@Service
public class ZPosInfoServiceImpl extends ServiceImpl<ZPosInfoMapper, ZPosInfo> implements ZPosInfoService {

    @Override
    public void add(ZPosInfoParam param) {
        ZPosInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZPosInfoParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZPosInfoParam param) {
        ZPosInfo oldEntity = getOldEntity(param);
        ZPosInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZPosInfoResult findBySpec(ZPosInfoParam param) {
        return null;
    }

    @Override
    public List<ZPosInfoResult> findListBySpec(ZPosInfoParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZPosInfoParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Autowired
    ZPosNameService zPosNameService;

    @Override
    public LayuiPageInfo findPosInfoByPosNameId(Long id, String location, String welfare) {
        ZPosName posName = zPosNameService.getById(id);
        String name = "";
        ZPosInfoParam param = new ZPosInfoParam();

        //设置zPosInfoParam对象的name或location或welfare作为查询条件
        if (posName != null) {
            name = posName.getName();
            param.setPosName(name);
        }
        if (location != null) {
            param.setLocation(location);
        }
        if (welfare != null) {
            param.setWelfare(welfare);
        }

        Page pageContext = getPageContext();
        IPage page = baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZPosInfoParam param) {
        return param.getPosInfoId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZPosInfo getOldEntity(ZPosInfoParam param) {
        return this.getById(getKey(param));
    }

    private ZPosInfo getEntity(ZPosInfoParam param) {
        ZPosInfo entity = new ZPosInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
