package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

	//@Autowired
	private PeopleService peopleService;
	private DataService ds;
	
	@Override
	public void init() throws ServletException {
		peopleService = new PeopleService();
		//adres bazy
		//emaile na ktre ma cos wyslac
		//konfiguracje table
		//uprawnienia
		//inne...
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
		
		Map<String, Long> statistics = ds.birthByMonth(people);
		String saveStc = request.getParameter("saveStatistics");
		if(saveStc!=null){
			ObjectOutputStream oos = new ObjectOutputStream
					(new FileOutputStream("C:/Users/Dell/Desktop/java korki/save_statistics.txt"));
			oos.writeObject(statistics);
			oos.close();
		}
		
		//problem z polskimi znakami
		String month = request.getParameter("month");
		List<Person> birth_by_month = null;
		if(month!=null){
			request.setAttribute("peopleDetaildList", ds.getAllPeopleByMonth(month, people));	
			birth_by_month = ds.getAllPeopleByMonth(month, people);
		}

		//beznadziejny kod, musi sie dac zrobic inaczej
		String save = request.getParameter("savePeopleByMonth");
		if(save!=null){
			ObjectOutputStream oos = new ObjectOutputStream
					(new FileOutputStream("C:/Users/Dell/Desktop/java korki/birth_by_month.txt"));
			oos.writeObject(birth_by_month);
			oos.close();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/subPage.jsp");
		rd.forward(request, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
