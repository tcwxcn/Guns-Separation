package cn.stylefeng.guns.modular.zj_user_info.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_user_info.entity.ZjUserInfo;
import cn.stylefeng.guns.modular.zj_user_info.model.params.ZjUserInfoParam;
import cn.stylefeng.guns.modular.zj_user_info.model.result.ZjUserInfoResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-22
 */
public interface ZjUserInfoService extends IService<ZjUserInfo> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-22
     */
    void add(ZjUserInfoParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-22
     */
    void delete(ZjUserInfoParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-22
     */
    void update(ZjUserInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-22
     */
    ZjUserInfoResult findBySpec(ZjUserInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-22
     */
    List<ZjUserInfoResult> findListBySpec(ZjUserInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-22
     */
     LayuiPageInfo findPageBySpec(ZjUserInfoParam param);


}
