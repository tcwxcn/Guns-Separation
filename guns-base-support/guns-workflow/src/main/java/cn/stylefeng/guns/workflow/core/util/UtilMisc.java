package cn.stylefeng.guns.workflow.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * map工具类
 *
 * @author fengshuonan
 * @Date 2019-08-27 18:39
 */
public class UtilMisc {

    public static <V, V1 extends V, V2 extends V> Map<String, V> toMap(String name1, V1 value1, String name2, V2 value2) {
        return populateMap(new HashMap<String, V>(), name1, value1, name2, value2);
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Map<String, V> populateMap(Map<String, V> map, Object... data) {
        for (int i = 0; i < data.length; ) {
            map.put((String) data[i++], (V) data[i++]);
        }
        return map;
    }

}
