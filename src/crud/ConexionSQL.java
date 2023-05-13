package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import modelo.Pokemon;

public class ConexionSQL {
	/**
	 * Conexion a la base de datos
	 */
	private static Connection connection;
	LinkedList<Pokemon> p = PokemonCRUD.selectPokemon();

	public static Connection getConexion() throws SQLException, ClassNotFoundException {
		if (connection == null) {
			String url = "jdbc:mysql://localhost:3306/pokemon";
			String login = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
			System.out.println("Conexión establecida");
		}
		return connection;
	}

}
