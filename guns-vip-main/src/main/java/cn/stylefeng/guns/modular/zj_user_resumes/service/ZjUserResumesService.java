package cn.stylefeng.guns.modular.zj_user_resumes.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.zj_user_resumes.entity.ZjUserResumes;
import cn.stylefeng.guns.modular.zj_user_resumes.model.params.ZjUserResumesParam;
import cn.stylefeng.guns.modular.zj_user_resumes.model.result.ZjUserResumesResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 个人简历信息表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-18
 */
public interface ZjUserResumesService extends IService<ZjUserResumes> {

    /**
     * 新增用户
     *
     * @author Hua
     * @Date 2020-09-18
     */
    void add(ZjUserResumesParam param);

    /**
     * 添加用户和职位的关联表信息
     */
    void addUserPos(ZjUserResumes entity);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-18
     */
    void delete(ZjUserResumesParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-18
     */
    void update(ZjUserResumesParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-18
     */
    ZjUserResumesResult findBySpec(ZjUserResumesParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-18
     */
    List<ZjUserResumesResult> findListBySpec(ZjUserResumesParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-18
     */
     LayuiPageInfo findPageBySpec(ZjUserResumesParam param);

}
