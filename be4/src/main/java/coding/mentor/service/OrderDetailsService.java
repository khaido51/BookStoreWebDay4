package coding.mentor.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coding.mentor.db.util.DBUtil;
import coding.mentor.entity.Book;
import coding.mentor.entity.Order;
import coding.mentor.entity.OrderDetails;

public class OrderDetailsService {
	
	public int addOrderDetails(OrderDetails orderDetails) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		

		try {
			conn = DBUtil.makeConnection();

			// QUery
			String query = "INSERT INTO `order_details`(bookId, orderId) VALUE (?,?)";
			ps = conn.prepareStatement(query);

			// Set params
			ps.setInt(1, orderDetails.getBookId());
			ps.setInt(2, orderDetails.getOrderId());
		

			ps.execute();


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
		return 0;
	}
	
	
	public List<Book> showOrderDetails(int orderID) throws SQLException{
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		List<Book> list= new ArrayList<Book>();
		
		
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// String query = "SELECT * FROM book where category_id = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement("SELECT b.id, b.`name`,b.category_id, b.title, b.author, b.stock,  b.price from book b join order_details od on b.id = od.bookId join `order` o on o.id = od.orderId  where o.id = ?");
			ps.setInt(1, orderID);
			// execute and get result SET
			rs = ps.executeQuery();

			// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int stock = rs.getInt("stock");
				double price = rs.getDouble("price");
				book = new Book(id, name, categoryId, title, author, stock, price);
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return list;
	}
		
	
	
}
