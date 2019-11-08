package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.service.ReimbursementService;


public class ReimbursementServlet extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;


	private ReimbursementService remService;

	
	private ObjectMapper om;

	public ReimbursementServlet() throws ClassNotFoundException, SQLException, IOException {
		remService = new ReimbursementService();
		om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		
	}

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
		String idString = session.getAttribute("userId").toString();
		System.out.println(idString);
		if (idString != null) {
			// try and find the desired bear
			try {
				int id = Integer.parseInt(idString);
				System.out.println("makes it here");
//				System.out.println(id);
//				System.out.println(remService.getReimbursmentbyId(121));
				String reimbursementJson = om.writeValueAsString(remService.getReimbursements());
				
				System.out.println(reimbursementJson);
				// if ObjectMapper gets a null value, it will be converted to a String "null"
				if (!reimbursementJson.equals("null")) {
					resp.getWriter().write(reimbursementJson);
					
				} else {
					resp.sendError(404);
				}
			} catch (Exception e) {
				resp.sendError(400); // general bad request
			}
		} else {
			// otherwise return all bears
			resp.getWriter().write(om.writeValueAsString(remService.getReimbursements()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "*");
	}


}