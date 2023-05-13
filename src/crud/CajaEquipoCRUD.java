package crud;

import java.sql.SQLException;
import java.sql.Statement;

import modelo.Entrenador;
import modelo.Pokemon;

public class CajaEquipoCRUD {
	/**
	 * Actuliza la base de datos 
	 * Inserta el pokemon en la caja del entrenador y lo saca
	 * del equipo
	 * 
	 * @param Pokemon    p
	 * @param Entrenador en
	 */
	public static void updateCaja(Pokemon p, Entrenador en) {
		String updateCaja = "UPDATE pokemon SET id_caja=" + EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ", "
				+ "id_equipo=null WHERE id_pokemon=" + p.getIdPokemon() + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(updateCaja);

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
	 * Inserta el pokemon en el equipo del entrenador y
	 * sacarlo de la caja
	 * 
	 * @param Pokemon    p
	 * @param Entrenador en
	 */

	public static void updateEquipo(Pokemon p, Entrenador en) {
		String updateEquipo = "UPDATE pokemon SET id_equipo=" + EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ", "
				+ "id_caja=null WHERE id_pokemon=" + p.getIdPokemon() + ";";

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
