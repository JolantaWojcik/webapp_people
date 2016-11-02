package service;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class DbService {
	public Connection connection() throws Exception {
		Connection conn;
		Context init = new InitialContext();
		Context contx = (Context) init.lookup("java:comp/env");
		DataSource dataSource = (DataSource) contx.lookup("jdbc/data");
		synchronized (dataSource) {
			conn = dataSource.getConnection();
		}
		return conn;
	}
}
