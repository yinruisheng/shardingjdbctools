package com.yrs.cn.shardingjdbcmanager.algorithm;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.Range;
import com.yrs.cn.shardingjdbcmanager.initdruiddatasource.LoadShardJdbcDataSourceBefore;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 类名称：  TableShardingAlg   
 * 类描述：  数据库表 分表算法
 * 创建人：  yrs
 * 创建时间：2019年6月20日 下午3:35:52
 * @version V1.0
 */

@Component
@Slf4j
public class TableShardingAlg implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
    public TableShardingAlg() {
        log.info("初始化 -> [{}]", "TableShardingAlg ----- 根据年月份范围分片算法-启用");
    }
    /**
     * 按月分表
     */
    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {
        log.info("databaseNames:{}", JSONUtils.toJSONString(databaseNames));
        Instant timestamp = Instant.ofEpochMilli(shardingValue.getValue());
        ZonedDateTime losAngelesTime = timestamp.atZone(ZoneId.of("Asia/Shanghai"));
        String yearAndMonth = "" + losAngelesTime.getYear() + (losAngelesTime.getMonthValue() < 10 ? "0" +
                losAngelesTime.getMonthValue() : "" + losAngelesTime.getMonthValue());
        for (String databaseName : databaseNames) {
            if (databaseName.endsWith(yearAndMonth)){
                return databaseName;
            }
        }
        log.error("The table name obtained by sharding is not in the specified list by the tableNames ");
        throw new UnsupportedOperationException("not table match");
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        log.info("collection:{},LogicTableName:{},ColumnValue:{}", JSONUtils.toJSONString(collection), rangeShardingValue.getLogicTableName(), rangeShardingValue.getValueRange().toString());
        Integer lowerYearAndMonth = null;
        Integer upperYearAndMonth = null;
        Collection<String> actualTables =  new LinkedHashSet<>(collection.size());
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        // 判断是否存在下界
        if (valueRange.hasLowerBound()) {
            Long lowerEndpoint = valueRange.lowerEndpoint();
            Instant lowerTimestamp = Instant.ofEpochMilli(lowerEndpoint);
            ZonedDateTime lowerLosAngelesTime = lowerTimestamp.atZone(ZoneId.of("Asia/Shanghai"));
            lowerYearAndMonth = Integer.valueOf(""+lowerLosAngelesTime.getYear()+(lowerLosAngelesTime.getMonthValue()<10 ? "0"+
                    lowerLosAngelesTime.getMonthValue(): ""+lowerLosAngelesTime.getMonthValue()));
        }
        // 判断是否存在上界
        if (valueRange.hasUpperBound()) {
            Long upperEndpoint = valueRange.upperEndpoint();
            Instant upperTimestamp = Instant.ofEpochMilli(upperEndpoint);
            ZonedDateTime upperLosAngelesTime = upperTimestamp.atZone(ZoneId.of("Asia/Shanghai"));
            upperYearAndMonth = Integer.valueOf(""+upperLosAngelesTime.getYear()+(upperLosAngelesTime.getMonthValue()<10 ? "0"+
                    upperLosAngelesTime.getMonthValue(): ""+upperLosAngelesTime.getMonthValue()));
        }
        for (String currentTableName : collection) {
            String[] strArr = currentTableName.split("_");
            Integer yearAndMonth = Integer.parseInt(strArr[1]);
            if (upperYearAndMonth == null) {
                if (yearAndMonth >= lowerYearAndMonth) {
                    actualTables.add(currentTableName);
                }
            } else if (lowerYearAndMonth == null) {
                if (yearAndMonth <= upperYearAndMonth) {
                    actualTables.add(currentTableName);
                }
            } else {
                if (yearAndMonth >= lowerYearAndMonth && yearAndMonth <= upperYearAndMonth) {
                    actualTables.add(currentTableName);
                }
            }
        }
        log.info("actualTables:{}",actualTables);
        if (actualTables.isEmpty()){
            log.error("The table name obtained by sharding is not in the specified list by the tableNames ");
            throw new UnsupportedOperationException("not table match");
        }
        return actualTables;
    }
}