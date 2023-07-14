package coding.mentor.entity;

import java.util.Date;

public class Order {
	private int id;
	private int userId;
	private Date submitDate;
	private boolean approve;
	

	public Order() {
		super();
	}

	public Order(int userId, boolean approve) {
		super();
		this.userId = userId;
		this.approve = approve;
	}

	public Order(int id, int userId, Date submitDate, boolean approve) {
		super();
		this.id = id;
		this.userId = userId;
		this.submitDate = submitDate;
		this.approve = approve;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

}
