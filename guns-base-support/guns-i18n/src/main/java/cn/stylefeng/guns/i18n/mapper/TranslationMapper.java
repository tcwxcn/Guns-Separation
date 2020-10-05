package cn.stylefeng.guns.i18n.mapper;

import cn.stylefeng.guns.base.i18n.entity.Translation;
import cn.stylefeng.guns.base.i18n.model.params.TranslationParam;
import cn.stylefeng.guns.base.i18n.model.result.TranslationResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 多语言表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-17
 */
public interface TranslationMapper extends BaseMapper<Translation> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    List<TranslationResult> customList(@Param("paramCondition") TranslationParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TranslationParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    Page<TranslationResult> customPageList(@Param("page") Page page, @Param("paramCondition") TranslationParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-10-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TranslationParam paramCondition);

}
