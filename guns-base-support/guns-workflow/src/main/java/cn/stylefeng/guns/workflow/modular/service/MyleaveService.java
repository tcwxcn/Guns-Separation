package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Myleave;
import cn.stylefeng.guns.workflow.modular.model.params.MyLeaveParam;
import cn.stylefeng.guns.workflow.modular.model.result.MyleaveResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-20
 */
public interface MyleaveService extends IService<Myleave> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    void add(MyLeaveParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    void delete(MyLeaveParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    void update(MyLeaveParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    MyleaveResult findBySpec(MyLeaveParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    List<MyleaveResult> findListBySpec(MyLeaveParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
     LayuiPageInfo findPageBySpec(MyLeaveParam param);

    /**
     * 获取请假类型
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    List<Map<String,Object>> getLeaves();

    /**
     * 批量删除请假单
     *
     * @author fengshuonan
     * @Date 2019-08-27
     */
    void batchDeleteByIds(List<String> ids);
}
