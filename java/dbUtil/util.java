package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class util {
	//1.DB연결
		public static Connection getConnection() {
			Connection conn = null;
			Context initContext;
			try {
				initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
				conn = ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		//2.자원반납
		public static void dbDisconnect(ResultSet rs, Statement st, Connection conn) {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
