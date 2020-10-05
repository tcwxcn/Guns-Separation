package cn.stylefeng.guns.workflow.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.workflow.modular.mapper.ActModelMapper;
import cn.stylefeng.guns.workflow.modular.model.ActModel;
import cn.stylefeng.guns.workflow.modular.service.ActModelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 模型管理接口实现类
 *
 * @author fengshuonan
 * @Date 2019/8/5 22:47
 */
@Service
@Transactional
public class ActModelServiceImpl implements ActModelService {

    @Autowired
    private ActModelMapper actModelMapper;

    /**
     * 列表
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:58
     */
    @Override
    public Page<Map<String, Object>> list(String keywords) {
        Page page = LayuiPageFactory.defaultPage();
        return actModelMapper.datalistPage(page, keywords);
    }

    /**
     * 通过id获取数据
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:58
     */
    @Override
    public Map<String, Object> findById(String modelId) {
        return actModelMapper.findById(modelId);
    }

    /**
     * 修改
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:58
     */
    public void edit(ActModel actModel) {
        actModelMapper.edit(actModel);
    }

}

