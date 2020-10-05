package cn.stylefeng.guns.modular.user.mapper;

import cn.stylefeng.guns.modular.user.entity.SysUser;
import cn.stylefeng.guns.modular.user.model.params.SysUserParam;
import cn.stylefeng.guns.modular.user.model.result.SysUserResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<SysUserResult> customList(@Param("paramCondition") SysUserParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SysUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    Page<SysUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") SysUserParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SysUserParam paramCondition);

}
