package cn.stylefeng.guns.modular.zj_position_name.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_position_name.entity.ZjPositionName;
import cn.stylefeng.guns.modular.zj_position_name.model.params.ZjPositionNameParam;
import cn.stylefeng.guns.modular.zj_position_name.model.result.ZjPositionNameResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工作职位名称表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
public interface ZjPositionNameService extends IService<ZjPositionName> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void add(ZjPositionNameParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void delete(ZjPositionNameParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void update(ZjPositionNameParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
    ZjPositionNameResult findBySpec(ZjPositionNameParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<ZjPositionNameResult> findListBySpec(ZjPositionNameParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
     LayuiPageInfo findPageBySpec(ZjPositionNameParam param);

}
