package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.PeopleService;

@WebServlet("/removePerson")
public class RemovePersonServlet extends HttpServlet{
	
		private PeopleService peopleService;

		@Override
		public void init() throws ServletException {
			peopleService = new PeopleService();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			removePerson(request);

			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
		}

		private void removePerson(HttpServletRequest request) {
			
			try {
				int id = Integer.valueOf(request.getParameter("id"));
				peopleService.deleteEmployee(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
