package cn.stylefeng.guns.i18n.service;

import cn.stylefeng.guns.base.i18n.service.TranslationService;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.i18n.entity.Translation;
import cn.stylefeng.guns.i18n.mapper.TranslationMapper;
import cn.stylefeng.guns.base.i18n.model.params.TranslationParam;
import cn.stylefeng.guns.base.i18n.model.result.TranslationResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 多语言表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-17
 */
@Service
public class TranslationServiceImpl extends ServiceImpl<TranslationMapper, Translation> implements TranslationService {

    @Override
    public void add(TranslationParam param){
        Translation entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TranslationParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TranslationParam param){
        Translation oldEntity = getOldEntity(param);
        Translation newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TranslationResult findBySpec(TranslationParam param){
        return null;
    }

    @Override
    public List<TranslationResult> findListBySpec(TranslationParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TranslationParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TranslationParam param){
        return param.getTranId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Translation getOldEntity(TranslationParam param) {
        return this.getById(getKey(param));
    }

    private Translation getEntity(TranslationParam param) {
        Translation entity = new Translation();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
