package coding.mentor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coding.mentor.db.util.DBUtil;
import coding.mentor.entity.Account;
import coding.mentor.entity.Book;

public class LoginService {
	public Account getUsernameAndPassword(String username, String password) throws SQLException {

		// Connection
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account = null;

		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			String query = "SELECT * FROM account where username = ? AND password = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
		  
			// execute and get result SET
			// System.out.println("Search book name: " + bookName);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int userId = rs.getInt("id");
		        account = new Account(username, password);
		        account.setId(userId);
			}
			
	
	       
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return account;
	}
}
