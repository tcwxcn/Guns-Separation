package cn.stylefeng.guns.modular.zj_user_resumes.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_relation_resumes.entity.ZjRelationResumes;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.params.ZjRelationResumesParam;
import cn.stylefeng.guns.modular.zj_relation_resumes.service.ZjRelationResumesService;
import cn.stylefeng.guns.modular.zj_relation_resumes.service.impl.ZjRelationResumesServiceImpl;
import cn.stylefeng.guns.modular.zj_user_info.entity.ZjUserInfo;
import cn.stylefeng.guns.modular.zj_user_info.mapper.ZjUserInfoMapper;
import cn.stylefeng.guns.modular.zj_user_info.model.params.ZjUserInfoParam;
import cn.stylefeng.guns.modular.zj_user_info.model.result.ZjUserInfoResult;
import cn.stylefeng.guns.modular.zj_user_info.service.ZjUserInfoService;
import cn.stylefeng.guns.modular.zj_user_resumes.entity.ZjUserResumes;
import cn.stylefeng.guns.modular.zj_user_resumes.mapper.ZjUserResumesMapper;
import cn.stylefeng.guns.modular.zj_user_resumes.model.params.ZjUserResumesParam;
import cn.stylefeng.guns.modular.zj_user_resumes.model.result.ZjUserResumesResult;
import  cn.stylefeng.guns.modular.zj_user_resumes.service.ZjUserResumesService;
import cn.stylefeng.guns.sys.modular.system.entity.UserPos;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 个人简历信息表 服务实现类
 * </p>
 *
 * @author Hua
 * @since 2020-09-18
 */
@Service
public class ZjUserResumesServiceImpl extends ServiceImpl<ZjUserResumesMapper, ZjUserResumes> implements ZjUserResumesService {

    @Autowired
    ZjRelationResumesServiceImpl zjRelationResumesService;

    @Autowired
    ZjUserInfoService zjUserInfoService;
    @Override
    public void add(ZjUserResumesParam param){

        ZjUserResumes entity = getEntity(param);
        this.save(entity);
        //.添加用户和职位的关联表
        addUserPos(entity);
    }

    /**
     * .添加用户职位关联信息
     * @param entity
     */
    @Override
    public void addUserPos(ZjUserResumes entity) {

//        String phone = entity.getPhone();
//        ZjUserInfoParam zjUserInfoParam = new ZjUserInfoParam();
//        zjUserInfoParam.setPhone(phone);
//        ZjUserInfoResult zjUserInfoResult = zjUserInfoService.findBySpec(zjUserInfoParam);
//        if(zjUserInfoResult != null){
//            long userInfoId = zjUserInfoResult.getUserInfoId();
//            long resumesId = entity.getResumesId();
//            ZjRelationResumes entity11  = new ZjRelationResumes();
//            entity11.setResumesId(resumesId);
//            entity11.setUserInfoId(userInfoId);
//            zjRelationResumesService.save(entity11);
//        }

        long resumesId = entity.getResumesId();
        //获取登录用户的id
        long userId = LoginContextHolder.getContext().getUserId();
        ZjRelationResumes entity11  = new ZjRelationResumes();
        entity11.setResumesId(resumesId);
        entity11.setUserInfoId(userId);
        zjRelationResumesService.save(entity11);


    }

    @Override
    public void delete(ZjUserResumesParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ZjUserResumesParam param){
        ZjUserResumes oldEntity = getOldEntity(param);
        ZjUserResumes newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ZjUserResumesResult findBySpec(ZjUserResumesParam param){
        return null;
    }

    @Override
    public List<ZjUserResumesResult> findListBySpec(ZjUserResumesParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ZjUserResumesParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ZjUserResumesParam param){
        return param.getResumesId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ZjUserResumes getOldEntity(ZjUserResumesParam param) {
        return this.getById(getKey(param));
    }

    private ZjUserResumes getEntity(ZjUserResumesParam param) {
        ZjUserResumes entity = new ZjUserResumes();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
