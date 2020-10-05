package cn.stylefeng.guns.modular.zj_pos_type.mapper;

import cn.stylefeng.guns.modular.zj_pos_type.entity.ZjPosType;
import cn.stylefeng.guns.modular.zj_pos_type.model.params.ZjPosTypeParam;
import cn.stylefeng.guns.modular.zj_pos_type.model.result.ZjPosTypeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位类型表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
public interface ZjPosTypeMapper extends BaseMapper<ZjPosType> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<ZjPosTypeResult> customList(@Param("paramCondition") ZjPosTypeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjPosTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    Page<ZjPosTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjPosTypeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjPosTypeParam paramCondition);

}
