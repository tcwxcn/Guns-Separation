package cn.stylefeng.guns.modular.position.mapper;

import cn.stylefeng.guns.modular.position.entity.SysPosition;
import cn.stylefeng.guns.modular.position.model.params.SysPositionParam;
import cn.stylefeng.guns.modular.position.model.result.SysPositionResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
public interface SysPositionMapper extends BaseMapper<SysPosition> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<SysPositionResult> customList(@Param("paramCondition") SysPositionParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SysPositionParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    Page<SysPositionResult> customPageList(@Param("page") Page page, @Param("paramCondition") SysPositionParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SysPositionParam paramCondition);

}
