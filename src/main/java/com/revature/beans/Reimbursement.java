package com.revature.beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.revature.util.ConnectionUtil;

public class Reimbursement {

	private int reportsto;

	public Reimbursement() {

		super();
	}

	public Reimbursement(int id, int account, String description, String date, String resolvedate, float amount,
			String name, String status,int reportsto) {

		this.id = id;
		this.account = account;
		this.description = description;
		this.postdate = date;
		this.amount = amount;
		this.name = name;
		this.status = status;
		this.setReportsto(reportsto);

	}

	public Reimbursement(int account, String description, String date, float amount, String name) {

		this.account = account;
		this.description = description;
		this.postdate = date;
		this.amount = amount;
		this.name = name;

	}
	public Reimbursement(int account, String description,float amount,int reportsto) {

		this.account = account;
		this.description = description;

		this.amount = amount;
		this.reportsto = reportsto;
		

	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {

		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) throws ParseException {
		LocalDate today = LocalDate.now();

		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

		this.postdate = formattedDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getResolvedate() {
		return resolvedate;
	}

	public void approveReimbursement() throws ClassNotFoundException, SQLException, IOException {

		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("here1");
			String sql = "UPDATE REIMBURSEMENT SET STATUS = 'APPROVED' WHERE REIMBURSEMENT_ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, this.id);

			pstmt.executeQuery();
			System.out.println("here2");

		}

	}
	
	public void denyReimbursement() throws ClassNotFoundException, SQLException, IOException {

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			System.out.println("here1");
			String sql = "UPDATE REIMBURSEMENT SET STATUS = 'DENIED', REIMBURSEMENT_RESOLVE_DATE = ? WHERE REIMBURSEMENT_ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
			pstmt.setInt(2, this.id);

			pstmt.executeQuery();
			System.out.println("here2");

		}

	}

	public void setResolvedate(String resolvedate) {
		LocalDate today = LocalDate.now();

		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

		this.resolvedate = formattedDate;
		
				
				
				
	}

	public String reimbursementInfo() {
		String reimbursementInfo = "REIMBURSEMENT ID : " + this.id + " ACCOUNT :" + this.account + " STATUS: "
				+ this.status + " ACCOUNT HOLDER :" + this.name + " AMOUNT : " + this.amount + " DESCRIPTION :"
				+ this.description + "DATE POSTED :" + this.postdate + "DATE RESOLVED" + this.resolvedate;

		return reimbursementInfo;

	}

	public void reimburseCommit() throws ClassNotFoundException, SQLException, IOException {

		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("here1");
			String sql = "INSERT INTO REIMBURSEMENT ( REIMBURSEMENT_ACCOUNT,REIMBURSEMENT_DESC,AMOUNT,STATUS)\r\n"
					+ "   VALUES ( ?,  ?,?, 'PENDING')";
			PreparedStatement pstmt = conn.prepareStatement(sql);

		
			pstmt.setInt(1, this.account);
			pstmt.setString(2, this.description);
			
			pstmt.setFloat(3, this.amount);

			pstmt.executeQuery();
			System.out.println("here2");

		}
	}

	private int id;
	private int account;
	private String description;
	private String postdate;
	private float amount;
	private String resolvedate;
	private String name;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReportsto() {
		return reportsto;
	}

	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}

}
