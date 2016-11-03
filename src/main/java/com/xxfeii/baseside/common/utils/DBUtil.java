package com.xxfeii.baseside.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jdbc 工具类
 * 
 * @author 80002196
 *
 */
public class DBUtil {
	private static final Logger log = LoggerFactory.getLogger(DBUtil.class);

	/**
	 * 创建一个数据库连接
	 * 
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 */
	public static Connection getConnection(String url, String user, String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			log.error("#ERROR# :加载数据库驱动异常，请检查！", e);
		} catch (SQLException e) {
			log.error("#ERROR# :创建数据库连接发生异常，请检查！", e);
		}
		return conn;
	}

	/**
	 * 在一个数据库连接上执行一个静态SQL语句查询
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static ResultSet executeQuery(Connection conn, String sql) {
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			log.error("#ERROR# :执行SQL语句出错，请检查！\n" + sql, e);
		}
		return rs;
	}

	/**
	 * 在一个数据库连接上执行一个静态SQL语句
	 * 
	 * @param conn
	 * @param sql
	 */
	public static void executeSQL(Connection conn, String sql) {
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			log.error("#ERROR# :执行SQL语句出错，请检查！\n" + sql, e);
		}
	}

	/**
	 * 批量执行sql
	 * 
	 * @param conn
	 * @param sqlList
	 */
	public static void executeBatchSQL(Connection conn, List<String> sqlList) {
		try {
			Statement st = conn.createStatement();
			for (String sql : sqlList) {
				st.addBatch(sql);
			}
			st.executeBatch();
		} catch (SQLException e) {
			log.error("#ERROR# :执行批量SQL语句出错，请检查！", e);
		}
	}

	/**
	 * ResultSet转为List
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Map<String, Object>> resultSetToList(ResultSet rs) {
		if (rs == null) {
			return Collections.emptyList();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
			while (rs.next()) {
				rowData = new HashMap<String, Object>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			log.error("#ERROR# :获取数据失败！", e);
		}
		return list;
	}

	/**
	 * 预处理批量执行sql
	 * 
	 * @param conn
	 * @param tabeName
	 * @param rs
	 */
	public static void executeBatchSQL(Connection conn, String tabeName, ResultSet rs) {
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
			StringBuffer newsql = new StringBuffer();
			newsql.append("insert into " + tabeName);
			StringBuffer k = new StringBuffer();
			StringBuffer v = new StringBuffer();
			k.append("(");
			v.append('(');
			for (int i = 1; i <= columnCount; i++) {
				k.append(md.getColumnName(i)).append(',');
				v.append("?,");
			}
			k.deleteCharAt(k.length() - 1);
			v.deleteCharAt(v.length() - 1);
			k.append(')');
			v.append(")");
			newsql.append(k).append(" values ").append(v);
			conn.setAutoCommit(false);
			PreparedStatement targetPstmt = conn.prepareStatement(newsql.toString());
			int batchCounter = 0; // 累加的批处理数量
			int pageSize = ProUtil.getIntConfig("synchronize_pageSize");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					targetPstmt.setObject(i, rs.getObject(i));
				}
				batchCounter++;
				targetPstmt.addBatch();
				if (batchCounter % pageSize == 0) { // 1万条数据一提交
					targetPstmt.executeBatch();
					targetPstmt.clearBatch();
					conn.commit();
					log.info("已经处理了" + tabeName + " : " + batchCounter + "条数据！");
				}
			}
			// 提交剩余的批处理
			targetPstmt.executeBatch();
			targetPstmt.clearBatch();
			conn.commit();
			log.info("已经处理了" + tabeName + " : " + batchCounter + "条数据！");
		} catch (SQLException e) {
			log.error("#ERROR# :获取数据失败！", e);
		}
	}

	/**
	 * 预处理批量执行sql
	 * 
	 * @param conn
	 * @param sql
	 * @param data
	 */
	public static void executeBatchSQL(Connection conn, String sql, List<Map<String, Object>> data) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int size = data.size();

			for (int i = 0; i < size; i++) {
				Map<String, Object> m = data.get(i);
				for (String key : m.keySet()) {
					ps.setObject(Integer.valueOf(key), m.get(key));
				}
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			ps.clearBatch();
		} catch (SQLException e) {
			log.error("#ERROR# :执行批量SQL语句出错，请检查！", e);
		}
	}

	/**
	 * 查询数量
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static int executeCountBysql(Connection conn, String sql) {
		int count = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			log.error("#ERROR# :执行SQL语句出错，请检查！\n" + sql, e);
		}
		return count;
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (null != conn) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error("#ERROR# :关闭数据库连接发生异常，请检查！", e);
			}
		}
	}
}
