package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.PeopleService;

@WebServlet("/addPerson")
public class AddPersonServlet extends HttpServlet{
	private PeopleService peopleService;

	@Override
	public void init() throws ServletException {
		peopleService = new PeopleService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean validated = validateResult(request);
		if (validated) {
			addPerson(request);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

	private void addPerson(HttpServletRequest request) {
		try {
			peopleService
					.insertEmployee(new Person(-1, request.getParameter("name"), 
							Date.valueOf(request.getParameter("birthdate")),
							Integer.parseInt(request.getParameter("height")),
							peopleService.calculateAge(Date.valueOf(request.getParameter("birthdate")))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validateResult(HttpServletRequest request) {
		boolean result = true;
		if (request.getParameter("name") == null || request.getParameter("name").length() == 0) {
			result = false;
			request.setAttribute("error_name", "imie nie moze byc puste");
		}
		if (request.getParameter("birthdate") == null || request.getParameter("birthdate").length() == 0) {
			result = false;
			request.setAttribute("error_birthdate", "data nie moze byc pusta");
		}
		if (request.getParameter("height") == null || request.getParameter("height").length() == 0) {
			result = false;
			request.setAttribute("error_height", "wzros nie moze byc pusty");
		}
		return result;
	}
}
