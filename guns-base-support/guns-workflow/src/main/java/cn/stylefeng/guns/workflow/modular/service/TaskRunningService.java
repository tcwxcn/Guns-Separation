package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.RuTask;
import cn.stylefeng.guns.workflow.modular.model.params.RuTaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.RuTaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-22
 */
public interface TaskRunningService extends IService<RuTask> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    void add(RuTaskParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    void delete(RuTaskParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    void update(RuTaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    RuTaskResult findBySpec(RuTaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    List<RuTaskResult> findListBySpec(RuTaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    LayuiPageInfo findPageBySpec(RuTaskParam param);

}
