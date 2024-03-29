package fr.univamu.master.jee.exam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;

/**
 * Servlet implementation class Controller
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Person p = new Person();
		DAO dao = new PersonDAO();
		dao.init();
		p = dao.findPerson(Integer.parseInt(request.getParameter("id")));
		dao.close();

		request.setAttribute("person", p);
		request.getRequestDispatcher("details.htm").forward(request, response);
	}

	/**
	 * Check the form to change personal details.
	 * 
	 * @see Person
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String err = "";
		boolean changePass = true;
		boolean changeEmail = true;
		Person p = null;

		if (null == session.getAttribute("user"))
			request.getRequestDispatcher("directory.htm").forward(request,
					response);
		else
			p = (Person) session.getAttribute("user");

		if (!"".equals(request.getParameter("email"))) {
			if (!p.checkEmail(request.getParameter("email"))) {
				err += "No valid email.";
				changeEmail = false;
			}
		} else
			changeEmail = false;

		if (!"".equals(request.getParameter("oldPasswd"))
				&& !"".equals(request.getParameter("newPasswd"))
				&& !"".equals(request.getParameter("newPasswdBis"))) {

			if (null == p.canConnect(p.getLogin(),
					request.getParameter("oldPasswd"))) {
				err += "Wrong password! ";
				changePass = false;
			}

			if (!request.getParameter("newPasswd").equals(
					request.getParameter("newPasswdBis"))
				 || !p.checkPasswd(request.getParameter("newPasswd"))) {
				err += "New password incorrect.";
				changePass = false;
			}

		} else
			changePass = false;

		if (0 != err.length()) {
			session.setAttribute("error", err);
			request.getRequestDispatcher("edition.htm").forward(request,
					response);
			return;
		}

		if (true == changePass)
			p.setPassword(request.getParameter("newPasswd"));
		if (true == changeEmail)
			p.setEmail(request.getParameter("email"));
		p.setWebsite(request.getParameter("webSite"));

		request.setAttribute("person", p);

		DAO dao = new PersonDAO();
		dao.init();
		p = dao.updatePerson(p);
		dao.close();

		request.getRequestDispatcher("directory.htm")
				.forward(request, response);

	}
}
