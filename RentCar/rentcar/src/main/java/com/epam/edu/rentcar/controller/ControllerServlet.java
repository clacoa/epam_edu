package com.epam.edu.rentcar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.CommandFactory;
import com.epam.edu.rentcar.service.LoginChecker;

/**
 * Servlet implementation class LoginForm
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(
				request.getAttribute("sendRedirect").toString()).forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		CommandFactory.getInstance().invoke(command, request, response);
		//response.sendRedirect(request.getAttribute("sendRedirect").toString());
		request.getRequestDispatcher(
				request.getAttribute("sendRedirect").toString()).forward(
				request, response);
	}
    protected String getActionName(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.substring(1, path.lastIndexOf("."));
   }
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		     Action action = factory.create(getActionName(request));
		     String url = action.perform(request, response);
		     if (url != null)
		          getServletContext().getRequestDispatcher(url).forward(request, response);
		}

}
