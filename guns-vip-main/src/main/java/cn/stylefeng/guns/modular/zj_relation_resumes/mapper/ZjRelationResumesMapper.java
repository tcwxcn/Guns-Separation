package cn.stylefeng.guns.modular.zj_relation_resumes.mapper;

import cn.stylefeng.guns.modular.zj_relation_resumes.entity.ZjRelationResumes;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.params.ZjRelationResumesParam;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.result.ZjRelationResumesResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 个人简历信息和用户信息关联表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
public interface ZjRelationResumesMapper extends BaseMapper<ZjRelationResumes> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<ZjRelationResumesResult> customList(@Param("paramCondition") ZjRelationResumesParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjRelationResumesParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    Page<ZjRelationResumesResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjRelationResumesParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjRelationResumesParam paramCondition);

}
