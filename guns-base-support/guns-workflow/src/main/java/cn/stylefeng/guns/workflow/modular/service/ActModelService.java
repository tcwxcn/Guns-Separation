package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.workflow.modular.model.ActModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * 模型管理接口
 *
 * @author fengshuonan
 * @Date 2019/8/5 22:49
 */
public interface ActModelService {

    /**
     * 列表
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:51
     */
    Page<Map<String, Object>> list(String keywords);

    /**
     * 通过id获取数据
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:51
     */
    Map<String, Object> findById(String modelId);

    /**
     * 修改
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:51
     */
    void edit(ActModel actModel);

}

