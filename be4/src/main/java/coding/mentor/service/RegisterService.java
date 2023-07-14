package coding.mentor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import coding.mentor.db.util.DBUtil;
import coding.mentor.entity.Account;


public class RegisterService {
	@SuppressWarnings("null")
	public boolean registerAccount(Account account) throws SQLException {

		// Connection
		Connection conn = null;
		PreparedStatement ps = null;
		//Account account = null;
		//ResultSet rs = null;
		//Account account = null;
		
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			String query = "INSERT INTO account (username, password, email) VALUES (?,?,?)";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement(query);
			
		    // Create a new Account instance and set its properties
	        //account = new Account(username, password, email);
	
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getEmail());
			// execute and get result SET
			// System.out.println("Search book name: " + bookName);
			ps.execute();
			// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return true;
	}
}
