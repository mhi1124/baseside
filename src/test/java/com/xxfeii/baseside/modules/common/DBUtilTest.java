package com.xxfeii.baseside.modules.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.xxfeii.baseside.common.utils.DBUtil;


public class DBUtilTest {

	@Test
	public void getConnectionTest(){
		Connection conn = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/foo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		System.out.println(conn);
	}
	
	@Test
	public void executeQueryTest() throws SQLException{
		Connection conn = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/foo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		String sql = "select * from tab_contact where col_adId != 0 limit 0,100";
		ResultSet rs = DBUtil.executeQuery(conn, sql);
		List<Map<String, Object>> data = DBUtil.resultSetToList(rs);
		System.out.println(data.size());
		DBUtil.closeConnection(conn);
	}
	
	@Test
	public void executeSQLTest(){
		Connection conn = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/foo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		String sql = "insert into au_rule(auname,lowestsys) values('test','test')";
		DBUtil.executeSQL(conn, sql);
		DBUtil.closeConnection(conn);
	}
	
	@Test
	public void executeBatchSQLTest(){
		Connection conn = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/foo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		String sql1 = "insert into au_rule(auname,lowestsys) values('test1','test')";
		String sql2 = "insert into au_rule(auname,lowestsys) values('test2','test')";
		String sql3 = "insert into au_rule(auname,lowestsys) values('test3','test')";
		String sql4 = "insert into au_rule(auname,lowestsys) values('test4','test')";
		String sql5 = "insert into au_rule(auname,lowestsys) values('test5','test')";
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(sql1);
		sqlList.add(sql2);
		sqlList.add(sql3);
		sqlList.add(sql4);
		sqlList.add(sql5);
		DBUtil.executeBatchSQL(conn, sqlList);
		DBUtil.closeConnection(conn);
	}
	
	@Test
	public void closeConnection(){
		Connection conn = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/foo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		DBUtil.closeConnection(conn);
		Connection conn1 = DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
		DBUtil.closeConnection(conn1);
	}
}
