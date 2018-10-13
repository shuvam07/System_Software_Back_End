import java.sql.*;

public class JDBCConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String url = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
		String uname = "admin";
		String pass ="admin";
		String query = "select * from student_info";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		for (int i = 1; i <= columnCount; i++ )  {
			String col_name = rsmd.getColumnName(i);
			System.out.print(col_name+" ");
		}
		
		System.out.println();
		while(rs.next()) {
			for (int i = 1; i <= columnCount; i++ ) {
				String name = rs.getString(i);
				System.out.print(name+" ");
			}
			System.out.println();
		}
		
		
		
		st.close();
		con.close();
	}

}
