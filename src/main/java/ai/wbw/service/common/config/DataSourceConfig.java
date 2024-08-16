package ai.wbw.service.common.config;

import ai.wbw.service.common.bean.MyRoutingDataSource;
import ai.wbw.service.common.enums.DBTypeEnum;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置类 关于数据源配置，参考SpringBoot官方文档第79章《Data Access》 79. Data Access 79.1 Configure a Custom
 * DataSource 79.2 Configure Two DataSources
 */
@Configuration
public class DataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.slave1")
  public DataSource slave1DataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public DataSource myRoutingDataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slave1DataSource") DataSource slave1DataSource) {
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
    targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
    MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
    // 默认主数据库
    myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
    myRoutingDataSource.setTargetDataSources(targetDataSources);
    return myRoutingDataSource;
  }
}
