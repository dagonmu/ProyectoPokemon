package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modelo.Movimiento;
import modelo.Objeto;
import modelo.Pokemon;
import modelo.Tipo;

public class PokemonCRUD {
	
	/**
	 * Extrae de la base de datos una lista con todos los pokemon
	 * y sus caracteristicas
	 * @return LinkedList<Pokemon>
	 */
	public static LinkedList<Pokemon> selectPokemon() {
		String sqlString = "SELECT p.id_pokemon,po.nombre, p.num_pokedex,p.id_caja,p.id_entrenador,p.id_equipo,p.mote, "
				+ "p.sexo,p.nivel,p.vitalidad,p.ataque, p.ataque_especial,p.defensa, p.defensa_especial,p.velocidad, "
				+ "p.estamina,p.fertilidad,po.nivel_evolucion, p.movimiento1 ,p.movimiento2,p.movimiento3,p.movimiento4,(SELECT e.nombre FROM estado e WHERE e.id_estado=p.estado) AS estado,p.experiencia, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.tipo) AS tipo1, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.subtipo) AS tipo2, "
				+ "(SELECT o.nombre FROM objeto o WHERE o.id_objeto=p.objeto) AS objeto, po.img_front, po.img_back "
				+ "FROM pokemon p JOIN pokedex po ON p.num_pokedex=po.num_pokedex;";
		PreparedStatement preparedStatement = null;
		LinkedList<Pokemon> lista = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				int idPokemon = rs.getInt("id_pokemon");
				int numPokedex = rs.getInt("num_pokedex");
				String nombre = rs.getString("nombre");
				String mote = rs.getString("mote");
				int nivel = rs.getInt("nivel");
				int experienciaObtenida = rs.getInt("experiencia");
				int vitalidad = rs.getInt("vitalidad");
				int vitalidadActual = vitalidad;
				int ataque = rs.getInt("ataque");
				int ataqueEspecial = rs.getInt("ataque_especial");
				int defensa = rs.getInt("defensa");
				int defensaEspecial = rs.getInt("defensa_especial");
				int velocidad = rs.getInt("velocidad");
				int estamina = rs.getInt("estamina");
				int estaminaActual = estamina;
				int fertilidad = rs.getInt("fertilidad");
				char sexo = rs.getString("sexo").charAt(0);
				int nivelEvolucion = rs.getInt("nivel_evolucion");
				String tipoPrimario = rs.getString("tipo1");
				String tipoSecundario = rs.getString("tipo2");
				String estado = rs.getString("estado");
				String nombreObjeto = rs.getString("objeto");
				Objeto objeto = ObjetoCRUD.selectUnObjeto(nombreObjeto);
				int movimiento1 = rs.getInt("movimiento1");
				int movimiento2 = rs.getInt("movimiento2");
				int movimiento3 = rs.getInt("movimiento3");
				int movimiento4 = rs.getInt("movimiento4");
				String img_front = rs.getString("img_front");
				String img_back = rs.getString("img_back");
				LinkedList<Movimiento> movimientos = new LinkedList<Movimiento>();
				movimientos.add(selectMovimientoPokemon(movimiento1));
				try {
					movimientos.add(selectMovimientoPokemon(movimiento2));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(selectMovimientoPokemon(movimiento3));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(selectMovimientoPokemon(movimiento4));
				} catch (NullPointerException e) {

				}
				if (tipoSecundario == null) {
					if (nombreObjeto == null) {
						Pokemon p = new Pokemon(idPokemon, numPokedex, nombre, mote, nivel, experienciaObtenida,
								vitalidad, vitalidadActual, ataque, ataqueEspecial, defensa, defensaEspecial, velocidad,
								estamina, estaminaActual, fertilidad, sexo, movimientos, Tipo.valueOf(tipoPrimario),
								null, nivelEvolucion, estado, null, img_front, img_back);
						lista.add(p);
					} else {
						Pokemon p = new Pokemon(idPokemon, numPokedex, nombre, mote, nivel, experienciaObtenida,
								vitalidad, vitalidadActual, ataque, ataqueEspecial, defensa, defensaEspecial, velocidad,
								estamina, estaminaActual, fertilidad, sexo, movimientos, Tipo.valueOf(tipoPrimario),
								null, nivelEvolucion, estado, objeto, img_front, img_back);
						lista.add(p);
					} 
				} else {
					if (nombreObjeto == null) {
						Pokemon p = new Pokemon(idPokemon, numPokedex, nombre, mote, nivel, experienciaObtenida,
								vitalidad, vitalidadActual, ataque, ataqueEspecial, defensa, defensaEspecial, velocidad,
								estamina, estaminaActual, fertilidad, sexo, movimientos, Tipo.valueOf(tipoPrimario),
								Tipo.valueOf(tipoSecundario), nivelEvolucion, estado, null, img_front, img_back);
						lista.add(p);
					} else {
						Pokemon p = new Pokemon(idPokemon, numPokedex, nombre, mote, nivel, experienciaObtenida,
								vitalidad, vitalidadActual, ataque, ataqueEspecial, defensa, defensaEspecial, velocidad,
								estamina, estaminaActual, fertilidad, sexo, movimientos, Tipo.valueOf(tipoPrimario),
								Tipo.valueOf(tipoSecundario), nivelEvolucion, estado, objeto, img_front, img_back);
						lista.add(p);
					}
				}

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
	 * Extrae un Movimiento y sus caracteristicas pasandole el id
	 * @param int idMovimiento
	 * @return Movimiento
	 */

	public static Movimiento selectMovimientoPokemon(int idMovimiento) {
		String sqlString = "SELECT m.nombre,(SELECT t.nombre FROM tipo t WHERE m.tipo=t.id_tipo) AS tipo, m.clase_movimiento,"
				+ " m.potencia, (SELECT e.nombre FROM estado e WHERE m.estado=e.id_estado) AS estado,"
				+ " m.probabilidad , m.duracion, m.mejora, m.categoria FROM movimiento m WHERE m.id_movimiento="
				+ idMovimiento + ";";
		PreparedStatement preparedStatement = null;
		Movimiento movimiento = new Movimiento();
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
				Movimiento movimiento2 = new Movimiento(nombre, tipoMovimiento, potencia, Tipo.valueOf(tipoAtaque),
						estado, probabilidad, numTurnos, mejora, categoria);
				movimiento = movimiento2;

			}
			return movimiento;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimiento;

	}
	/**
	 * Extrae de la base de datos una lista con todos los pokemon
	 * de la pokedex
	 * @return LinkedList<Pokemon>
	 */

	public static LinkedList<Pokemon> selectPokedex() {
		String sqlString = "SELECT po.nombre, po.num_pokedex ,po.nivel_evolucion, po.movimiento1 ,po.movimiento2,"
				+ "po.movimiento3,po.movimiento4, (SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.tipo) AS tipo1, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.subtipo) AS tipo2, po.img_front, po.img_back FROM pokedex po;";
		PreparedStatement preparedStatement = null;
		LinkedList<Pokemon> pokedex = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				int numPokedex = rs.getInt("num_pokedex");
				String nombre = rs.getString("nombre");
				int nivelEvolucion = rs.getInt("nivel_evolucion");
				String tipoPrimario = rs.getString("tipo1");
				String tipoSecundario = rs.getString("tipo2");
				int movimiento1 = rs.getInt("movimiento1");
				int movimiento2 = rs.getInt("movimiento2");
				int movimiento3 = rs.getInt("movimiento3");
				int movimiento4 = rs.getInt("movimiento4");
				String img_front = rs.getString("img_front");
				String img_back = rs.getString("img_back");
				LinkedList<Movimiento> movimientos = new LinkedList<Movimiento>();
				movimientos.add(selectMovimientoPokemon(movimiento1));
				try {
					movimientos.add(PokemonCRUD.selectMovimientoPokemon(movimiento2));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(PokemonCRUD.selectMovimientoPokemon(movimiento3));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(PokemonCRUD.selectMovimientoPokemon(movimiento4));
				} catch (NullPointerException e) {

				}
				if (tipoSecundario == null) {
					Pokemon p = new Pokemon(numPokedex, nombre, movimientos, Tipo.valueOf(tipoPrimario), null,
							nivelEvolucion, img_front, img_back);
					pokedex.add(p);
				} else {
					Pokemon p = new Pokemon(numPokedex, nombre, movimientos, Tipo.valueOf(tipoPrimario),
							Tipo.valueOf(tipoSecundario), nivelEvolucion, img_front, img_back);
					pokedex.add(p);
				}
			}
			return pokedex;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pokedex;

	}
	/**
	 * Extrae un Pokemon con todas sus caracteristicas
	 * @param Pokemon p
	 * @return Pokemon
	 */

	public static Pokemon selectUnPokemon(Pokemon p) {
		String sqlString = "SELECT p.id_pokemon,po.nombre, p.num_pokedex,p.id_caja,p.id_entrenador,p.id_equipo,p.mote, "
				+ "p.sexo,p.nivel,p.vitalidad,p.ataque, p.ataque_especial,p.defensa, p.defensa_especial,p.velocidad, "
				+ "p.estamina,p.fertilidad,po.nivel_evolucion, p.movimiento1 ,p.movimiento2,p.movimiento3,p.movimiento4, (SELECT e.nombre FROM estado e WHERE e.id_estado=p.estado) AS estado,p.experiencia, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.tipo) AS tipo1, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.subtipo) AS tipo2, "
				+ "(SELECT o.nombre FROM objeto o WHERE o.id_objeto=p.objeto) AS objeto, po.img_front, po.img_back "
				+ "FROM pokemon p JOIN pokedex po ON p.num_pokedex=po.num_pokedex WHERE p.id_pokemon="
				+ p.getIdPokemon() + ";";
		PreparedStatement preparedStatement = null;
		Pokemon pokemon = new Pokemon();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				int idPokemon = rs.getInt("id_pokemon");
				int numPokedex = rs.getInt("num_pokedex");
				String nombre = rs.getString("nombre");
				String mote = rs.getString("mote");
				int nivel = rs.getInt("nivel");
				int experienciaObtenida = rs.getInt("experiencia");
				int vitalidad = rs.getInt("vitalidad");
				int vitalidadActual = vitalidad;
				int ataque = rs.getInt("ataque");
				int ataqueEspecial = rs.getInt("ataque_especial");
				int defensa = rs.getInt("defensa");
				int defensaEspecial = rs.getInt("defensa_especial");
				int velocidad = rs.getInt("velocidad");
				int estamina = rs.getInt("estamina");
				int estaminaActual = estamina;
				int fertilidad = rs.getInt("fertilidad");
				char sexo = rs.getString("sexo").charAt(0);
				int nivelEvolucion = 0;
				try {
					nivelEvolucion = rs.getInt("nivel_evolucion");
				} catch (NullPointerException e) {

				}
				String tipoPrimario = rs.getString("tipo1");
				String tipoSecundario = rs.getString("tipo2");
				String estado = rs.getString("estado");
				String nombreObjeto = rs.getString("objeto");
				Objeto objeto = ObjetoCRUD.selectUnObjeto(nombreObjeto);
				int movimiento1 = rs.getInt("movimiento1");
				int movimiento2 = rs.getInt("movimiento2");
				int movimiento3 = rs.getInt("movimiento3");
				int movimiento4 = rs.getInt("movimiento4");
				String img_front = rs.getString("img_front");
				String img_back = rs.getString("img_back");
				LinkedList<Movimiento> movimientos = new LinkedList<Movimiento>();
				movimientos.add(selectMovimientoPokemon(movimiento1));
				try {
					movimientos.add(selectMovimientoPokemon(movimiento2));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(selectMovimientoPokemon(movimiento3));
				} catch (NullPointerException e) {

				}
				try {
					movimientos.add(selectMovimientoPokemon(movimiento4));
				} catch (NullPointerException e) {

				}
				pokemon.setIdPokemon(idPokemon);
				pokemon.setNumPokedex(numPokedex);
				pokemon.setNombre(nombre);
				pokemon.setMote(mote);
				pokemon.setNivel(nivel);
				pokemon.setExperienciaObtenida(experienciaObtenida);
				pokemon.setVitalidad(vitalidad);
				pokemon.setVitalidadActual(vitalidadActual);
				pokemon.setAtaqueEspecial(ataqueEspecial);
				pokemon.setAtaque(ataque);
				pokemon.setDefensa(defensa);
				pokemon.setDefensaEspecial(defensaEspecial);
				pokemon.setVelocidad(velocidad);
				pokemon.setEstamina(estamina);
				pokemon.setEstaminaActual(estaminaActual);
				pokemon.setFertilidad(fertilidad);
				pokemon.setSexo(sexo);
				pokemon.setEstado(estado);
				pokemon.setImgFrontal(img_front);
				pokemon.setImgTrasera(img_back);
				pokemon.setMovimientos(movimientos);
				pokemon.setNivelEvolucion(nivelEvolucion);
				pokemon.setTipoPrimario(Tipo.valueOf(tipoPrimario));
				if (tipoSecundario == null) {
					pokemon.setTipoSecundario(null);
				} else {
					pokemon.setTipoSecundario(Tipo.valueOf(tipoSecundario));
				}
				if (nombreObjeto == null) {
					pokemon.setObjeto(null);
				} else {
					pokemon.setObjeto(objeto);
				}
			}
			return pokemon;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pokemon;

	}
}
