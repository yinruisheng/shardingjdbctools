package com.yrs.cn.shardingjdbcmanager.algorithm;

import com.alibaba.druid.support.json.JSONUtils;
import com.yrs.cn.shardingjdbcmanager.initdruiddatasource.LoadShardJdbcDataSourceBefore;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 类名称：  DatabaseShardingAlg   
 * 类描述：  数据库 分库算法
 * 创建时间：2019年6月20日 下午1:43:27
 * @version V1.0
 * @author yrs
 */
@Component
@Slf4j
public class DatabaseShardingAlg implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
	public DatabaseShardingAlg() {
		log.info("初始化 -> [{}]", "DatabaseShardingAlg ----- 根据年月份范围分片算法-启用");
	}
	// databaseNames就是所谓的实际数据库表节点,而shardingValue其实就是分片项，也就是比如user_id,developer_id等字段值
	@Override
	public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {
		log.info("databaseNames:{},LogicTableName:{},ColumnValue:{}", JSONUtils.toJSONString(databaseNames), shardingValue.getLogicTableName(), shardingValue.getValue());
		Instant timestamp = Instant.ofEpochMilli(shardingValue.getValue());
		ZonedDateTime losAngelesTime = timestamp.atZone(ZoneId.of("Asia/Shanghai"));
		int year = losAngelesTime.toLocalDate().getYear();
		for (String databaseName : databaseNames) {
			if (databaseName.endsWith(String.valueOf(year))){
				return databaseName;
			}
		}
		log.error("The database name obtained by sharding is not in the specified list by the databaseNames ");
		throw new UnsupportedOperationException("not database match");
	}

	@Override
	public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
		log.info("collection:{},LogicTableName:{},ColumnValue:{}", JSONUtils.toJSONString(collection), rangeShardingValue.getLogicTableName(), rangeShardingValue.getValueRange().toString());
		Collection<String> actualDbs = new LinkedHashSet<>(collection.size());
		Long lowerEndpoint = rangeShardingValue.getValueRange().lowerEndpoint();
		Long upperEndpoint = rangeShardingValue.getValueRange().upperEndpoint();
		Instant lowerTimestamp = Instant.ofEpochMilli(lowerEndpoint);
		Instant upperTimestamp = Instant.ofEpochMilli(upperEndpoint);
		ZonedDateTime lowerLosAngelesTime = lowerTimestamp.atZone(ZoneId.of("Asia/Shanghai"));
		ZonedDateTime upperLosAngelesTime = upperTimestamp.atZone(ZoneId.of("Asia/Shanghai"));
		int lowYear = lowerLosAngelesTime.toLocalDate().getYear();
		int upperYear = upperLosAngelesTime.toLocalDate().getYear();
		for (int i=lowYear;i<=upperYear;i++){
			for (String dbName : collection) {
				if (dbName.endsWith(String.valueOf(i))){
					actualDbs.add(dbName);
				}
			}
		}
		log.info("actualDbs:{}",actualDbs);
		if (actualDbs.isEmpty()){
			log.error("The database name obtained by sharding is not in the specified list by the databaseNames ");
			throw new UnsupportedOperationException("not database match");
		}
		return actualDbs;
	}

}
