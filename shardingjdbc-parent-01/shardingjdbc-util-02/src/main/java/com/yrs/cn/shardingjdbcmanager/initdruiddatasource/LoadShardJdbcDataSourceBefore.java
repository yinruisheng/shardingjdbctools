package com.yrs.cn.shardingjdbcmanager.initdruiddatasource;

import com.yrs.cn.shardingjdbcmanager.entity.ShardDatabase;
import com.yrs.cn.shardingjdbcmanager.initDatasourceInfo.DataSourceUtil;
import com.yrs.cn.shardingjdbcmanager.initDatasourceInfo.ResultForLoadShardJdbcDataSourceBefore;
import com.yrs.cn.shardingjdbcmanager.shardautoconfigue.ShardStarterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类名称：  LoadShardJdbcDataSource   
 * 类描述：  加载分库分表之前获取数据源 
 * 创建人：  yrs
 * 创建时间：2019年6月27日 下午2:54:06
 * @version V1.0
 */
//参考: https://blog.csdn.net/jinhaijing/article/details/83994083
//参考：https://blog.csdn.net/qq_42684642/article/details/109297119
//参考: https://blog.csdn.net/u010634066/article/details/108325717

@Component
@Slf4j
public class LoadShardJdbcDataSourceBefore implements BeanDefinitionRegistryPostProcessor,EnvironmentAware {
	private Environment environment;

	public static List<ShardDatabase> initListDatabase = null;
	public static ShardStarterProperties shardStarterProperties = null;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 在postProcessBeanDefinitionRegistry() 之后执行
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.info("开始执行BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry方法");
		// 连接默认数据库，并初始化数据源
		ResultForLoadShardJdbcDataSourceBefore resultForLoadShardJdbcDataSourceBefore = DataSourceUtil.getDefaultDataBaseConn(environment);
		initListDatabase = resultForLoadShardJdbcDataSourceBefore.getInitListDatabase();
		shardStarterProperties = resultForLoadShardJdbcDataSourceBefore.getShardStarterProperties();
	}

	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}
}
