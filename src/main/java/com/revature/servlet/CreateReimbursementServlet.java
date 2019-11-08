package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.service.ReimbursementService;

@WebServlet("/create")
public class CreateReimbursementServlet extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2L;




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
		Reimbursement rem = null;
		// check whether there is an id provided in the query string
		int idString = (int) session.getAttribute("userId");
		int manager = (int) session.getAttribute("manager");
		float amount = Integer.parseInt(req.getParameter("amount").toString());
		String description = req.getParameter("description").toString();
		rem = new Reimbursement( idString, description,amount,manager);
		System.out.println("reimbursement time!");
	
			try {
				rem.reimburseCommit();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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