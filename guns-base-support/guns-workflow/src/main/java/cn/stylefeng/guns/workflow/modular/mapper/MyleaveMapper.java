package cn.stylefeng.guns.workflow.modular.mapper;

import cn.stylefeng.guns.workflow.modular.entity.Myleave;
import cn.stylefeng.guns.workflow.modular.model.params.MyLeaveParam;
import cn.stylefeng.guns.workflow.modular.model.result.MyleaveResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
public interface MyleaveMapper extends BaseMapper<Myleave> {

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    List<MyleaveResult> customList(@Param("paramCondition") MyLeaveParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") MyLeaveParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    Page<MyleaveResult> customPageList(@Param("page") Page page, @Param("paramCondition") MyLeaveParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    Page<Map<String, Object>> datalistPage(@Param("page") Page page, @Param("pd") MyLeaveParam paramCondition);

    /**
     * 获取请假类型
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    List<Map<String,Object>> getLeaves();
}
