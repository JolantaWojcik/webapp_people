package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.InputStream;

import model.Person;
import service.PeopleService;

@WebServlet("/")
public class StarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PeopleService peopleService;
	private Person person;
	
	@Override
	public void init() throws ServletException {
		peopleService = new PeopleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sortBy;
		List<Person> people = null;
		try {
			sortBy = Optional.ofNullable(request.getParameter("sortBy")).orElse("id");
			request.setAttribute("peopleList", peopleService.getPeople(sortBy));
			people = peopleService.getPeople(sortBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//beznadziejny kod, musi sie dac zrobic inaczej
		String save = request.getParameter("save");
		if(save!=null){
			String path = System.getProperty("user.home");
			//klient ten plik ma sciagnac.
			ObjectOutputStream oos = new ObjectOutputStream
					(new FileOutputStream(path +"/Downloads/" + "all_users.txt"));
			oos.writeObject(people);
			oos.close();
//			Paths.get(".").toAbsolutePath().normalize().toString();
		   }
		
//		response.getWriter().println("<br>Operator to: " + Paths.get(".").toAbsolutePath().normalize().toString()
//				+ "</br>");
//		response.getWriter().println("<br>Operator to: " + people + "</br>");
		RequestDispatcher rd = request.getRequestDispatcher("startPage.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
