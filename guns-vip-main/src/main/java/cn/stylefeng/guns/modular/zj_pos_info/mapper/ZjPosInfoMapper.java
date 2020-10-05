package cn.stylefeng.guns.modular.zj_pos_info.mapper;

import cn.stylefeng.guns.modular.zj_pos_info.entity.ZjPosInfo;
import cn.stylefeng.guns.modular.zj_pos_info.model.params.ZjPosInfoParam;
import cn.stylefeng.guns.modular.zj_pos_info.model.result.ZjPosInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职业信息表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
public interface ZjPosInfoMapper extends BaseMapper<ZjPosInfo> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<ZjPosInfoResult> customList(@Param("paramCondition") ZjPosInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjPosInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    Page<ZjPosInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjPosInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjPosInfoParam paramCondition);

}
