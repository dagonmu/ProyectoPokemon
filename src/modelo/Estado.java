package modelo;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Estado {

	/**
	 * Devuelve true o false en base a un porcentaje pasado por parametro. Si el
	 * porcentaje es mayor o igual que el numero generado aleatoriamente, true. En
	 * el caso contrario, false.
	 * 
	 * @param porcentaje
	 * @return boolean
	 */
	private static boolean probabilidad(int porcentaje) {

		int numeroAleatorio = (int) Math.floor(Math.random() * (1 - 100) + 100);
		if (porcentaje >= numeroAleatorio) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Devuelve un String del estado paralizado si la probabilidad es favorable. En
	 * caso contrario devuelve null.
	 * 
	 * @param probabilidad
	 * @return String
	 */
	private static String paralizar(int probabilidad) {

		if (Estado.probabilidad(probabilidad)) {
			return "paralizado";
		} else {
			return null;
		}

	}

	/**
	 * Devuelve un String del estado envenado si la probabilidad es favorable. En
	 * caso contrario devuelve null.
	 * 
	 * @param probabilidad
	 * @return String
	 */
	private static String envenenar(int probabilidad) {

		if (Estado.probabilidad(probabilidad)) {
			return "envenenado";
		} else {
			return null;
		}

	}

	/**
	 * Devuelve un String del estado envenenado grave si la probabilidad es
	 * favorable. En caso contrario devuelve null.
	 * 
	 * @param probabilidad
	 * @return String
	 */
	private static String envenenarGrave(int probabilidad) {

		if (Estado.probabilidad(probabilidad)) {
			return "envenenado grave";
		} else {
			return null;
		}

	}

	/**
	 * Devuelve un String del estado dormido si la probabilidad es favorable. En
	 * caso contrario devuelve null.
	 * 
	 * @param probabilidad
	 * @return String
	 */
	private static String dormir(int probabilidad) {

		if (Estado.probabilidad(probabilidad)) {
			return "dormido";
		} else {
			return null;
		}

	}

	/**
	 * Devuelve un String del estado confundido si la probabilidad es favorable. En
	 * caso contrario devuelve null.
	 * 
	 * @param probabilidad
	 * @return String
	 */
	private static String confundir(int probabilidad) {

		if (Estado.probabilidad(probabilidad)) {
			return "confundido";
		} else {
			return null;
		}

	}

	/**
	 * Comprueba el estado que genera un Movimiento y en base a su probabilidad
	 * ,devuelve o no, dicho estado.
	 * 
	 * @param m
	 * @param probabilidad
	 * @return boolean
	 */
	public static String realizarAlteracion(Movimiento m, int probabilidad) {

		switch (m.getEstado()) {

		case "paralizado":
			return Estado.paralizar(probabilidad);

		case "envenenado":
			return Estado.envenenar(probabilidad);

		case "envenenado grave":
			return Estado.envenenarGrave(probabilidad);

		case "dormido":
			return Estado.dormir(probabilidad);

		case "confundido":
			return Estado.confundir(probabilidad);

		default:
			return null;

		}

	}

	/**
	 * Comprueba si un Pokemon esta bajo los efectos de un estado que tenga efecto
	 * antes de que el Pokemon ejecute una accion. Si lo esta, comprueba si el
	 * estado surje efecto y lo aplica o no.
	 * 
	 * @param p
	 * @return boolean
	 */
	public static boolean estadoPreAccion(Pokemon p) {

		switch (p.getEstado()) {

		case "paralizado":
			if (Estado.probabilidad(40)) {

				return true;
			} else {
				return false;
			}

		case "dormido":
			if (Estado.probabilidad(50)) {

				return true;
			} else {

				return false;
			}

		case "confundido":
			if (Estado.probabilidad(33)) {
				p.setVitalidadActual(p.getVitalidadActual() - (p.getVitalidad() / 8));

				return true;
			} else {
				return false;
			}

		default:
			return false;

		}

	}

	/**
	 * Comprueba si un Pokemon esta bajo los efectos de un estado que tenga efecto
	 * despues de que el Pokemon ejecute una accion. Si lo esta, aplica dicho
	 * efecto.
	 * 
	 * @param p
	 * @return boolean
	 */
	public static boolean estadoPostAccion(Pokemon p) {

		switch (p.getEstado()) {

		case "envenenado":
			p.setVitalidadActual(p.getVitalidadActual() - (p.getVitalidad() / 8));
			// Pierde 1/8 de sus ps totales
			return true;

		case "envenenado grave":

			p.setVitalidadActual(p.getVitalidadActual() - ((p.getVitalidad() / 8) + (p.getVitalidad() / 16)));
			return true;

		default:
			return false;

		}

	}

}
