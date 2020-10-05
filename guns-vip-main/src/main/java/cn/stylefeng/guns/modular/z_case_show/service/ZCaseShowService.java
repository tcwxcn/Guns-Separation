package cn.stylefeng.guns.modular.z_case_show.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.z_case_show.entity.ZCaseShow;
import cn.stylefeng.guns.modular.z_case_show.model.params.ZCaseShowParam;
import cn.stylefeng.guns.modular.z_case_show.model.result.ZCaseShowResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 案例展示表 服务类
 * </p>
 *
 * @author Hua
 * @since 2020-09-29
 */
public interface ZCaseShowService extends IService<ZCaseShow> {

    /**
     * 新增
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void add(ZCaseShowParam param);

    /**
     * 删除
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void delete(ZCaseShowParam param);

    /**
     * 更新
     *
     * @author Hua
     * @Date 2020-09-29
     */
    void update(ZCaseShowParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
    ZCaseShowResult findBySpec(ZCaseShowParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
    List<ZCaseShowResult> findListBySpec(ZCaseShowParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Hua
     * @Date 2020-09-29
     */
     LayuiPageInfo findPageBySpec(ZCaseShowParam param);

    /**
     * 查看案例展示图片
     * @return
     */
    List<byte[]> viewPics();

    /**
     * 获取案例展示图片所以地址和标题
     * @return
     */
    List<Map<String,String>> findAllPicturePath();

}
