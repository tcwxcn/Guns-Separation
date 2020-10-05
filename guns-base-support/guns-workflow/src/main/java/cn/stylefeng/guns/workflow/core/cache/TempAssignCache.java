package cn.stylefeng.guns.workflow.core.cache;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 指定下一代办人的临时缓存
 *
 * @author fengshuonan
 * @date 2019-09-05-14:18
 */
public class TempAssignCache {

    private static Map<Long, String> map = new ConcurrentHashMap<>();

    /**
     * 设置代办人
     *
     * @author fengshuonan
     * @Date 2019-09-05 14:21
     */
    public static String set(String assign) {
        Long userId = LoginContextHolder.getContext().getUserId();
        return map.put(userId, assign);
    }

    /**
     * 获取代办人
     *
     * @author fengshuonan
     * @Date 2019-09-05 14:21
     */
    public static String get() {
        Long userId = LoginContextHolder.getContext().getUserId();
        return map.get(userId);
    }

}
