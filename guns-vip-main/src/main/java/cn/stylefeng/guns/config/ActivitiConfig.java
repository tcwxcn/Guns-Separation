package cn.stylefeng.guns.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl.DATABASE_TYPE_MYSQL;

/**
 * 工作流配置
 *
 * @author fengshuonan
 * @Date 2019/8/5 22:07
 */
@Configuration
public class ActivitiConfig {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SpringProcessEngineConfiguration getProcessEngineConfiguration() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        config.setDataSource(dataSource);
        config.setDbIdentityUsed(false);
        config.setTransactionManager(transactionManager);
        config.setActivityFontName("宋体");
        config.setLabelFontName("宋体");
        config.setAnnotationFontName("宋体");

        //使用别的数据库注意修改
        config.setDatabaseType(DATABASE_TYPE_MYSQL);

        //初始化工作流数据库用
        //config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        return config;
    }

}
