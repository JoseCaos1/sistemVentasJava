package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	Connection con;

	String myBD = "jdbc:mysql://localhost:3306/sistemaventas?serverTimezone=UTC";

	public Connection getConnection() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(myBD, "root", "");
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.toString());
			System.out.println(e.toString());
		}
		return null;
	}

}
