package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Task;
import cn.stylefeng.guns.workflow.modular.model.params.TaskParam;
import cn.stylefeng.guns.workflow.modular.model.result.TaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
public interface TaskWaitingService extends IService<Task> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    void add(TaskParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    void delete(TaskParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    void update(TaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    TaskResult findBySpec(TaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    List<TaskResult> findListBySpec(TaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    LayuiPageInfo findPageBySpec(TaskParam param);

    /**
     * 查询登录人角色信息（返回字符串）
     *
     * @author fengshuonan
     * @Date 2019-08-26
     */
    String getUserRoleString();

}
