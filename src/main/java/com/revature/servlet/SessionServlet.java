package com.revature.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
@WebServlet("/session")

public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = -1319793763433572026L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		try {
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			String firstname = session.getAttribute("firstname").toString();
			String lastname = session.getAttribute("lastname").toString();
			String password = session.getAttribute("password").toString();
			String admin = session.getAttribute("admin").toString();
			int managerId = Integer.parseInt(session.getAttribute("manager").toString());
			User u = new User(userId, firstname, lastname,password,admin,managerId);
			
			resp.getWriter().write((new ObjectMapper()).writeValueAsString(u));
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"session\":null}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
