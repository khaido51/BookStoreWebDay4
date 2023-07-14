package coding.mentor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coding.mentor.db.util.DBUtil;
import coding.mentor.entity.Book;

public class BookService {

	public List<Book> getBooksByCategoryId(int categoryId) throws SQLException {

		// Connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		List<Book> list = new ArrayList<Book>();
//		list.add(new Category(1, "Geography"));
//		list.add(new Category(2, "Maths"));
//		list.add(new Category(3, "English"));
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// String query = "SELECT * FROM book where category_id = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement("SELECT * FROM `book` where category_id = ?");
			ps.setInt(1, categoryId);
			// execute and get result SET
			rs = ps.executeQuery();

			// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
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

	public List<Book> getBooksByName(String bookName) throws SQLException {
		System.out.println("Searching books by name: " + bookName);
		// Connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		List<Book> list = new ArrayList<Book>();
//		list.add(new Category(1, "Geography"));
//		list.add(new Category(2, "Maths"));
//		list.add(new Category(3, "English"));
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// String query = "SELECT * FROM book where category_id = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement("SELECT * FROM book where name like ?");
			ps.setString(1, "%" + bookName + "%");
			// execute and get result SET
			System.out.println("Search book name: " + bookName);
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

	public List<Book> getAllBooks() throws SQLException {
		// System.out.println("Searching books by name: " + bookName);
		// Connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		List<Book> list = new ArrayList<Book>();
//		list.add(new Category(1, "Geography"));
//		list.add(new Category(2, "Maths"));
//		list.add(new Category(3, "English"));
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// String query = "SELECT * FROM book where category_id = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement("SELECT * FROM book");
			// ps.setString(1, "%" + bookName + "%");
			// execute and get result SET
			// System.out.println("Search book name: " + bookName);
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

	public Book getBookDetails(int bookId) throws SQLException {

		// Connection
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// String query = "SELECT * FROM book where category_id = ?";
			// -> table Category
			// Run query "Select * from category"
			ps = conn.prepareStatement("SELECT * FROM book where id = ?");
			ps.setInt(1, bookId);
			// execute and get result SET
			// System.out.println("Search book name: " + bookName);
			rs = ps.executeQuery();

			// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int stock = rs.getInt("stock");
				double price = rs.getDouble("price");
				book = new Book(id, name, categoryId, title, author, stock, price);
		
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

		return book;

	}

}
