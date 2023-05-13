package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

public class MovimientoCRUD {
	/**
	 * Crea una lista con todos los movimientos
	 * @return LinkedList<Movimiento>
	 */
	public static LinkedList<Movimiento> todosMovimientos() {
		String sqlString = "SELECT m.nombre,m.potencia,m.clase_movimiento,m.probabilidad, m.duracion,m.mejora,"
				+ " m.categoria,(SELECT t.nombre FROM tipo t WHERE t.id_tipo=m.tipo) AS tipo , "
				+ "(SELECT e.nombre FROM estado e WHERE e.id_estado=m.estado) AS estado  FROM movimiento m;";
		PreparedStatement preparedStatement = null;
		LinkedList<Movimiento> lista = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String tipoMovimiento = rs.getString("clase_movimiento");
				int potencia = rs.getInt("potencia");
				String tipoAtaque = rs.getString("tipo");
				String estado = rs.getString("estado");
				int probabilidad = rs.getInt("probabilidad");
				int numTurnos = rs.getInt("duracion");
				String mejora = rs.getString("mejora");
				String categoria = rs.getString("categoria");
				Movimiento movimiento = new Movimiento(nombre, tipoMovimiento, potencia, Tipo.valueOf(tipoAtaque),
						estado, probabilidad, numTurnos, mejora, categoria);
				lista.add(movimiento);

			}
			return lista;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}
	/**
	 * Extrae el id del movimiento pasandole el nombre
	 * @param String nombre
	 * @return int
	 */

	public static int selectIdMovimiento(String nombre) {
		String sqlString = "SELECT id_movimiento FROM movimiento WHERE nombre='" + nombre + "';";
		PreparedStatement preparedStatement = null;
		int idMovimiento = 0;
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				idMovimiento = rs.getInt("id_movimiento");
			}
			return idMovimiento;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idMovimiento;

	}
	/**
	 * Actualiza la base de datos
	 * Sustituye el movimiento que quieres olvidar por el aprendido en un Pokemon
	 * @param String movimientoAprender
	 * @param String movimientoOlvidar
	 * @param Pokemon p
	 */

	public static void updateAprenderMovimiento(String movimientoAprender, String movimientoOlvidar, Pokemon p) {
		int movimientoID = selectIdMovimiento(movimientoAprender);
		String updateEquipo = "UPDATE pokemon SET movimiento"
				+ posicionMovimiento(p.getMovimientos(), movimientoOlvidar) + "=" + movimientoID + " "
				+ "WHERE id_pokemon=" + p.getIdPokemon() + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(updateEquipo);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Determina que posicion tiene un movimiento
	 * @param LinkedList<Movimiento> m
	 * @param String movimiento
	 * @return int
	 */

	public static int posicionMovimiento(LinkedList<Movimiento> m, String movimiento) {
		int posicion = 1;
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getNombre() == movimiento) {
				posicion = i + 1;
			}
		}
		return posicion;

	}
	/**
	 * Extrae todos los movimientos de un Pokemon
	 * @param Pokemn p
	 * @return LinkedList<Movimiento>
	 */
	

	public static LinkedList<Movimiento> movimientosPokemon(Pokemon p) {
		String sqlString = "SELECT m.nombre,m.potencia,m.clase_movimiento,m.probabilidad, m.duracion,m.mejora, m.categoria,"
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=m.tipo) AS tipo ,"
				+ "(SELECT e.nombre FROM estado e WHERE e.id_estado=m.estado) AS estado "
				+ "FROM movimiento m JOIN pokemon p ON m.id_movimiento=p.movimiento1 OR m.id_movimiento=p.movimiento2 "
				+ "OR m.id_movimiento=p.movimiento3 OR m.id_movimiento=p.movimiento4 WHERE p.id_pokemon="
				+ p.getIdPokemon() + ";";
		PreparedStatement preparedStatement = null;
		LinkedList<Movimiento> lista = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String tipoMovimiento = rs.getString("clase_movimiento");
				int potencia = rs.getInt("potencia");
				String tipoAtaque = rs.getString("tipo");
				String estado = rs.getString("estado");
				int probabilidad = rs.getInt("probabilidad");
				int numTurnos = rs.getInt("duracion");
				String mejora = rs.getString("mejora");
				String categoria = rs.getString("categoria");
				Movimiento movimiento = new Movimiento(nombre, tipoMovimiento, potencia, Tipo.valueOf(tipoAtaque),
						estado, probabilidad, numTurnos, mejora, categoria);
				lista.add(movimiento);

			}
			return lista;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}

}
