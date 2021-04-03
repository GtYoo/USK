package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//version 1.0.1. (202103/13 update)
/**
 * 
 * @author Lim
 *
 * 2021-03-13
 *	DBController에서 connect(), close() 제외 잡다한 코드 제거
 *	pstmt.executeUpdate(); 처리 부분을 
 *		select(), insert(), update(), delete()로 명시적으로 설정
 *
 */

//	---usuage---
//	DBController.connect();
//	String sql = "쿼리문 작성";
//	DBController.setPstmt(sql);
//	DBController.getPstmt().setString(1, reviewDTO.getAttractionNo());
//	...등등
//
//	ResultSet rs = DBController.getRs();
//  또는
//	ResultSet rs = DBController.select();

//	int rs = DBController.insert();
//	int rs = DBController.update();
//	int rs = DBController.delete();

//	DBController.close();

public class DBController {

	private static final String driver 		= "oracle.jdbc.driver.OracleDriver";
	private static final String url 		= "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user 		= "dev";
	private static final String password 	= "123456";
	
	private static Connection con = null;
	private static String sql;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	public DBController(){
		;
	}
	
	// setter & getter
	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		DBController.con = con;
	}

	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		DBController.sql = sql;
	}

	public static PreparedStatement getPstmt() {
		return pstmt;
	}

	
	public static void setPstmt(String sql) throws SQLException{
		pstmt = con.prepareStatement(sql);
	}

	public static ResultSet getRs() throws SQLException{
		rs = pstmt.executeQuery();
		return rs;
	}

	public static void setRs(ResultSet rs) {
		DBController.rs = rs;
	}
	
	
	// CRUD 함수들
	public static ResultSet select() throws SQLException {
		return getRs();
	}
	
	public static int insert() throws SQLException {
		return execute();
	}
	
	public static int update() throws SQLException {
		return execute();
	}
	
	public static int delete() throws SQLException {
		return execute();
	}
	
	private static int execute() throws SQLException {
		return pstmt.executeUpdate();
	}
	
	
	
	

	// DB 연결
	public static void connect() throws BizException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
			con.setAutoCommit(true);
		}
		catch (ClassNotFoundException e) {
		}
		catch (SQLException e) {
		}
	}
	
	// DB 연결 종료
	public static void close() throws BizException {
		try {
			if (rs != null) 	rs.close();
			if (pstmt != null) 	pstmt.close();
			if (con != null) 	con.close();
		}
		catch (SQLException e) {
		}
	}
	
	
}
