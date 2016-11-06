package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.DataService;
import service.PeopleService;

@WebServlet("/showDetails")
public class ShowDetailsServlet extends HttpServlet{

	private PeopleService peopleService;
	private DataService ds;
	
	@Override
	public void init() throws ServletException {
		peopleService = new PeopleService();
		ds = new DataService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		PeopleService peopleService = new PeopleService();
		String sortBy = Optional.ofNullable(request.getParameter("sortBy")).orElse("id");
		List<Person> people = null;
		try {
			people = peopleService.getPeople(sortBy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ds.birthByMonth(people);
		request.setAttribute("birthByMonth", ds.birthByMonth(people));
		
		String month = request.getParameter("month");
		request.setAttribute("peopleList", ds.getAllPeopleByMonth(month, people));
		
		RequestDispatcher rd = request.getRequestDispatcher("/subPage.jsp");
		rd.forward(request, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
