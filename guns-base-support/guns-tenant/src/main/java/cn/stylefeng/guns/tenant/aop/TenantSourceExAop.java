package cn.stylefeng.guns.tenant.aop;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.tenant.context.DataBaseNameHolder;
import cn.stylefeng.guns.base.tenant.context.TenantCodeHolder;
import cn.stylefeng.roses.core.mutidatasource.DataSourceContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 租户的多数据源切换的aop
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午10:22:16
 */
@Aspect
@Component
public class TenantSourceExAop implements Ordered {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截控制器层
     */
    @Pointcut("execution(* *..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            //根据系统总开关来进行aop，获取当前用户登录的租户标识
            if (ConstantsContext.getTenantOpen()) {
                if (LoginContextHolder.getContext().hasLogin()) {
                    String dataSourceName = LoginContextHolder.getContext().getUser().getTenantDataSourceName();
                    if (ToolUtil.isNotEmpty(dataSourceName)) {
                        DataSourceContextHolder.setDataSourceType(dataSourceName);
                        log.debug("多租户AOP--TenantSourceExAop--设置数据源为：" + dataSourceName);
                    }
                }
            }
            return point.proceed();
        } finally {
            log.debug("多租户AOP--TenantSourceExAop--清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
            TenantCodeHolder.remove();
            DataBaseNameHolder.remove();
        }
    }

    /**
     * aop的顺序要早于多数据源切换的
     */
    @Override
    public int getOrder() {
        return -100;
    }

}
