package helpers.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Conection {
	
	private String URL = ("jdbc:postgresql://localhost:5432/Webpage");
	private String user = "postgres";
	private String pass = "Hatsunemiku";
	private Connection conn = null;
	public String[] dataInfo = null;

	
	
	public Conection() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL, user, pass);
		}
			catch(SQLException | ClassNotFoundException e) {
				System.out.println(e);
		}
	}

	public ArrayList<String> ST(String SQL) {	
		ArrayList<String> data = new ArrayList<String>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next()) {			
				System.out.print(rs.getString("name"));
				System.out.print(" ");
				System.out.print(rs.getString("username"));
				System.out.print(" ");
				System.out.print(rs.getString("telf"));
				System.out.print(" ");
				System.out.print(rs.getString("id"));
				data.add(rs.getString("name"));
				data.add(rs.getString("username"));
				data.add(rs.getString("telf"));
				data.add(rs.getString("id"));
			} 
		} catch(SQLException e) {
			System.out.println(e);
		}
		return data;
	} 
	
	public void Close() {
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

	

