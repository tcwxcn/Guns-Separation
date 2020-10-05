package cn.stylefeng.guns.tenant.mapper;

import cn.stylefeng.guns.base.tenant.entity.TenantInfo;
import cn.stylefeng.guns.base.tenant.model.params.TenantInfoParam;
import cn.stylefeng.guns.base.tenant.model.result.TenantInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-16
 */
public interface TenantInfoMapper extends BaseMapper<TenantInfo> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    List<TenantInfoResult> customList(@Param("paramCondition") TenantInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TenantInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    Page<TenantInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") TenantInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-06-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TenantInfoParam paramCondition);

}
