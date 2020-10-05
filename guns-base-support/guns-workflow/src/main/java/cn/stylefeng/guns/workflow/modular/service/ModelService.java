package cn.stylefeng.guns.workflow.modular.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.entity.Model;
import cn.stylefeng.guns.workflow.modular.model.params.ModelParam;
import cn.stylefeng.guns.workflow.modular.model.result.ModelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-08-06
 */
public interface ModelService extends IService<Model> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    void add(ModelParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    void delete(ModelParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    void update(ModelParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    ModelResult findBySpec(ModelParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
    List<ModelResult> findListBySpec(ModelParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-08-06
     */
     LayuiPageInfo findPageBySpec(ModelParam param);

}
