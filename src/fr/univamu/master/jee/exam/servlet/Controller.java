package fr.univamu.master.jee.exam.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;
import fr.univamu.master.jee.exam.exception.DateException;

/**
 * Servlet implementation class Controler
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
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

	/**
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
			request.getRequestDispatcher("directory.jsp").forward(request,
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
				|| !"".equals(request.getParameter("newPasswd"))
				|| !"".equals(request.getParameter("newPasswdBis"))) {

			if (null == p.canConnect(p.getLogin(),
					request.getParameter("oldPasswd"))) {
				err += "Wrong password!";
				changePass = false;
			}

			if (!request.getParameter("newPasswd").equals(
					request.getParameter("newPasswdBis"))) {
				err += "New password should be the same for both input.";
				changePass = false;
			}

			if (!p.checkPasswd(request.getParameter("newPasswd"))) {
				err += "No valid password.";
				changePass = false;
			}

		} else
			changePass = false;

		if (0 != err.length()) {
			session.setAttribute("error", err);
			request.getRequestDispatcher("edition.jsp").forward(request,
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

		request.getRequestDispatcher("directory.jsp")
				.forward(request, response);

	}
}
