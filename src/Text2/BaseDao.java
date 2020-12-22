package Text2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {
	public static Connection con;
	public static PreparedStatement pst;
	public static ResultSet rs;
	
	  //数据库连接
	 
	public static Connection connectionDataBase(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	 //关闭对象
	 
	public static void closeDataBase(){
		try {
			if (rs!=null) {
				rs.close();
			}
			if (pst!=null) {
				pst.close();
			}
			if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
