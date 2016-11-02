package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PeopleService;


@WebServlet("/")
public class StarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PeopleService peopleService;

	@Override
	public void init() throws ServletException {
		peopleService = new PeopleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String sortBy = Optional.ofNullable(request.getParameter("sortBy")).orElse("id");
			request.setAttribute("peopleList", peopleService.getPeople(sortBy));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("startPage.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
