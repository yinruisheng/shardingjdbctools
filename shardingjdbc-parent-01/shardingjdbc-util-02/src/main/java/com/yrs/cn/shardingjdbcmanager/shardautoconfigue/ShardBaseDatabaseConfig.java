package com.yrs.cn.shardingjdbcmanager.shardautoconfigue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ShardBaseDatabaseConfig
 * @Description TODO
 * @Date 2020/12/24 16:31
 * @Created by yinruisheng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShardBaseDatabaseConfig {
    /**
     * 数据库加载数据的url
     */
    private String  url;
    /**
     * 数据库加载数据用户名
     */
    private String  username;
    /**
     * 数据库加载数据密码
     */
    private String  password;
    /**
     * shardingjdbc中配置的默认数据库名称
     */
    private String defaultDataSourceName;
    /**
     * 分表的逻辑表名称
     */
    private String logicTable;
    /**
     * 分库分表映射规则 db.table
     */
    private String actualDataNodes;
    /**
     * 数据库分片字段
     */
    private String dbShardColumn;
    /**
     * 表分片字段
     */
    private String tableShardColumn;
    /**
     * 是否打开SQL改写打印展示
     */
    private Boolean sqlShowEnable;
}
