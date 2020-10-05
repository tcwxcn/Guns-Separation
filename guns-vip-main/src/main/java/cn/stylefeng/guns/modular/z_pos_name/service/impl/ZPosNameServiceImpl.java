package cn.stylefeng.guns.modular.z_pos_name.service.impl;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_name.entity.ZPosName;
import cn.stylefeng.guns.modular.z_pos_name.mapper.ZPosNameMapper;
import cn.stylefeng.guns.modular.z_pos_name.model.params.ZPosNameParam;
import cn.stylefeng.guns.modular.z_pos_name.model.result.ZPosNameResult;
import  cn.stylefeng.guns.modular.z_pos_name.service.ZPosNameService;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 职位名称表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-30
 */
@Service
public class ZPosNameServiceImpl extends ServiceImpl<ZPosNameMapper, ZPosName> implements ZPosNameService {

    @Override
    public void add(ZPosNameParam param){
        if(ToolUtil.isEmpty(param.getPid()) || param.getPid().equals(0)){
            param.setPid(0L);
        }
        ZPosName entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void addPosName(ZPosName entity) {
        if(ToolUtil.isEmpty(entity.getPid()) || entity.getPid().equals(0)){
            entity.setPid(0L);
            this.save(entity);
        }
    }

    @Override
    public void delete(ZPosNameParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZPosNameParam param){
        ZPosName oldEntity = getOldEntity(param);
        ZPosName newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZPosNameResult findBySpec(ZPosNameParam param){
        return null;
    }

    @Override
    public List<ZPosNameResult> findListBySpec(ZPosNameParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZPosNameParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<LayuiTreeNode> layuiTree() {
        return this.baseMapper.layuiTree();
    }

    private Serializable getKey(ZPosNameParam param){
        return param.getPosNameId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZPosName getOldEntity(ZPosNameParam param) {
        return this.getById(getKey(param));
    }

    private ZPosName getEntity(ZPosNameParam param) {
        ZPosName entity = new ZPosName();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
