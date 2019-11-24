package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	//finds user with specified username and password
	/*
	public void findLogin(String username, String password) {
		String query = "SELECT user_id " + "FROM User " + "WHERE username = ? " + "AND password = ? ";
		
		try {
			Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
 
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}*/
	
	//inserts user into database
	/*public int insertUser(User user) {
		String query = "INSERT INTO actor(username ,password, email, address, admin) "+ "VALUES(?,?,?,?,?)";
		
		int id = 0;
		
		try {
			Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress);
            pstmt.setBoolean(5, user.getAdmin());
            
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try  {
                	ResultSet rs = pstmt.getGeneratedKeys();
                	
                	if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
       
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
	}*/
	
	public static void main(String[] args) {
        PostgreSQLConnector pgsc = new PostgreSQLConnector();
        pgsc.connect();
    }

}
