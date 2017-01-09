package com.zs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;


public class DBUtils {
	
	/*
	public static Connection openDB() throws SQLException, ClassNotFoundException{
		Connection con=null;
		 *2016年7月7日17:16:11
		 *张顺
		 *mysql 
		String url="jdbc:mysql://localhost:3306/luck?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		con = DriverManager.getConnection(url);
		
		return con;
	}
	 * */
	
	public static Connection openDBFromProxool() throws SQLException, ClassNotFoundException{
		//1：注册驱动类，这次这个驱动已经不是Oracle的驱动了，是Proxool专用的驱动 
		Class.forName("org.logicalcobwebs.proxool.ProxoolDriver"); 
		//2：创建数据库连接，这个参数是一个字符串，是数据源的别名，在配置文件中配置的timalias，参数格式为：proxool.数据源的别名 
		Connection conn = DriverManager.getConnection("proxool.DBPool_LUCK"); 
		return conn;
	}
}