package step6.dao;

import java.sql.*;

public class ConnectionManager {
	/**
	 * jdbc연결을 관리
	 * 드라이버 로딩해서 Connection 객체 생성하고 닫는데 필요한 유틸리티 클래스 
	 * Databse클래스에서 메소드를 통해 사용하여 코드가 간결해진다. 
	 */
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "hr";
	private static String password = "hr";
	
	private static ConnectionManager cm = new ConnectionManager(); 
	private ConnectionManager(){};//객체생성막음
	
	static {
		try {
			Class.forName(driver);//드라이버라이브러리 로딩 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static ConnectionManager getInstance(){
		return cm; //객체에 접근이 필요할 때 
	}
	
	public static Connection getConnection(){
		Connection con = null;//Connection 할 때 사용
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	public static void close(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
