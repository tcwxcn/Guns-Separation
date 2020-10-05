package cn.stylefeng.guns.modular.user.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.user.entity.SysUser;
import cn.stylefeng.guns.modular.user.model.params.SysUserParam;
import cn.stylefeng.guns.modular.user.model.result.SysUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-15
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void add(SysUserParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void delete(SysUserParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-15
     */
    void update(SysUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
    SysUserResult findBySpec(SysUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
    List<SysUserResult> findListBySpec(SysUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-15
     */
     LayuiPageInfo findPageBySpec(SysUserParam param);

}
