package cn.stylefeng.guns.modular.position.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.position.entity.SysPosition;
import cn.stylefeng.guns.modular.position.model.params.SysPositionParam;
import cn.stylefeng.guns.modular.position.model.result.SysPositionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
public interface SysPositionService extends IService<SysPosition> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void add(SysPositionParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void delete(SysPositionParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void update(SysPositionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
    SysPositionResult findBySpec(SysPositionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<SysPositionResult> findListBySpec(SysPositionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
     LayuiPageInfo findPageBySpec(SysPositionParam param);

}
