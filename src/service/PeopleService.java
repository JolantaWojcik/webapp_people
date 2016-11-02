package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					"insert into people(name, birthdate, height) values (?,?,?)",
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
			int height = rs.getInt("height");
			list.add(new Person(id, name, birthdate, height));
		}
		rs.close();
		return list;
	}

	public void insertEmployee(Person p) throws SQLException {
		
		insert.setString(1, p.getName());
		insert.setDate(2, (java.sql.Date) p.getDateOfBirth());
		insert.setInt(3, p.getHeight());

		insert.executeQuery();

		ResultSet rs = insert.getGeneratedKeys();
		if (rs.next()) {
			p.setId(rs.getInt("id"));
		}
		rs.close();
	}
}
