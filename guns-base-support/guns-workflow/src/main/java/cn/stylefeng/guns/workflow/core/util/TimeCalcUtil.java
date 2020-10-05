package cn.stylefeng.guns.workflow.core.util;

/**
 * 时间计算工具类
 *
 * @author fengshuonan
 * @Date 2019/8/7 23:14
 */
public class TimeCalcUtil {

    /**
     * 根据耗时的毫秒数计算天时分秒
     *
     * @author fengshuonan
     * @Date 2019-08-28 14:41
     */
    public static String calc(Long millisSeconds) {
        long tian = millisSeconds / (1000 * 60 * 60 * 24);
        long shi = (millisSeconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long fen = (millisSeconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
        long miao = (millisSeconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) % (1000 * 60) / 1000;
        return tian + "天" + shi + "时" + fen + "分" + miao + "秒";
    }

}
