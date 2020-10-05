package cn.stylefeng.guns.modular.zj_position_name.mapper;

import cn.stylefeng.guns.modular.zj_position_name.entity.ZjPositionName;
import cn.stylefeng.guns.modular.zj_position_name.model.params.ZjPositionNameParam;
import cn.stylefeng.guns.modular.zj_position_name.model.result.ZjPositionNameResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工作职位名称表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
public interface ZjPositionNameMapper extends BaseMapper<ZjPositionName> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<ZjPositionNameResult> customList(@Param("paramCondition") ZjPositionNameParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjPositionNameParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    Page<ZjPositionNameResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjPositionNameParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjPositionNameParam paramCondition);

}
