package com.ecarinfo.traffic.persist.gen;

import com.ecarinfo.db4j.generator.DBUtil;
import com.ecarinfo.db4j.generator.Db4jGenerator;

/**
 * Hello world!
 * 
 */
public class AutoGenCode {
	public static void main(String[] args) {
		
		//数据库配置
		DBUtil.DIREVER = "com.mysql.jdbc.Driver";
		DBUtil.URL = "jdbc:mysql://192.168.1.241:3306/ec_traffic_engine?characterEncoding=UTF-8&rewriteBatchedStatements=true";
		DBUtil.USERNAME = "developer";
		DBUtil.PASSWORD = "123456";
//		DBUtil.URL = "jdbc:mysql://localhost:3306/ec_traffic_engine?characterEncoding=UTF-8&rewriteBatchedStatements=true";
//		DBUtil.USERNAME = "root";
//		DBUtil.PASSWORD = "root";
		
		//表别名相关
//		MetaUtil.PREFIX = "core,C:dict,C";//前缀替换，将core_替换成C,dict_替换成C,多个替换间使用冒号分隔
		
		//生成表的包名配置
		String domain = "com.ecarinfo.traffic.persist";
		//生成表，参数2为输出目录，填null则输出在本项目的src/main/java目录下
		Db4jGenerator.execute(domain,null);
		
	}
}
