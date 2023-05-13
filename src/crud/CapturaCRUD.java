package crud;

import java.sql.SQLException;
import java.sql.Statement;

import modelo.Entrenador;
import modelo.Pokemon;

public class CapturaCRUD {
	/**
	 * Actuliza la base de datos 
	 * Inserta el pokemon capturado en la caja del entrenador
	 * 
	 * @param Pokemon    p
	 * @param Entrenador en
	 */
	public static void insertCapturaCaja(Pokemon p, Entrenador en) {
		int movimiento1 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(0).getNombre());

		String insertCaja = "INSERT INTO pokemon (num_pokedex, id_caja, id_entrenador, mote, sexo, nivel, "
				+ "vitalidad, ataque, ataque_especial, defensa, defensa_especial, velocidad, estamina, fertilidad,  "
				+ "movimiento1, movimiento2, movimiento3, movimiento4) VALUES(" + p.getNumPokedex() + "	, "
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ","
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ", " + " '" + p.getMote() + "', '" + p.getSexo()
				+ "'," + p.getNivel() + "," + p.getVitalidad() + "," + p.getAtaque() + "," + p.getAtaqueEspecial() + ","
				+ p.getDefensa() + "," + p.getDefensaEspecial() + ", " + p.getVelocidad() + "," + p.getEstamina() + ","
				+ p.getFertilidad() + "," + movimiento1 + ", null, null, null);";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(insertCaja);

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
	 * Inserta el pokemon capturado en el equipo del entrenador
	 * 
	 * @param Pokemon    p
	 * @param Entrenador en
	 */

	public static void insertCapturaEquipo(Pokemon p, Entrenador en) {
		int movimiento1 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(0).getNombre());

		String insertEquipo = "INSERT INTO pokemon (num_pokedex	, id_equipo, id_entrenador, mote, sexo, nivel, "
				+ "vitalidad, ataque, ataque_especial, defensa, defensa_especial, velocidad, estamina, fertilidad,  "
				+ "movimiento1, movimiento2, movimiento3, movimiento4) VALUES(" + p.getNumPokedex() + "	, "
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ","
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ", " + " '" + p.getMote() + "', '" + p.getSexo()
				+ "'," + p.getNivel() + "," + p.getVitalidad() + "," + p.getAtaque() + "," + p.getAtaqueEspecial() + ","
				+ p.getDefensa() + "," + p.getDefensaEspecial() + ", " + p.getVelocidad() + "," + p.getEstamina() + ","
				+ p.getFertilidad() + "," + movimiento1 + ", null, null, null);";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(insertEquipo);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
