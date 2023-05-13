package crud;

import java.sql.SQLException;
import java.sql.Statement;

import modelo.Entrenador;
import modelo.Objeto;
import modelo.Pokemon;

public class CombateCRUD {
	/**
	 * Actuliza la base de datos 
	 * Actualiza las estadisticas del pokemon
	 * 
	 * @param Pokemon p
	 */
	public static void updateEstadisticas(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET nivel=" + p.getNivel() + ", vitalidad=" + p.getVitalidad()
				+ ", ataque=" + p.getAtaque() + ", ataque_especial=" + p.getAtaqueEspecial() + ", defensa="
				+ p.getDefensa() + ", defensa_especial=" + p.getDefensaEspecial() + ", velocidad=" + p.getVelocidad()
				+ ", estamina=" + p.getEstamina() + ",experiencia=" + p.getExperienciaObtenida() + " WHERE id_pokemon="
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
	 * Actuliza la base de datos
	 *  Actualiza la experiencia del pokemon
	 * 
	 * @param Pokemon p
	 */

	public static void updateExperienciaPokemon(Pokemon p) {
		String updateEquipo = "UPDATE pokemon SET experiencia=" + p.getExperienciaObtenida() + " WHERE id_pokemon="
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

}
