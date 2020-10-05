package cn.stylefeng.guns.modular.zj_relation_resumes.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_relation_resumes.entity.ZjRelationResumes;
import cn.stylefeng.guns.modular.zj_relation_resumes.mapper.ZjRelationResumesMapper;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.params.ZjRelationResumesParam;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.result.ZjRelationResumesResult;
import  cn.stylefeng.guns.modular.zj_relation_resumes.service.ZjRelationResumesService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 个人简历信息和用户信息关联表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
@Service
public class ZjRelationResumesServiceImpl extends ServiceImpl<ZjRelationResumesMapper, ZjRelationResumes> implements ZjRelationResumesService {

    @Override
    public void add(ZjRelationResumesParam param){
        ZjRelationResumes entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ZjRelationResumesParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjRelationResumesParam param){
        ZjRelationResumes oldEntity = getOldEntity(param);
        ZjRelationResumes newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjRelationResumesResult findBySpec(ZjRelationResumesParam param){
        return null;
    }

    @Override
    public List<ZjRelationResumesResult> findListBySpec(ZjRelationResumesParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjRelationResumesParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjRelationResumesParam param){
        return param.getRelationId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjRelationResumes getOldEntity(ZjRelationResumesParam param) {
        return this.getById(getKey(param));
    }

     public ZjRelationResumes getEntity(ZjRelationResumesParam param) {
        ZjRelationResumes entity = new ZjRelationResumes();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
