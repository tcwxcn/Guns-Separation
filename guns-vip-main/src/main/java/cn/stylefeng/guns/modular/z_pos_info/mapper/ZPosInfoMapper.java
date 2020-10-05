package cn.stylefeng.guns.modular.z_pos_info.mapper;

import cn.stylefeng.guns.modular.z_pos_info.entity.ZPosInfo;
import cn.stylefeng.guns.modular.z_pos_info.model.params.ZPosInfoParam;
import cn.stylefeng.guns.modular.z_pos_info.model.result.ZPosInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位信息表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-27
 */
public interface ZPosInfoMapper extends BaseMapper<ZPosInfo> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-27
     */
    List<ZPosInfoResult> customList(@Param("paramCondition") ZPosInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-27
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZPosInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-27
     */
    Page<ZPosInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZPosInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-27
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZPosInfoParam paramCondition);

}
