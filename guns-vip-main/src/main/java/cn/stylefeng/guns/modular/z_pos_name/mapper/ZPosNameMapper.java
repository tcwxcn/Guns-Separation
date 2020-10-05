package cn.stylefeng.guns.modular.z_pos_name.mapper;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.modular.z_pos_name.entity.ZPosName;
import cn.stylefeng.guns.modular.z_pos_name.model.params.ZPosNameParam;
import cn.stylefeng.guns.modular.z_pos_name.model.result.ZPosNameResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位名称表 Mapper 接口
 * </p>
 *
 * @author Hua
 * @since 2020-09-30
 */
public interface ZPosNameMapper extends BaseMapper<ZPosName> {

    /**
     * 获取列表
     *
     * @author Hua
     * @Date 2020-09-30
     */
    List<ZPosNameResult> customList(@Param("paramCondition") ZPosNameParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Hua
     * @Date 2020-09-30
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ZPosNameParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Hua
     * @Date 2020-09-30
     */
    Page<ZPosNameResult> customPageList(@Param("page") Page page, @Param("paramCondition") ZPosNameParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Hua
     * @Date 2020-09-30
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ZPosNameParam paramCondition);

    /**
     * 获取职位名称的tree列表，layuiTree格式
     * @return
     */
    List<LayuiTreeNode> layuiTree();
}
