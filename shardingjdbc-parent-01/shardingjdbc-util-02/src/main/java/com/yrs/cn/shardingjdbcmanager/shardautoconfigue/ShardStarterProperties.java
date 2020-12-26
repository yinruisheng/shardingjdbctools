package com.yrs.cn.shardingjdbcmanager.shardautoconfigue;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname ShardStarterProperties
 * @Description TODO
 * @Date 2020/12/1 15:47
 * @Created by yinruisheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShardStarterProperties extends ShardBaseDatabaseConfig {

    public static final String PREFIX = "sharding.starter.initconfig";
    /**
     * 是否开启
     */
    private Boolean enable;

    public static String getPREFIX() {
        return PREFIX;
    }





}
