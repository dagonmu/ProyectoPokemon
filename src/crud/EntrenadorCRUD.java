package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Objeto;
import modelo.Pokemon;
import modelo.Tipo;

public class EntrenadorCRUD {
	/**
	 * Actuliza la base de datos
	 * Crea un entrenador, su caja, equipo y mochila
	 * @param String nombre
	 * @return String
	 */

	public static String insertEntrenador(String nombre) {
		String insert = "INSERT INTO entrenador (nombre, pokedollar) VALUES('" + nombre + "', 1000);";
		Statement statement = null;
		String mensaje = "Entrenador creado";
		try {
			statement = ConexionSQL.getConexion().createStatement();
			statement.executeUpdate(insert);
			insertCaja(selectIdEntrenador(nombre));
			insertEquipo(selectIdEntrenador(nombre));
			insertMochila(selectIdEntrenador(nombre));
			return mensaje;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;

	}
	/**
	 * Actuliza la base de datos
	 * Crea una caja
	 * @param int id
	 */

	public static void insertCaja(int id) {
		String insert = "INSERT INTO caja (id_caja, id_entrenador) VALUES (" + id + "," + id + ");";
		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(insert);

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
	 * Crea un equipo 
	 * @param id
	 */

	public static void insertEquipo(int id) {
		String insert = "INSERT INTO equipo (id_equipo, id_entrenador) VALUES (" + id + "," + id + ");";
		;
		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(insert);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * Extrae de la base de datos el id de un entrenador pasandole el nombre
 * @param String nombre
 * @return int
 */

	public static int selectIdEntrenador(String nombre) {
		String sqlString = "SELECT id_entrenador FROM entrenador WHERE nombre='" + nombre + "';";
		PreparedStatement preparedStatement = null;
		int idEntrenador = 1;
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				idEntrenador = rs.getInt("id_entrenador");
			}
			return idEntrenador;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idEntrenador;

	}
	/**
	 * Crea una LinkedList con el nombre de todos los entrenadores
	 * de la base de datos
	 * @return LinkedList<String>
	 */

	public static LinkedList<String> selectEntrenadores() {
		String sqlString = "SELECT nombre FROM entrenador;";
		PreparedStatement preparedStatement = null;
		LinkedList<String> entrenador = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				entrenador.add(nombre);

			}
			return entrenador;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entrenador;

	}
	/**
	 * Extrae de la base de datos una lista con todos los pokemon
	 * y sus caracteristicas de la caja de un entrenador
	 *  @param int id
	 * @return LinkedList<Pokemon>
	 */

	public static LinkedList<Pokemon> selectCajaEntrenador(int id) {
		String sqlString = "SELECT p.id_pokemon,po.nombre, p.num_pokedex,p.id_caja,p.id_entrenador,p.id_equipo,p.mote, "
				+ "p.sexo,p.nivel,p.vitalidad,p.ataque, p.ataque_especial,p.defensa, p.defensa_especial,p.velocidad, "
				+ "p.estamina,p.fertilidad,po.nivel_evolucion, p.movimiento1 ,p.movimiento2, p.movimiento3, p.movimiento4, "
				+ "p.estado,p.experiencia, (SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.tipo) AS tipo1, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.subtipo) AS tipo2, "
				+ "(SELECT o.nombre FROM objeto o WHERE o.id_objeto=p.objeto) AS objeto,po.img_front, po.img_back FROM pokemon p JOIN "
				+ "pokedex po ON p.num_pokedex=po.num_pokedex WHERE p.id_caja=" + id + ";";
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
				movimientos.add(PokemonCRUD.selectMovimientoPokemon(movimiento1));
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
	 * Extrae de la base de datos una lista con todos los pokemon
	 * y sus caracteristicas de equipo de un entrenador
	 *  @param int id
	 * @return LinkedList<Pokemon>
	 */

	public static LinkedList<Pokemon> selectEquipoEntrenador(int id) {
		String sqlString = "SELECT p.id_pokemon,po.nombre, p.num_pokedex,p.id_caja,p.id_entrenador,p.id_equipo,p.mote, "
				+ "p.sexo,p.nivel,p.vitalidad,p.ataque, p.ataque_especial,p.defensa, p.defensa_especial,p.velocidad, "
				+ "p.estamina,p.fertilidad,po.nivel_evolucion, p.movimiento1 ,p.movimiento2, p.movimiento3, p.movimiento4, "
				+ "p.estado,p.experiencia, (SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.tipo) AS tipo1, "
				+ "(SELECT t.nombre FROM tipo t WHERE t.id_tipo=po.subtipo) AS tipo2, "
				+ "(SELECT o.nombre FROM objeto o WHERE o.id_objeto=p.objeto) AS objeto,po.img_front, po.img_back FROM pokemon p JOIN "
				+ "pokedex po ON p.num_pokedex=po.num_pokedex WHERE p.id_equipo=" + id + ";";
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
				movimientos.add(PokemonCRUD.selectMovimientoPokemon(movimiento1));
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
	 * Extrae un entrenador y todas sus caracteristicas
	 * @param String nombre
	 * @return Entrenador
	 */

	public static Entrenador selectSacarEntrenador(String nombre) {
		String sqlString = "SELECT e.nombre, e.pokedollar, e.id_entrenador FROM entrenador e WHERE e.nombre='" + nombre
				+ "';";
		PreparedStatement preparedStatement = null;
		Entrenador entrenador = new Entrenador();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				entrenador.setNombre(rs.getString("nombre"));
				entrenador.setPokeDollar(rs.getInt("pokedollar"));
				entrenador.setCaja(selectCajaEntrenador(rs.getInt("id_entrenador")));
				entrenador.setEquipo(selectEquipoEntrenador(rs.getInt("id_entrenador")));
				entrenador.setMochila(selectMochilaEntrenador(rs.getInt("id_entrenador")));
			}
			return entrenador;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entrenador;

	}
	/**
	 * Extrae de la base de datos una lista con todos los objetos
	 * y sus caracteristicas de la caja de un entrenador
	 *  @param int id
	 * @return LinkedList<Objeto>
	 */

	public static LinkedList<Objeto> selectMochilaEntrenador(int id) {
		String sqlString = "SELECT o.nombre, o.precio_compra, o.precio_venta, o.aumento, o.disminucion, o.descripcion "
				+ "FROM objeto o JOIN mochila m ON o.id_objeto=m.id_objeto WHERE m.id_entrenador=" + id + ";";
		PreparedStatement preparedStatement = null;
		LinkedList<Objeto> mochila = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);
			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				int aumento = rs.getInt("aumento");
				int disminucion = rs.getInt("disminucion");
				int precioCompra = rs.getInt("precio_compra");
				int precioVenta = rs.getInt("precio_venta");
				String descripcion = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				Objeto o = new Objeto(nombre, aumento, disminucion, precioCompra, precioVenta,
						 descripcion);
				mochila.add(o);

			}
			return mochila;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mochila;

	}
	/**
	 * Actuliza la base de datos
	 * Crea una mochila
	 * @param int id
	 */

	public static void insertMochila(int id) {
		int anillo = ObjetoCRUD.selectIdObjeto("anillo unico");
		String insert = "INSERT INTO mochila (id_mochila, id_entrenador, id_objeto) VALUES ( " + id + "," + +id + ","
				+ anillo + ");";
		;
		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(insert);

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
	 * Inserta el pokemon criado
	 * @param Pokemon p
	 * @param Entrenador en
	 */

	public static void insertCrianza(Pokemon p, Entrenador en) {

		Integer movimiento1 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(0).getNombre());
		Integer movimiento2 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(1).getNombre());
		Integer movimiento3 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(2).getNombre());
		Integer movimiento4 = MovimientoCRUD.selectIdMovimiento(p.getMovimientos().get(3).getNombre());
		if (movimiento2 < 1 || movimiento2 > MovimientoCRUD.todosMovimientos().size()) {
			movimiento2 = null;
		}
		if (movimiento3 < 1 || movimiento3 > MovimientoCRUD.todosMovimientos().size()) {
			movimiento3 = null;
		}
		if (movimiento4 < 1 || movimiento4 > MovimientoCRUD.todosMovimientos().size()) {
			movimiento4 = null;
		}

		String insertCaja = "INSERT INTO pokemon (num_pokedex, id_caja, id_entrenador, mote, sexo, nivel, "
				+ "vitalidad, ataque, ataque_especial, defensa, defensa_especial, velocidad, estamina, fertilidad,  "
				+ "movimiento1, movimiento2, movimiento3, movimiento4) VALUES(" + p.getNumPokedex() + "	, "
				+ selectIdEntrenador(en.getNombre()) + "," + selectIdEntrenador(en.getNombre()) + ", " + " '"
				+ p.getMote() + "', '" + p.getSexo() + "'," + p.getNivel() + "," + p.getVitalidad() + ","
				+ p.getAtaque() + "," + p.getAtaqueEspecial() + "," + p.getDefensa() + "," + p.getDefensaEspecial()
				+ ", " + p.getVelocidad() + "," + p.getEstamina() + "," + p.getFertilidad() + "," + movimiento1 + ", "
				+ movimiento2 + ", " + movimiento3 + ", " + movimiento4 + ");";

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
	 * Actualiza los pokedoolars de un entrenador
	 * @param Entrenador en
	 */

	public static void actualizarPokedollar(Entrenador en) {
		String updatePokedollar = "UPDATE entrenador e SET e.pokedollar=("+en.getPokeDollar()+") "
				+ "WHERE e.id_entrenador=" + EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();
			statement.executeUpdate(updatePokedollar);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
