package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Entrenador;
import modelo.Objeto;
import modelo.Pokemon;
import modelo.Tipo;

public class ObjetoCRUD {
	/**
	 * Extrae el id del Objeto pasandole el nombre
	 * @param String nombre
	 * @return int
	 */
	public static int selectIdObjeto(String nombre) {
		String sqlString = "SELECT id_objeto FROM objeto WHERE nombre='" + nombre + "';";
		PreparedStatement preparedStatement = null;
		int idObjeto = 0;
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				idObjeto = rs.getInt("id_objeto");
			}
			return idObjeto;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idObjeto;

	}
	/**
	 * Extrae una LinkedList<Objeto> de todos los objetos de la base de datos
	 * @return LinkedList<Objeto>
	 */

	public static LinkedList<Objeto> todosObjetos() {
		String sqlString = "SELECT o.nombre, o.aumento, o.disminucion, o.precio_compra, o.precio_venta, o.descripcion FROM objeto o WHERE o.nombre != 'anillo unico';";
		PreparedStatement preparedStatement = null;
		LinkedList<Objeto> lista = new LinkedList<>();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				int aumento = rs.getInt("aumento");
				int disminucion = rs.getInt("disminucion");
				int precioCompra = rs.getInt("precio_compra");
				int precioVenta = rs.getInt("precio_venta");
				String descripcion = rs.getString("descripcion");
				Objeto objeto = new Objeto(nombre, aumento, disminucion, precioCompra, precioVenta,
						 descripcion);
				lista.add(objeto);
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
	 * Extrae un objeto pasandole el nombre del objeto
	 * @param String nombreObjeto
	 * @return Objeto
	 */

	public static Objeto selectUnObjeto(String nombreObjeto) {
		String sqlString = "SELECT o.nombre, o.aumento, o.disminucion, o.precio_compra, o.precio_venta, o.descripcion FROM objeto o WHERE o.nombre = '"
				+ nombreObjeto + "';";
		PreparedStatement preparedStatement = null;
		Objeto o = new Objeto();
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				int aumento = rs.getInt("aumento");
				int disminucion = rs.getInt("disminucion");
				int precioCompra = rs.getInt("precio_compra");
				int precioVenta = rs.getInt("precio_venta");
				String descripcion = rs.getString("descripcion");
				Objeto objeto = new Objeto(nombre, aumento, disminucion, precioCompra, precioVenta, descripcion);
				o = objeto;
			}
			return o;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;

	}
	/**
	 * Actualiza la base de datos
	 * Modifica los pokedollar del entrenador cuando compra un objeto
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void comprarObjetoPokedollar(Entrenador en, Objeto o) {
		String updatePokedollar = "UPDATE entrenador e SET e.pokedollar=(e.pokedollar-" + o.getPrecioCompra() + ") "
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
	/**
	 * Actualiza la base de datos
	 * Modifica los pokedollar del entrenador cuando vende un objeto
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void venderObjetoPokedollar(Entrenador en, Objeto o) {
		String updatePokedollar = "UPDATE entrenador e SET e.pokedollar=(e.pokedollar+" + o.getPrecioVenta() + ") "
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
	/**
	 * Extrae la cantidad de objetos de un tipo que tiene un Entrenador en su mochila
	 * @param Entrenador en
	 * @param Objeto o
	 * @return int
	 */

	public static int selectCantidadObjeto(Entrenador en, Objeto o) {
		String sqlString = "SELECT m.cantidad FROM mochila m WHERE m.id_entrenador="
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + " " + "AND m.id_objeto="
				+ selectIdObjeto(o.getNombre()) + ";";
		PreparedStatement preparedStatement = null;
		int cantidad = 0;
		try {
			preparedStatement = ConexionSQL.getConexion().prepareStatement(sqlString);

			ResultSet rs = preparedStatement.executeQuery(sqlString);
			while (rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			return cantidad;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cantidad;

	}
	/**
	 * Actualiza la base de datos
	 * Si el entrenador tiene algun objeto del que quieres agregar, modifica la cantidad y añade uno, 
	 * si no tiene ninguno hace un insert y añade el objeto
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void agregarObjetoMochila(Entrenador en, Objeto o) {
		int cantidad = selectCantidadObjeto(en, o);
		String insertObjeto = "INSERT INTO mochila (id_entrenador, id_objeto, cantidad) " + "VALUES ("
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ", " + selectIdObjeto(o.getNombre()) + ", "
				+ (cantidad + 1) + ");";
		String updateMochila = "UPDATE mochila SET cantidad=" + (cantidad + 1) + " WHERE id_objeto="
				+ selectIdObjeto(o.getNombre()) + " AND " + "id_entrenador="
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ";";

		Statement statement = null;
		if (cantidad == 0) {
			try {
				statement = ConexionSQL.getConexion().createStatement();

				statement.executeUpdate(insertObjeto);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				statement = ConexionSQL.getConexion().createStatement();

				statement.executeUpdate(updateMochila);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * Actualiza la base de datos
	 * Si el entrenador tiene mas de un objeto del que quieres quitar, modifica la cantidad y quita uno, 
	 * si tiene uno hace un delete y quita el objeto
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void quitarObjetoMochila(Entrenador en, Objeto o) {
		int cantidad = selectCantidadObjeto(en, o);
		String deleteObjeto = "DELETE FROM mochila WHERE id_entrenador="
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + " " + "AND id_objeto="
				+ selectIdObjeto(o.getNombre()) + ";";
		String updateMochila = "UPDATE mochila SET cantidad=" + (cantidad - 1) + " WHERE id_objeto="
				+ selectIdObjeto(o.getNombre()) + " AND " + "id_entrenador="
				+ EntrenadorCRUD.selectIdEntrenador(en.getNombre()) + ";";

		Statement statement = null;
		if (cantidad == 1) {
			try {
				statement = ConexionSQL.getConexion().createStatement();

				statement.executeUpdate(deleteObjeto);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				statement = ConexionSQL.getConexion().createStatement();

				statement.executeUpdate(updateMochila);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * Actualiza la base de datos
	 * Actualiza el pokemon y le añade el objeto que se le ha equipado
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void equiparObjetoPokemon(Pokemon p, Objeto o, Entrenador en) {
		String updatePokedollar = "UPDATE pokemon p SET p.objeto=" + selectIdObjeto(o.getNombre()) + " "
				+ "WHERE p.id_pokemon=" + p.getIdPokemon() + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(updatePokedollar);
			quitarObjetoMochila(en, o);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Actualiza la base de datos
	 * Actualiza el pokemon y le quita el objeto que se le ha quitado
	 * @param Entrenador en
	 * @param Objeto o
	 */

	public static void quitarObjetoPokemon(Pokemon p, Objeto o, Entrenador en) {
		String updatePokedollar = "UPDATE pokemon p SET p.objeto=NULL WHERE p.id_pokemon=" + p.getIdPokemon() + ";";

		Statement statement = null;
		try {
			statement = ConexionSQL.getConexion().createStatement();

			statement.executeUpdate(updatePokedollar);
			agregarObjetoMochila(en, o);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
