package cn.stylefeng.guns.modular.z_partner.mapper;

import cn.stylefeng.guns.modular.z_partner.entity.ZPartner;
import cn.stylefeng.guns.modular.z_partner.model.params.ZPartnerParam;
import cn.stylefeng.guns.modular.z_partner.model.result.ZPartnerResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合作伙伴表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
public interface ZPartnerMapper extends BaseMapper<ZPartner> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<ZPartnerResult> customList(@Param("paramCondition") ZPartnerParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZPartnerParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    Page<ZPartnerResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZPartnerParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-29
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZPartnerParam paramCondition);

}
