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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		String err = "";

		Person p = null;

		if (null == session.getAttribute("user"))
			request.getRequestDispatcher("directory.jsp").forward(request,
					response);
		else
			p = (Person) session.getAttribute("user");

		if (null != request.getParameter("firstName"))
			p.setFirstName(request.getParameter("firstName"));

		if (null != request.getParameter("lastName"))
			p.setLastName(request.getParameter("lastName"));

		if (null != request.getParameter("birthdate")) {

			try {
				p.checkBirthDate(request.getParameter("birthdate"));
					
				p.setBirthdate(simpleDateFormat.parse(request
						.getParameter("birthdate")));

			} catch (DateException e) {
				err = "Error birthdate";
				session.setAttribute("error", err);
				request.getRequestDispatcher("edition.jsp").forward(request,
						response);
				e.printStackTrace();
			} catch (ParseException e) {
				session.setAttribute("error", err);
				request.getRequestDispatcher("edition.jsp").forward(request,
						response);
				e.printStackTrace();
			}

		}
		if (null != request.getParameter("webSite"))
			p.setWebsite(request.getParameter("webSite"));

		if (null != request.getParameter("email"))
			p.setEmail(request.getParameter("email"));

		if (0 != err.length()) {
			session.setAttribute("error", err);
			request.getRequestDispatcher("edition.jsp").forward(request,
					response);
		}

		request.setAttribute("person", p);

		DAO dao = new PersonDAO();
		dao.init();
		p = dao.updatePerson(p);
		dao.close();

		request.getRequestDispatcher("directory.jsp")
				.forward(request, response);

	}

}
