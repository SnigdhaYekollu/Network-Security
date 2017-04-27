package DBfunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DBconn {

	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		public DBconn(){
	
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/securecloud","root","root");
				if(conn!=null)
				System.out.println("Connected to database");
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		

	/** inserts values into database
	 * @param userName
	 * @param fileName
	 * @param encFileKey
	 * @param encHashKey
	 * @param hashValue
	 * @param doubleHashPass
	 * @throws SQLException
	 */
	public void insert(String filename, boolean passwordexist, String password, byte[] enckey, byte[] hashkey,String hashvalue) throws SQLException{

		PreparedStatement p = conn.prepareStatement("insert into keystore values(?,?,?,?,?,?)");

		
		p.setString(1, filename);
		p.setBoolean(2, passwordexist);
		p.setString(3, password);
		p.setBytes(4, enckey);
		p.setBytes(5, hashkey);
		p.setString(6, hashvalue);
	    
		p.executeUpdate();


	}

	/** used to retrieve values from the data base.
	 * @param userName
	 * @param fileName
	 * @return
	 */
	public DBstore getData(String fileName){
		
		ResultSet rs=null;
		String input = "select * from keystore where filename = '" +fileName + "'"; 
		DBstore obj1 =new DBstore();
		try {
			rs= stmt.executeQuery(input);
			while(rs.next()){
		    //System.out.println(Arrays.toString(b1) + "\n"+ rs.getString(5)+"\n"+rs.getString(6));
				obj1.setPasswordExist(rs.getBoolean(2));
				obj1.setFileName(rs.getString(1));
				obj1.setFileEncKey(rs.getBytes(4));
				obj1.setHashEncKey(rs.getBytes(5));
				obj1.setHashValue(rs.getString(6));
				obj1.setPassword(rs.getString(3));
			
			}  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	return obj1;

		
	}



	
}
