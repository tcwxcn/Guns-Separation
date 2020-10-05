package cn.stylefeng.guns.modular.zj_user_resumes.mapper;

import cn.stylefeng.guns.modular.zj_user_resumes.entity.ZjUserResumes;
import cn.stylefeng.guns.modular.zj_user_resumes.model.params.ZjUserResumesParam;
import cn.stylefeng.guns.modular.zj_user_resumes.model.result.ZjUserResumesResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 个人简历信息表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-18
 */
public interface ZjUserResumesMapper extends BaseMapper<ZjUserResumes> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-18
     */
    List<ZjUserResumesResult> customList(@Param("paramCondition") ZjUserResumesParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjUserResumesParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-18
     */
    Page<ZjUserResumesResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjUserResumesParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjUserResumesParam paramCondition);

}
