package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.beans.Reimbursement;
import com.revature.service.ReimbursementService;

@WebServlet("/approvedeny")
public class ApproveDenyServlet extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	// this is our service class whose methods we will call
	private ReimbursementService remService;

	// we'll use this to convert Java objects to JSON-formatted data to include in
	// response bodies
	// we use Jackson to get this dependency
	private ObjectMapper om;

	public ApproveDenyServlet() throws ClassNotFoundException, SQLException, IOException {
		remService = new ReimbursementService();
		om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		
	}

	// return all bears, in JSON format, IF no bear id is specified. Otherwise
	// return the requested bear.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("here at get");
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("userId") != null) {
//				req.getRequestDispatcher("AdminReimbursement.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
		setAccessControlHeaders(resp);
		// check whether there is an id provided in the query string
		String idString = req.getParameter("reimbursementid").toString();
		String nuStatus = req.getParameter("nuStatus").toString();
		System.out.println(idString);
		if (idString != null) {
			// try and find the desired bear
			
				int id = Integer.parseInt(idString);
				System.out.println("makes it here");
//				System.out.println(id);
//				System.out.println(remService.getReimbursmentbyId(121));
				ArrayList<Reimbursement> reimbursements = remService.getReimbursements();
				for (Reimbursement rem : reimbursements) {
					
					if((rem.getId() == id)&&(rem.getStatus().equals("PENDING"))) {
						
						if(nuStatus.equals("APPROVED")) {
							
							try {
								rem.approveReimbursement(); 
								
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						if(nuStatus.equals("DENIED")) {
							
							try {
								rem.denyReimbursement();
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
						
					}
					
				
				
				
		
	}}}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read request body and convert to a Java object from JSON format
//		if (remService.createBear(om.readValue(req.getReader(), Reimbursement.class))) {
//			resp.setStatus(200);
//		} else {
//			resp.setStatus(400);
//		}
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "*");
	}


}