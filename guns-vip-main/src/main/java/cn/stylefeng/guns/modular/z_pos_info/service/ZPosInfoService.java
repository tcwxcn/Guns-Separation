package cn.stylefeng.guns.modular.z_pos_info.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_info.entity.ZPosInfo;
import cn.stylefeng.guns.modular.z_pos_info.model.params.ZPosInfoParam;
import cn.stylefeng.guns.modular.z_pos_info.model.result.ZPosInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位信息表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-27
 */
public interface ZPosInfoService extends IService<ZPosInfo> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-27
     */
    void add(ZPosInfoParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-27
     */
    void delete(ZPosInfoParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-27
     */
    void update(ZPosInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-27
     */
    ZPosInfoResult findBySpec(ZPosInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-27
     */
    List<ZPosInfoResult> findListBySpec(ZPosInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-27
     */
     LayuiPageInfo findPageBySpec(ZPosInfoParam param);

    /**
     * 通过职位名称id或职位地址或福利查询职位信息
     * @param id
     * @return
     */
     LayuiPageInfo findPosInfoByPosNameId(Long id,String location,String welfare);

}
