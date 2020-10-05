package cn.stylefeng.guns.modular.z_partner.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_partner.entity.ZPartner;
import cn.stylefeng.guns.modular.z_partner.model.params.ZPartnerParam;
import cn.stylefeng.guns.modular.z_partner.model.result.ZPartnerResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 合作伙伴表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
public interface ZPartnerService extends IService<ZPartner> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void add(ZPartnerParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void delete(ZPartnerParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void update(ZPartnerParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
    ZPartnerResult findBySpec(ZPartnerParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<ZPartnerResult> findListBySpec(ZPartnerParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
     LayuiPageInfo findPageBySpec(ZPartnerParam param);

}
