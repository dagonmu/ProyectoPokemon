package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Movimiento;
import modelo.Objeto;
import modelo.Pokemon;
import modelo.Tipo;

public class EvolucionCRUD {
	/**
	 * Extrae el numero de pokedex de la evolucion del Pokemon introducido
	 * @param Pokemon p
	 * @return int
	 */
	public static int selectNumPokedexEvolucion(Pokemon p) {
		String sqlString = "SELECT po.num_pokedex FROM pokedex po JOIN pokemon p ON po.num_pokedex=p.num_pokedex "
				+ "WHERE p.id_pokemon=" + p.getIdPokemon() + ";";
		PreparedStatement preparedStatement = null;
		int idPokedex = 0;
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);
			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				idPokedex = rs.getInt("num_pokedex");
			}
			return idPokedex + 1;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPokedex + 1;

	}
	/**
	 * Actualiza la base de datos
	 * Cambia el numero de pokedex del pokemon evolucionado
	 * @param Pokemon p
	 */

	public static void evolucionPokemon(Pokemon p) {
		String updateEvolucion = "UPDATE pokemon SET num_pokedex=" + selectNumPokedexEvolucion(p) + " WHERE id_pokemon="
				+ p.getIdPokemon() + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(updateEvolucion);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
