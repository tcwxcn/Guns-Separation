package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.Model;
import cn.stylefeng.guns.workflow.modular.model.params.ModelParam;
import cn.stylefeng.guns.workflow.modular.model.result.ModelResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-06
 */
public interface ModelMapper extends BaseMapper<Model> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    List<ModelResult> customList(@Param("paramCondition") ModelParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ModelParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    Page<ModelResult> customPageList(@Param("page") Page page, @Param("paramCondition") ModelParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ModelParam paramCondition);

}
