package fr.univamu.master.jee.exam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univamu.master.jee.exam.beans.Person;

/**
 * Servlet implementation class Connection
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
@WebServlet("/connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Check user information before the connection.
	 * 
	 * @see Person#canConnect(String, String)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("user"))
			request.getRequestDispatcher("directory.htm").forward(request, response);

		Person p = new Person();
		p = p.canConnect(request.getParameter("login"), request.getParameter("password"));
		if (null == p) {
			session.setAttribute("error", "Wrong login and/or password.");
			request.getRequestDispatcher("connection.htm").forward(request, response);
		} else {
			session.setAttribute("user", p);
			request.getRequestDispatcher("directory.htm").forward(request, response);
		}
		
	}

}
