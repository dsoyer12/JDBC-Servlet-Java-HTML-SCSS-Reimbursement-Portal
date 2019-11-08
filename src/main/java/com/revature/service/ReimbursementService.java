package com.revature.service;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.util.*;

 public class ReimbursementService  {
	   ArrayList <Reimbursement> rem;
	 public ReimbursementService()throws ClassNotFoundException, SQLException, IOException {
		 
		 
		 try (Connection conn = ConnectionUtil.getConnection()) {
			 rem= new ArrayList<Reimbursement>();
				System.out.println("here1");
				String sql = "SELECT (A.FIRSTNAME||' '||A.LASTNAME) AS NAME,A.REPORTSTO, B.REIMBURSEMENT_ID,B.REIMBURSEMENT_ACCOUNT,B.REIMBURSEMENT_DESC,B.REIMBURSEMENT_IMG_ID,B.REIMBURSEMENT_DATE,B.REIMBURSEMENT_RESOLVE_DATE,B.AMOUNT,B.STATUS FROM EMPLOYEE A INNER JOIN REIMBURSEMENT B ON A.EMPLOYEEID = B.REIMBURSEMENT_ACCOUNT";
				PreparedStatement pstmt = conn.prepareStatement(sql);
			
				
				ResultSet rs= pstmt.executeQuery();
				System.out.println("here2");

				while(rs.next()) {
					System.out.println("query successful");
					int managerid = rs.getInt("REPORTSTO");
					String name = rs.getString("NAME");
					String description = rs.getString("REIMBURSEMENT_DESC");
					String submitdate = rs.getString("REIMBURSEMENT_DATE");
					String resolvedate = rs.getString("REIMBURSEMENT_RESOLVE_DATE");
					float amount = rs.getFloat("AMOUNT");
					String status = rs.getString("STATUS");
					int reimaccount = rs.getInt("REIMBURSEMENT_ACCOUNT");
					
					int id = rs.getInt("REIMBURSEMENT_ID");
					
					System.out.println(description);
						
						Reimbursement reim = new Reimbursement(id,reimaccount,description,submitdate,resolvedate,amount,name,status,managerid);
						System.out.println(reim.getDescription());
						rem.add(reim);
					
				}
					
							

		 
		 
		 
	 }
	 } 
	
	 public Reimbursement getReimbursmentbyId(int id) {
		 Reimbursement temp = new Reimbursement();
		 for (Reimbursement re: rem) {
			 
			 if(re.getId()== id) {
				 System.out.println(re.getId());
				 
				 temp =  re; 
			 }
			 
		 }
		 System.out.println(temp);
		 System.out.println(temp.getDescription());
		return temp;
		 
		 
		 
		 
		}
		

	 public ArrayList<Reimbursement> getReimbursements() {
	 
	 

			return rem;}}