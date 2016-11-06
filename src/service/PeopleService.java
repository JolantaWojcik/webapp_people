package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Person;

public class PeopleService extends DbService{
	private PreparedStatement insert;
	private PreparedStatement delete;

	private Connection connection;

	public PeopleService() {
		try {
			this.connection = connection();
			this.insert = connection.prepareStatement(
					"insert into people (name, birthdate, height, age) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			this.delete = connection.prepareStatement("delete from people where id = ?");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public List<Person> getPeople(String sortBy) throws SQLException {
		PreparedStatement select = connection.prepareStatement("select * from people order by " + sortBy);
		ResultSet rs = select.executeQuery();
		List<Person> list = new ArrayList<Person>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Date birthdate = rs.getDate("birthdate");
			int age = rs.getInt("age");
			int height = rs.getInt("height");
			list.add(new Person(id, name, birthdate, age, height));
		}
		rs.close();
		return list;
	}
	
	public int calculateAge(Date date) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = fromDate(date);

		int age = 0 ;
		if ((birthday != null) && (today != null)) {
			age = Period.between(birthday, today).getYears();
		}
		return age; 
	}
	 
	 public LocalDate fromDate(Date date) {
		    Instant instant = Instant.ofEpochMilli(date.getTime());
		    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
		        .toLocalDate();
		  }

	public void insertEmployee(Person p) throws SQLException {
		
		insert.setString(1, p.getName());
		insert.setDate(2, (java.sql.Date) p.getBirthdate());
		insert.setInt(3, p.getHeight());
		insert.setInt(4, p.getAge());
		

		//insert.executeQuery();
		insert.executeUpdate();

		ResultSet rs = insert.getGeneratedKeys();
		if (rs.next()) {
			p.setId(rs.getInt("id"));
		}
		rs.close();
	}
	
	public void deleteEmployee(int id) throws SQLException {

		delete.setInt(1, id);

		delete.executeUpdate();

	}
}
