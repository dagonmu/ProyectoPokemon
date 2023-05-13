package modelo;

import java.util.ArrayList;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class TablaTipos {

	/**
	 * Devuelve un ArrayList<Tipo> que contiene las debilidades del Tipo pasado por
	 * parametro
	 * 
	 * @param tipoPokemon
	 * @return ArrayList Tipo
	 */
	private static ArrayList<Tipo> obtenerDebilidad(Tipo tipoPokemon) {

		ArrayList<Tipo> debilidad = new ArrayList<Tipo>();

		switch (tipoPokemon) {
		case AGUA:
			debilidad.add(Tipo.ELECTRICO);
			debilidad.add(Tipo.PLANTA);
			break;
		case BICHO:
			debilidad.add(Tipo.FUEGO);
			debilidad.add(Tipo.ROCA);
			debilidad.add(Tipo.VENENO);
			debilidad.add(Tipo.VOLADOR);
			break;
		case DRAGON:
			debilidad.add(Tipo.DRAGON);
			debilidad.add(Tipo.HIELO);
			break;
		case ELECTRICO:
			debilidad.add(Tipo.TIERRA);
			break;
		case FANTASMA:
			debilidad.add(Tipo.FANTASMA);
			break;
		case FUEGO:
			debilidad.add(Tipo.AGUA);
			debilidad.add(Tipo.ROCA);
			debilidad.add(Tipo.TIERRA);
			break;
		case HIELO:
			debilidad.add(Tipo.FUEGO);
			debilidad.add(Tipo.LUCHA);
			debilidad.add(Tipo.ROCA);
			break;
		case LUCHA:
			debilidad.add(Tipo.PSIQUICO);
			debilidad.add(Tipo.VOLADOR);
			break;
		case NORMAL:
			debilidad.add(Tipo.LUCHA);
			break;
		case PLANTA:
			debilidad.add(Tipo.FUEGO);
			debilidad.add(Tipo.BICHO);
			debilidad.add(Tipo.HIELO);
			debilidad.add(Tipo.VENENO);
			debilidad.add(Tipo.VOLADOR);
			break;
		case PSIQUICO:
			debilidad.add(Tipo.FANTASMA);
			debilidad.add(Tipo.BICHO);
			break;
		case ROCA:
			debilidad.add(Tipo.AGUA);
			debilidad.add(Tipo.PLANTA);
			debilidad.add(Tipo.TIERRA);
			debilidad.add(Tipo.LUCHA);
			break;
		case TIERRA:
			debilidad.add(Tipo.AGUA);
			debilidad.add(Tipo.HIELO);
			debilidad.add(Tipo.PLANTA);
			break;
		case VENENO:
			debilidad.add(Tipo.BICHO);
			debilidad.add(Tipo.TIERRA);
			debilidad.add(Tipo.PSIQUICO);
			break;
		case VOLADOR:
			debilidad.add(Tipo.ELECTRICO);
			debilidad.add(Tipo.HIELO);
			debilidad.add(Tipo.ROCA);
			break;
		}

		return debilidad;

	}

	/**
	 * Devuelve un ArrayList<Tipo> que contiene las fortalezas del Tipo pasado por
	 * parametro
	 * 
	 * @param tipoPokemon
	 * @return ArrayList Tipo
	 */
	private static ArrayList<Tipo> obtenerFortaleza(Tipo tipoPokemon) {

		ArrayList<Tipo> fortaleza = new ArrayList<Tipo>();

		switch (tipoPokemon) {
		case AGUA:
			fortaleza.add(Tipo.AGUA);
			fortaleza.add(Tipo.FUEGO);
			fortaleza.add(Tipo.HIELO);
			break;
		case BICHO:
			fortaleza.add(Tipo.LUCHA);
			fortaleza.add(Tipo.PLANTA);
			fortaleza.add(Tipo.TIERRA);
			break;
		case DRAGON:
			fortaleza.add(Tipo.AGUA);
			fortaleza.add(Tipo.ELECTRICO);
			fortaleza.add(Tipo.FUEGO);
			fortaleza.add(Tipo.PLANTA);
			break;
		case ELECTRICO:
			fortaleza.add(Tipo.ELECTRICO);
			fortaleza.add(Tipo.VOLADOR);
			break;
		case FANTASMA:
			fortaleza.add(Tipo.BICHO);
			fortaleza.add(Tipo.VENENO);
			break;
		case FUEGO:
			fortaleza.add(Tipo.BICHO);
			fortaleza.add(Tipo.FUEGO);
			fortaleza.add(Tipo.PLANTA);
			break;
		case HIELO:
			fortaleza.add(Tipo.HIELO);
			break;
		case LUCHA:
			fortaleza.add(Tipo.BICHO);
			fortaleza.add(Tipo.ROCA);
			break;
		case NORMAL:
			break;
		case PLANTA:
			fortaleza.add(Tipo.AGUA);
			fortaleza.add(Tipo.ELECTRICO);
			fortaleza.add(Tipo.PLANTA);
			fortaleza.add(Tipo.TIERRA);
			break;
		case PSIQUICO:
			fortaleza.add(Tipo.LUCHA);
			fortaleza.add(Tipo.PSIQUICO);
			break;
		case ROCA:
			fortaleza.add(Tipo.FUEGO);
			fortaleza.add(Tipo.NORMAL);
			fortaleza.add(Tipo.VENENO);
			fortaleza.add(Tipo.VOLADOR);
			break;
		case TIERRA:
			fortaleza.add(Tipo.ROCA);
			fortaleza.add(Tipo.VENENO);
			break;
		case VENENO:
			fortaleza.add(Tipo.LUCHA);
			fortaleza.add(Tipo.PLANTA);
			fortaleza.add(Tipo.VENENO);
			break;
		case VOLADOR:
			fortaleza.add(Tipo.BICHO);
			fortaleza.add(Tipo.LUCHA);
			fortaleza.add(Tipo.PLANTA);
			break;
		}

		return fortaleza;

	}

	/**
	 * Devuelve un ArrayList<Tipo> que contiene las inmunidades del Tipo pasado por
	 * parametro
	 * 
	 * @param tipoPokemon
	 * @return
	 */
	private static ArrayList<Tipo> obtenerInmunidad(Tipo tipoPokemon) {

		ArrayList<Tipo> inmunidad = new ArrayList<Tipo>();

		switch (tipoPokemon) {

		case AGUA:
			break;
		case BICHO:
			break;
		case DRAGON:
			break;
		case ELECTRICO:
			break;
		case FANTASMA:
			inmunidad.add(Tipo.NORMAL);
			inmunidad.add(Tipo.LUCHA);
			break;
		case FUEGO:
			break;
		case HIELO:
			break;
		case LUCHA:
			break;
		case NORMAL:
			inmunidad.add(Tipo.FANTASMA);
			break;
		case PLANTA:
			break;
		case PSIQUICO:
			break;
		case ROCA:
			break;
		case TIERRA:
			inmunidad.add(Tipo.ELECTRICO);
			break;
		case VENENO:
			break;
		case VOLADOR:
			inmunidad.add(Tipo.TIERRA);
			break;
		}

		return inmunidad;

	}

	/**
	 * Calcula el multiplicador de daño que tiene un Movimiento dependiendo de su
	 * Tipo con respecto a el/los Tipos del Pokemon afectado. Devuelve 0 si alguno
	 * de los Tipos del Pokemon es inmune al Tipo del Movimiento. Devuelve 0.5 si al
	 * menos uno de los dos posibles Tipos del Pokemon tiene fortaleza sobre el
	 * Movimiento y el otro (si lo tiene) no es efectivo. Devuelve 1 si ambos Tipos
	 * son neutros o uno tiene fortaleza y el otro es efectivo. Devuelve 2 si uno de
	 * los Tipos es efectivo y el otro neutro. Devuelve 4 si ambos Tipos son
	 * efectivos.
	 * 
	 * @param tipoMovimiento
	 * @param tipoPokemon
	 * @param tipoPokemonSecundario
	 * @return double
	 */
	public static double calcularEfectividad(Tipo tipoMovimiento, Tipo tipoPokemon, Tipo subTipoPokemon) {

		ArrayList<Tipo> debilidad = new ArrayList<Tipo>();
		ArrayList<Tipo> debilidadSecundaria = new ArrayList<Tipo>();
		ArrayList<Tipo> fortaleza = new ArrayList<Tipo>();
		ArrayList<Tipo> fortalezaSecundaria = new ArrayList<Tipo>();
		ArrayList<Tipo> inmunidad = new ArrayList<Tipo>();
		ArrayList<Tipo> inmunidadSecundaria = new ArrayList<Tipo>();

		debilidad = obtenerDebilidad(tipoPokemon);
		fortaleza = obtenerFortaleza(tipoPokemon);
		inmunidad = obtenerInmunidad(tipoPokemon);

		try {
			debilidadSecundaria = obtenerDebilidad(subTipoPokemon);
			fortalezaSecundaria = obtenerFortaleza(subTipoPokemon);
			inmunidadSecundaria = obtenerInmunidad(subTipoPokemon);
		} catch (NullPointerException e) {

		}

		if (inmunidad.contains(tipoMovimiento)) {
			return 0;
		} else if (inmunidadSecundaria.contains(tipoMovimiento)) {
			return 0;
		} else if (debilidad.contains(tipoMovimiento)) {
			if (debilidadSecundaria.contains(tipoMovimiento)) {
				return 4;
			} else if (fortalezaSecundaria.contains(tipoMovimiento)) {
				return 1;
			}
			return 2;
		} else if (fortaleza.contains(tipoMovimiento)) {
			if (debilidadSecundaria.contains(tipoMovimiento)) {
				return 1;
			}
			return 0.5;
		} else {
			return 1;
		}
	}

}
