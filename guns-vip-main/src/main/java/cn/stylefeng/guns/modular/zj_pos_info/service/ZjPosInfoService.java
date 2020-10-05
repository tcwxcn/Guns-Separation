package cn.stylefeng.guns.modular.zj_pos_info.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_info.entity.ZjPosInfo;
import cn.stylefeng.guns.modular.zj_pos_info.model.params.ZjPosInfoParam;
import cn.stylefeng.guns.modular.zj_pos_info.model.result.ZjPosInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职业信息表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
public interface ZjPosInfoService extends IService<ZjPosInfo> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void add(ZjPosInfoParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void delete(ZjPosInfoParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void update(ZjPosInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
    ZjPosInfoResult findBySpec(ZjPosInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<ZjPosInfoResult> findListBySpec(ZjPosInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
     LayuiPageInfo findPageBySpec(ZjPosInfoParam param);

}
