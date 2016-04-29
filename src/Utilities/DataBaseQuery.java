package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseQuery {
	
	String serverDB;
	String userDB;
	String pwdDB; 
	
	public DataBaseQuery() throws Exception{
		ReadPropertyFile data = new ReadPropertyFile();
		serverDB = "jdbc:oracle:thin:@"+data.getServerDB();
		userDB = data.getUserDB();
		pwdDB = data.getPwdDB();						
	}

	public String Select(String fromWhere, String columnReturn){
	
		String text = "";
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(serverDB,userDB,pwdDB);
			
			Statement st = con.createStatement();
			
			String sql = "select "+columnReturn+" from "+fromWhere;
			
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			
//			rs.last();
//			
//			String result ="";
//			int rowCount = rs.getRow();
//			if(rowCount < 6){
//				for(int i=1; i <= rowCount; i++){
//					
//					
//				}
//			}
			
			
			text = rs.getString(1);
			 	 
			con.close();
			
			rs.close();
			
			return text;
			
		}catch(Exception e){
			System.out.println(e);
			return text;
		}
	}
	
	public int Count(String fromWhere){
		
		int rows = 0;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(serverDB,userDB,pwdDB);
			
			Statement st = con.createStatement();
			
			String sql = "select Count(*) from "+fromWhere;
			
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			
			rows = rs.getInt(1);
			 	 
			con.close();
			
			rs.close();
			
			return rows;
			
		}catch(Exception e){
			System.out.println(e);
			return rows;
		}

	}
	
}
