package cn.stylefeng.guns.modular.z_case_show.mapper;

import cn.stylefeng.guns.modular.z_case_show.entity.ZCaseShow;
import cn.stylefeng.guns.modular.z_case_show.model.params.ZCaseShowParam;
import cn.stylefeng.guns.modular.z_case_show.model.result.ZCaseShowResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 案例展示表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
public interface ZCaseShowMapper extends BaseMapper<ZCaseShow> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<ZCaseShowResult> customList(@Param("paramCondition") ZCaseShowParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZCaseShowParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    Page<ZCaseShowResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZCaseShowParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZCaseShowParam paramCondition);

}
