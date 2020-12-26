package com.yrs.cn.shardingjdbcmanager.initDatasourceInfo;

import com.mysql.cj.jdbc.Driver;
import com.yrs.cn.shardingjdbcmanager.entity.ShardDatabase;
import com.yrs.cn.shardingjdbcmanager.shardautoconfigue.ShardStarterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：  DataSourceUtil   
 * 类描述：  数据源工具类 
 * 创建人：  yrs
 * 创建时间：2019年6月24日 下午2:58:53
 * @version V1.0
 */
@Configuration
@Slf4j
public class DataSourceUtil {

	/**
	 * @Title:  createDatabase   
	 * @Description:   新建数据库
	 * @param:  @param dataBaseName      
	 * @return: void      
	 * @throws
	 */
	/**
	 * 默认数据库连接
	 */
	public static String DRIVERMANAGER_PARAM_P ="?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8";
	public static String DRIVERMANAGER = "jdbc:mysql://10.45.64.135:3306/";
	public static String USERNAME      = "root";
	public static String PASSWORD      = "123456";

	public static void createDatabase(String dataBaseName) {
		Connection conn = null;
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String databaseSql = "CREATE DATABASE IF NOT EXISTS " + dataBaseName + " DEFAULT CHARSET utf8 COLLATE utf8_general_ci";
			conn = DriverManager.getConnection(DRIVERMANAGER, USERNAME, PASSWORD);
			if (conn != null) {
				Statement stm = conn.createStatement();
				int i = stm.executeUpdate(databaseSql);
				if (i == 1) {
					System.out.println(databaseSql + "新建数据库成功！");
				}
			}
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @Title:  createTable   
	 * @Description:   新建表
	 * @param:  @param databaseName
	 * @param:  @param tableName      
	 * @return: void      
	 * @throws
	 */
	public static void createTable(ShardDatabase shardDatabase, String tableName){
		String tableSQL = "CREATE TABLE "+tableName+
		        		  " (\n" +
		                    "  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',\n" +
		                    "  developer_id varchar(64) DEFAULT NULL COMMENT '开发者标识',\n" +
		                    "  eqp_num varchar(20) NOT NULL COMMENT '设备编号',\n" +
		                    "  file_name varchar(20) NOT NULL COMMENT '文件名称',\n" +
		                    "  file_size int DEFAULT NULL COMMENT '文件大小',\n" +
		                    "  file_type varchar(50) DEFAULT NULL COMMENT '文件类型',\n" +
		                    "  file_path varchar(50) DEFAULT NULL COMMENT '文件路径',\n" +
		                    "  s3_file_path varchar(50) DEFAULT NULL COMMENT 'S3文件路径',\n" +
		                    "  year int(11) DEFAULT NULL COMMENT '年',\n" +
		                    "  month int(11) DEFAULT NULL COMMENT '月',\n" +
		                    "  day int(11) DEFAULT NULL COMMENT '日',\n" +
		                    "  status int(11) DEFAULT NULL COMMENT '状态：0.失效，1.启用',\n" +
		                    "  create_time  datetime(3) DEFAULT NULL COMMENT '创建时间',\n" +
		                    "  update_time  datetime(3) DEFAULT NULL COMMENT '更新时间',\n" +
		                    "  device_data_id varchar(64) DEFAULT NULL COMMENT '业务编号',\n" +
		                    "  file_detail varchar(1024) DEFAULT NULL COMMENT '业务详情',\n" +
		                    "  account_id int DEFAULT NULL COMMENT '开发者编号',\n" +
		                    "  PRIMARY KEY (id)\n" +
		                 " ) ENGINE=InnoDB DEFAULT CHARSET=utf8;" ;
		try {
			String url = "jdbc:mysql://"+shardDatabase.getIp()+":"+shardDatabase.getPort()+"/" + shardDatabase.getDatabaseName() + DRIVERMANAGER_PARAM_P;
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection(url, shardDatabase.getUsername(), shardDatabase.getPassword());
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(tableSQL);
			conn.close();
		} catch (Exception e) {
			System.out.print("createTable Mysql Connection Error:" + e.getMessage());
		}
	}

	/**
	 * @Title:  getDefaultDataBaseConn   
	 * @Description: 连接数据库，并初始化数据源  
	 * @param:  @return      
	 * @return: List<DataBase>      
	 * @throws
	 */
	public static ResultForLoadShardJdbcDataSourceBefore getDefaultDataBaseConn(Environment environment) {
		List<ShardDatabase> listDataBase = new ArrayList<>();
		//获取配置文件配置，并绑定
		BindResult<ShardStarterProperties> propertiesConfigBindResult = Binder.get(environment).bind(ShardStarterProperties.PREFIX, ShardStarterProperties.class);
		ShardStarterProperties shardStarterProperties = propertiesConfigBindResult.get();
		log.info("从environment中拉取到sharding-spring-boot-starter的基础配置[{}]",shardStarterProperties);
		try {
			Connection conn = null;
			Class.forName(Driver.class.getName()).newInstance();
			conn = DriverManager.getConnection(shardStarterProperties.getUrl(), shardStarterProperties.getUsername(), shardStarterProperties.getPassword());
			Statement stmt = conn.createStatement();
			// 查询数据库集合
			String selectSql = "select ip,port,database_name,username,password from shard_database";
			ResultSet selectRes = stmt.executeQuery(selectSql);
			while (selectRes.next()) {
				String  ip    = selectRes.getString("ip");
				Integer port    = selectRes.getInt("port");
				String  databaseName  = selectRes.getString("database_name");
				String  username = selectRes.getString("username");
				String  password = selectRes.getString("password");
				ShardDatabase db = new ShardDatabase();
				db.setIp(ip);
				db.setPort(port);
				db.setDatabaseName(databaseName);
				db.setUsername(username);
				db.setPassword(password);
				listDataBase.add(db);
			}
			conn.close();
		} catch (Exception e) {
			log.error("getDefaultDataBaseInfo Exception:" + e.getMessage());
		}
		return new ResultForLoadShardJdbcDataSourceBefore(listDataBase,shardStarterProperties);
	}

}
