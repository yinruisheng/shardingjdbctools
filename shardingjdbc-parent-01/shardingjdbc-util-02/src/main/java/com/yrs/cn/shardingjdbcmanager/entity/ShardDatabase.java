package com.yrs.cn.shardingjdbcmanager.entity;

import lombok.Data;

/**
 * 类名称：  ShardDatabase   
 * 类描述：  数据源实体类信息
 * 创建时间：2019年7月11日 下午2:16:30
 * @version V1.0
 * @author yrs
 */
@Data
public class ShardDatabase {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 数据库IP
	 */
	private String  ip;
	/**
	 * 数据库IP
	 */
	private Integer port;
	/**
	 * 数据库名称
	 */
	private String  databaseName;
	/**
	 * 用户名
	 */
	private String  username;
	/**
	 * 密码
	 */
	private String  password;
}
