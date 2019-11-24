package Database;

import java.sql.Connection;
import java.sql.DriverManager;

//https://www.tutorialspoint.com/postgresql/postgresql_java.htm
//http://www.postgresqltutorial.com/postgresql-jdbc/
public class PostgreSQLConnector {
	
	private final String url = "jdbc:postgresql://localhost:5432/alertapp";
	private final String user = "postgres";
	private final String password = "croakusite9697";

	//Establishes connection to PostgreSQL database
	//returns connection object
	public Connection connect() {
		Connection conn = null;
		
		try {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager.getConnection(url, user, password);
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }	
		
		System.out.println("Database opened successfully!");
		return conn;
	}
	
	public static void main(String[] args) {
        PostgreSQLConnector pgsc = new PostgreSQLConnector();
        pgsc.connect();
    }

}
