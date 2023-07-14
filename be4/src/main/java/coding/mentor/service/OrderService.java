package coding.mentor.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coding.mentor.db.util.DBUtil;
import coding.mentor.entity.Order;

public class OrderService {

	public int addOrder(Order order) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.makeConnection();

			// QUery
			String query = "INSERT INTO `order`(userId, submitDate, approve) VALUE (?,?,?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			// Set params
			ps.setInt(1, order.getUserId());
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setBoolean(3, order.isApprove());

			ps.execute();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int insertedId = rs.getInt(1);
				return insertedId;
			}
			return 0;

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
		return 0;

	}

}
