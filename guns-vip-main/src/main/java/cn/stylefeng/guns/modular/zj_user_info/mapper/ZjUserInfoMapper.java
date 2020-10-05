package cn.stylefeng.guns.modular.zj_user_info.mapper;

import cn.stylefeng.guns.modular.zj_user_info.entity.ZjUserInfo;
import cn.stylefeng.guns.modular.zj_user_info.model.params.ZjUserInfoParam;
import cn.stylefeng.guns.modular.zj_user_info.model.result.ZjUserInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-22
 */
public interface ZjUserInfoMapper extends BaseMapper<ZjUserInfo> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-22
     */
    List<ZjUserInfoResult> customList(@Param("paramCondition") ZjUserInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZjUserInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-22
     */
    Page<ZjUserInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZjUserInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZjUserInfoParam paramCondition);

    /**
     * 通过手机号获取用户信息
     * @param phone
     * @return
     */
    ZjUserInfoResult findByPhone(@Param("phone") String phone);

}
