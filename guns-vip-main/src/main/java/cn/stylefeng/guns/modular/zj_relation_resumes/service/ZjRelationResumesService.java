package cn.stylefeng.guns.modular.zj_relation_resumes.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_relation_resumes.entity.ZjRelationResumes;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.params.ZjRelationResumesParam;
import cn.stylefeng.guns.modular.zj_relation_resumes.model.result.ZjRelationResumesResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 个人简历信息和用户信息关联表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-21
 */
public interface ZjRelationResumesService extends IService<ZjRelationResumes> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void add(ZjRelationResumesParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void delete(ZjRelationResumesParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-21
     */
    void update(ZjRelationResumesParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
    ZjRelationResumesResult findBySpec(ZjRelationResumesParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
    List<ZjRelationResumesResult> findListBySpec(ZjRelationResumesParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-21
     */
     LayuiPageInfo findPageBySpec(ZjRelationResumesParam param);

}
