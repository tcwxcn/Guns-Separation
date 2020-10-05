package cn.stylefeng.guns.modular.zj_pos_type.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_pos_type.entity.ZjPosType;
import cn.stylefeng.guns.modular.zj_pos_type.model.params.ZjPosTypeParam;
import cn.stylefeng.guns.modular.zj_pos_type.model.result.ZjPosTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位类型表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-17
 */
public interface ZjPosTypeService extends IService<ZjPosType> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void add(ZjPosTypeParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void delete(ZjPosTypeParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-17
     */
    void update(ZjPosTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
    ZjPosTypeResult findBySpec(ZjPosTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
    List<ZjPosTypeResult> findListBySpec(ZjPosTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-17
     */
     LayuiPageInfo findPageBySpec(ZjPosTypeParam param);

}
