package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.model.ActModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 模型管理Mapper
 *
 * @author fengshuonan
 * @Date 2019/8/5 22:37
 */
public interface ActModelMapper {

    /**
     * 列表
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:44
     */
    Page<Map<String, Object>> datalistPage(@Param("page") Page page, @Param("keywords") String keywords);

    /**
     * 通过id获取数据
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:44
     */
    Map<String, Object> findById(String modelId);

    /**
     * 修改
     *
     * @author fengshuonan
     * @Date 2019/8/6 20:45
     */
    void edit(ActModel actModel);

}

