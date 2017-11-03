package cn.may.datasource;

import cn.may.config.DataSources;
import cn.may.config.DynamicRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaojianqun on 2017/11/1.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("datasource.db_a")
    @Qualifier("dataSourceDefault")
    public DataSource dataSourceDefault(){
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties("datasource.db_b")
    @Qualifier("dataSourceB")
    public DataSource dataSourceB(){
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties("dataSource.db_c")
    @Qualifier("dataSourceC")
    public DataSource dataSourceC(){
        return new DruidDataSource();
    }

    /**
     * 搭建数据源的访问路由
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(){
        //设置数据源访问路由
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        //设置一个默认的数据源,避免其他数据数据源出现问题的时候依然能调用该数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceDefault());
        Map<Object,Object> map = new HashMap<Object,Object>();
        //设置数据源访问路由map
        map.put(DataSources.DATASOURCE_A,dataSourceDefault());
        map.put(DataSources.DATASOURCE_B,dataSourceB());
        map.put(DataSources.DATASOURCE_C,dataSourceC());
        dynamicRoutingDataSource.setTargetDataSources(map);

        return dynamicRoutingDataSource;
    }

    /**
     * 搭建SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multipleDataSource());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.may.model");
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("cofiguration.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 开启springboot事务管理，同时返回事务调用实例
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSourceDefault());
    }
}
