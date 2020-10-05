package cn.stylefeng.guns.modular.z_pos_name.service;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_pos_name.entity.ZPosName;
import cn.stylefeng.guns.modular.z_pos_name.model.params.ZPosNameParam;
import cn.stylefeng.guns.modular.z_pos_name.model.result.ZPosNameResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位名称表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-30
 */
public interface ZPosNameService extends IService<ZPosName> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-30
     */
    void add(ZPosNameParam param);/**

     * 新增职位名称
     *
     * @author Hua
     * @Date 2020-09-30
     */
    void addPosName(ZPosName entity);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-30
     */
    void delete(ZPosNameParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-30
     */
    void update(ZPosNameParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-30
     */
    ZPosNameResult findBySpec(ZPosNameParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-30
     */
    List<ZPosNameResult> findListBySpec(ZPosNameParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-30
     */
     LayuiPageInfo findPageBySpec(ZPosNameParam param);

    /**
     * .获取职位名称的tree列表，layuiTree格式
     * @return
     */
    List<LayuiTreeNode> layuiTree();
}
