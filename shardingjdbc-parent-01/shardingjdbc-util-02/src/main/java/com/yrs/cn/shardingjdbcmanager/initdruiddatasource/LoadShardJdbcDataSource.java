package com.yrs.cn.shardingjdbcmanager.initdruiddatasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.yrs.cn.shardingjdbcmanager.algorithm.DatabaseShardingAlg;
import com.yrs.cn.shardingjdbcmanager.algorithm.TableShardingAlg;
import com.yrs.cn.shardingjdbcmanager.entity.ShardDatabase;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Classname LoadShardJdbcDataSource
 * @Description TODO
 * @Date 2020/12/23 19:51
 * @Created by yinruisheng
 */
@Slf4j
@Component
public class LoadShardJdbcDataSource implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    /**
     * 用来自定义加载持有的数据源Bean
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 数据库映射
        Map<String, DataSource> dataMap = new LinkedHashMap<String, DataSource>();
        log.info("开始执行BeanFactoryPostProcessor的postProcessBeanFactory方法");
        if(LoadShardJdbcDataSourceBefore.initListDatabase != null && LoadShardJdbcDataSourceBefore.initListDatabase.size() > 0){
            for(int i=0;i<LoadShardJdbcDataSourceBefore.initListDatabase.size();i++){
                ShardDatabase db = LoadShardJdbcDataSourceBefore.initListDatabase.get(i);
                // 获取BeanFactory
                DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();

                String urlSuffix = LoadShardJdbcDataSourceBefore.shardStarterProperties.getUrl().substring(LoadShardJdbcDataSourceBefore.shardStarterProperties.getUrl().indexOf("?"));
                String url = String.format("jdbc:mysql://"+db.getIp()+":"+db.getPort()+"/%s", db.getDatabaseName())+ urlSuffix;
                // 创建连接池Bean
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
                beanDefinitionBuilder.addPropertyValue("driverClassName", com.mysql.cj.jdbc.Driver.class.getName());
                beanDefinitionBuilder.addPropertyValue("url",url);
                beanDefinitionBuilder.addPropertyValue("username",db.getUsername());
                beanDefinitionBuilder.addPropertyValue("password",db.getPassword());

                // BeanFactory注册Bean
                defaultListableBeanFactory.registerBeanDefinition(db.getDatabaseName(), beanDefinitionBuilder.getBeanDefinition());

                // Map中存放数据源映射
                dataMap.put(db.getDatabaseName(), (DataSource)ctx.getBean(db.getDatabaseName()));
            }
        }
        try {
            this.getShardDataBaseRule(dataMap);
            log.info("datasource生成完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Title:  getShardDataBaseRule
     * @Description:    Shard-JDBC 文件分库配置
     * @param:  @param  dataMap
     * @param:  @return
     * @param:  @throws Exception
     * @return: DataSource
     * @throws
     */
    @Primary
    @Bean
    //  https://www.cnblogs.com/baixianlong/p/12644027.html  参考此文档
    public DataSource getShardDataBaseRule(Map<String, DataSource> dataMap) throws Exception {
        //1、指定需要分库分表的数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("ds0", dataSourceOne);
//        dataSourceMap.put("ds1",dataSourceTwo);
        ShardingRuleConfiguration shardJdbcConfig = new ShardingRuleConfiguration();
        //2.1 配置各个表的分库分表策略，这里只配了一张表的就是t_order
        shardJdbcConfig.getTableRuleConfigs().add(getShardTableRule());
        //2.2 配置绑定表规则列表,级联绑定表代表一组表，这组表的逻辑表与实际表之间的映射关系是相同的
        //shardingRuleConfig.getBindingTableGroups().add("t_order","t_order_item");
        //2.3 配置广播表规则列表,利用广播小表提高性能
        //shardingRuleConfig.getBroadcastTables().add("t_config");
        //2.4 配置默认分表规则
        shardJdbcConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        //2.5 配置默认分库规则(不配置分库规则,则只采用分表规则)
        shardJdbcConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        //2.6 配置默认数据源,一般配置基础库
        shardJdbcConfig.setDefaultDataSourceName(LoadShardJdbcDataSourceBefore.shardStarterProperties.getDefaultDataSourceName());
        //2.7 配置读写分离规则
        //shardJdbcConfig.setMasterSlaveRuleConfigs();
        //3、属性配置项，可以为以下属性
        Properties propertie = new Properties();
        //是否打印SQL解析和改写日志
        if (LoadShardJdbcDataSourceBefore.shardStarterProperties.getSqlShowEnable()){
            propertie.setProperty("sql.show",Boolean.TRUE.toString());
        }
        //用于SQL执行的工作线程数量，为零则表示无限制
//        propertie.setProperty("executor.size","4");
        //每个物理数据库为每次查询分配的最大连接数量
//        propertie.setProperty("max.connections.size.per.query","1");
        //是否在启动时检查分表元数据一致性
//        propertie.setProperty("check.table.metadata.enabled","false");
        //4、用户自定义属性
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put("effect","分库分表");
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardJdbcConfig,configMap,propertie);
        //5、数据治理
        //5.1、配置注册中心
//        RegistryCenterConfiguration regConfig = new RegistryCenterConfiguration();
//        regConfig.setServerLists("localhost:2181");
//        regConfig.setNamespace("sharding-sphere-orchestration");
        //regConfig.setDigest("data-centre");
        //5.2、配置数据治理
//        OrchestrationConfiguration orchConfig = new OrchestrationConfiguration("orchestration-sharding-data-source", regConfig, true);
        //5.3、获取数据源对象
//        DataSource dataSource = OrchestrationShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, configMap, propertie, orchConfig);
        log.info("根据自定义配置开始生成datasource");
        return ShardingDataSourceFactory.createDataSource(dataMap,shardJdbcConfig,propertie);
    }

    /**
     * @Title:  getShardTableRule
     * @Description:    Shard-JDBC 文件分表配置
     * @param:  @return
     * @return: TableRuleConfiguration
     * @throws
     */
    private static TableRuleConfiguration getShardTableRule() {
        // 配置Alarm表规则
        TableRuleConfiguration result = new TableRuleConfiguration(LoadShardJdbcDataSourceBefore.shardStarterProperties.getLogicTable(),LoadShardJdbcDataSourceBefore.shardStarterProperties.getActualDataNodes());
        // 配置分库策略
//        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(LoadShardJdbcDataSourceBefore.shardStarterProperties.getDbShardColumn(), new DatabaseShardingAlg()));
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(LoadShardJdbcDataSourceBefore.shardStarterProperties.getDbShardColumn(), new DatabaseShardingAlg(),new DatabaseShardingAlg()));
        // 配置分表策略
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(LoadShardJdbcDataSourceBefore.shardStarterProperties.getTableShardColumn(), new TableShardingAlg(),new TableShardingAlg()));
        //指定自增字段以及key的生成方式
//        result.setKeyGeneratorColumnName("order_id");
//        result.setKeyGenerator(new DefaultKeyGenerator());
        return result;
    }

}
