package crud;

import java.sql.SQLException;
import java.sql.Statement;

import modelo.Pokemon;

public class EntrenamientoCRUD {
	/**
	 * Actualiza las estadisticas del pokemon despues del entrenamiento pesado
	 * @param Pokemon p
	 */
	public static void updateEntrenamientoPesado(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET vitalidad=" + p.getVitalidad() + ", defensa=" + p.getDefensa()
				+ ", defensa_especial=" + p.getDefensaEspecial() + " WHERE id_pokemon=" + p.getIdPokemon() + ";";

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
	 * Actualiza las estadisticas del pokemon despues del entrenamiento furioso
	 * @param Pokemon p
	 */

	public static void updateEntrenamientoFurioso(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET ataque=" + p.getAtaque() + ", ataque_especial="
				+ p.getAtaqueEspecial() + ", velocidad=" + p.getVelocidad() + "" + " WHERE id_pokemon="
				+ p.getIdPokemon() + ";";

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
	 * Actualiza las estadisticas del pokemon despues del entrenamiento funcional
	 * @param Pokemon p
	 */

	public static void updateEntrenamientoFuncional(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET  vitalidad=" + p.getVitalidad() + ", ataque=" + p.getAtaque()
				+ ", defensa=" + p.getDefensa() + ", velocidad=" + p.getVelocidad() + " WHERE id_pokemon="
				+ p.getIdPokemon() + ";";

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
	 * Actualiza las estadisticas del pokemon despues del entrenamiento onirico
	 * @param Pokemon p
	 */

	public static void updateEntrenamientoOnirico(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET vitalidad=" + p.getVitalidad() + ", ataque_especial="
				+ p.getAtaqueEspecial() + "," + " defensa_especial=" + p.getDefensaEspecial() + ", velocidad="
				+ p.getVelocidad() + " WHERE id_pokemon=" + p.getIdPokemon() + ";";

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

}
