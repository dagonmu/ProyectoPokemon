package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import crud.CombateCRUD;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 */
public class Combate {

	private Entrenador entrenador;
	private Entrenador entrenadorRival;
	private Pokemon pokemonEntrenador;
	private Pokemon pokemonEntrenadorRival;
	private Movimiento movimientoEntrenador;
	private Movimiento movimientoEntrenadorRival;
	private Turno turno;
	private LinkedList<Pokemon> pokemonEntrenadorKO;
	private LinkedList<Pokemon> pokemonEntrenadorRivalKO;
	private Entrenador ganador;

	/**
	 * Constructor por defecto
	 * 
	 */
	public Combate() {
		super();
		this.entrenador = new Entrenador();
		this.entrenadorRival = new Entrenador();
		this.pokemonEntrenador = null;
		this.pokemonEntrenadorRival = null;
		this.movimientoEntrenador = null;
		this.movimientoEntrenadorRival = null;
		this.turno = new Turno();
		this.pokemonEntrenadorKO = new LinkedList<Pokemon>();
		this.pokemonEntrenadorRivalKO = new LinkedList<Pokemon>();
		this.ganador = null;
	}

	/**
	 * Constructor completo.
	 * 
	 * @param entrenador
	 * @param entrenadorRival
	 * @param pokemonEntrenador
	 * @param pokemonEntrenadorRival
	 * @param movimientoEntrenador
	 * @param movimientoEntrenadorRival
	 * @param turno
	 * @param pokemonEntrenadorKO
	 * @param pokemonEntrenadorRivalKO
	 * @param ganador
	 */
	public Combate(Entrenador entrenador, Entrenador entrenadorRival, Pokemon pokemonEntrenador,
			Pokemon pokemonEntrenadorRival, Movimiento movimientoEntrenador, Movimiento movimientoEntrenadorRival,
			Turno turno, LinkedList<Pokemon> pokemonEntrenadorKO, LinkedList<Pokemon> pokemonEntrenadorRivalKO,
			Entrenador ganador) {
		super();
		this.entrenador = entrenador;
		this.entrenadorRival = entrenadorRival;
		this.pokemonEntrenador = pokemonEntrenador;
		this.pokemonEntrenadorRival = pokemonEntrenadorRival;
		this.movimientoEntrenador = movimientoEntrenador;
		this.movimientoEntrenadorRival = movimientoEntrenadorRival;
		this.turno = turno;
		this.pokemonEntrenadorKO = pokemonEntrenadorKO;
		this.pokemonEntrenadorRivalKO = pokemonEntrenadorRivalKO;
		this.ganador = ganador;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Entrenador getEntrenadorRival() {
		return entrenadorRival;
	}

	public void setEntrenadorRival(Entrenador entrenadorRival) {
		this.entrenadorRival = entrenadorRival;
	}

	public Pokemon getPokemonEntrenador() {
		return pokemonEntrenador;
	}

	public void setPokemonEntrenador(Pokemon pokemonEntrenador) {
		this.pokemonEntrenador = pokemonEntrenador;
	}

	public Pokemon getPokemonEntrenadorRival() {
		return pokemonEntrenadorRival;
	}

	public void setPokemonEntrenadorRival(Pokemon pokemonEntrenadorRival) {
		this.pokemonEntrenadorRival = pokemonEntrenadorRival;
	}

	public Movimiento getMovimientoEntrenador() {
		return movimientoEntrenador;
	}

	public void setMovimientoEntrenador(Movimiento movimientoEntrenador) {
		this.movimientoEntrenador = movimientoEntrenador;
	}

	public Movimiento getMovimientoEntrenadorRival() {
		return movimientoEntrenadorRival;
	}

	public void setMovimientoEntrenadorRival(Movimiento movimientoEntrenadorRival) {
		this.movimientoEntrenadorRival = movimientoEntrenadorRival;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public LinkedList<Pokemon> getPokemonEntrenadorKO() {
		return pokemonEntrenadorKO;
	}

	public void setPokemonEntrenadorKO(LinkedList<Pokemon> pokemonEntrenadorKO) {
		this.pokemonEntrenadorKO = pokemonEntrenadorKO;
	}

	public LinkedList<Pokemon> getPokemonEntrenadorRivalKO() {
		return pokemonEntrenadorRivalKO;
	}

	public void setPokemonEntrenadorRivalKO(LinkedList<Pokemon> pokemonEntrenadorRivalKO) {
		this.pokemonEntrenadorRivalKO = pokemonEntrenadorRivalKO;
	}

	public Entrenador getGanador() {
		return ganador;
	}

	public void setGanador(Entrenador ganador) {
		this.ganador = ganador;
	}

	public void elegirMovimientoEntrenador(int numMovimiento) {

		movimientoEntrenador = pokemonEntrenador.getMovimientos().get(numMovimiento);

	}

	/**
	 * Elige aleatoriamente el movimiento que va a realizar el Pokemon rival entre
	 * sus movimientos disponibles.
	 */
	public void elegirMovimientoEntrenadorRival() {

		do {
			movimientoEntrenadorRival = pokemonEntrenadorRival.getMovimientos()
					.get(((int) Math.floor(Math.random() * (0 - pokemonEntrenadorRival.getMovimientos().size())
							+ pokemonEntrenadorRival.getMovimientos().size())));
		} while (movimientoEntrenadorRival.getTipoAtaque() == null);
	}

	/**
	 * Busca en el equipo del Entrenador el Pokemon que esta en combate y aumenta su
	 * experiencia en relacion al pokemon del entrenador rival.
	 * 
	 * @param e
	 */
	public void obtenerExperiencia(Entrenador e) {

		for (int i = 0; i < e.getEquipo().size(); i++) {
			int expUp;
			int expTotal;
			if (e.getEquipo().get(i).getIdPokemon() == pokemonEntrenador.getIdPokemon()) {
				expUp = (pokemonEntrenador.getNivel() + pokemonEntrenadorRival.getNivel() * 10) / 4;
				expTotal = e.getEquipo().get(i).getExperienciaObtenida() + expUp;

				e.getEquipo().get(i).setExperienciaObtenida(expTotal);
				logs((e.getEquipo().get(i).getNombre() + " obtuvo: " + expUp + " puntos de experiencia."));
				CombateCRUD.updateExperienciaPokemon(e.getEquipo().get(i));
			}
		}
	}

	/**
	 * Comprueba quien ha sido el ganador del combate.
	 * 
	 * @return boolean
	 */
	public boolean comprobarGanador() {
		try {
			if (ganador.equals(entrenador)) {

				return true;
			} else {

				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Calcula la cantidad de Pokedollars que va a ganar o perder el Entrenador en
	 * relacion a quien sea el ganador del combate.
	 * 
	 * @return int
	 */
	public int calcularGananciaPerdida() {
		if (comprobarGanador()) {
			return entrenadorRival.getPokeDollar() / 3;
		} else {
			return entrenador.getPokeDollar() / 3;
		}
	}

	/**
	 * Modifica los Pokedollar en relacion a quien haya sido el ganador del combate.
	 * 
	 * @param e
	 */
	public void entregarPokedollars(Entrenador e) {

		if (comprobarGanador()) {

			logs((e.getNombre() + " obtuvo: " + calcularGananciaPerdida() + " pokedollars."));
			logs((ganador.getNombre() + " es el ganador del combate."));

			e.setPokeDollar(e.getPokeDollar() + (calcularGananciaPerdida()));
			entrenadorRival.setPokeDollar((entrenadorRival.getPokeDollar() / 3) * 2);

		} else {

			logs((e.getNombre() + " perdió: " + calcularGananciaPerdida() + " pokedollars."));
			logs((ganador.getNombre() + " (Rival) es el ganador del combate."));

			entrenadorRival.setPokeDollar(entrenadorRival.getPokeDollar() + (calcularGananciaPerdida()));
			e.setPokeDollar((e.getPokeDollar() / 3) * 2);

		}
	}

	/**
	 * Concede la victoria al entrenador rival.
	 * 
	 */
	public void abandonarCombate() {
		logs(("El jugador se retiró del combate."));
		ganador = entrenadorRival;

	}

	/**
	 * Comprueba si el pokemon del entrenador rival ha quedado KO y lo agrega a su
	 * lista de pokemons debilitados. Devuelve true si la vitalidad actual del
	 * Pokemon es menor o igual a 0. En caso contrario devuelve false.
	 * 
	 * @return boolean
	 */
	public boolean pokemonRivalKO() {

		if (pokemonEntrenadorRival.getVitalidadActual() <= 0) {
			pokemonEntrenadorRivalKO.add(pokemonEntrenadorRival);
			logs(("El pokemon: " + pokemonEntrenadorRival.getNombre() + " del rival, fué debilitado"));
			return true;
		}
		return false;
	}

	/**
	 * Cambia el Pokemon del entrenador rival al siguiente de su lista.
	 * 
	 */
	public void pokemonRivalCambio() {

		if (entrenadorRival.getEquipo().size() > pokemonEntrenadorRivalKO.size()) {
			pokemonEntrenadorRival = entrenadorRival.getEquipo().get(pokemonEntrenadorRivalKO.size());
			logs(("El rival sacó a: " + pokemonEntrenadorRival.getNombre()));
		}
	}

	/**
	 * Comprueba si el pokemon del entrenador ha quedado KO y lo agrega a su lista
	 * de pokemons debilitados. Devuelve true si la vitalidad actual del Pokemon es
	 * menor o igual a 0. En caso contrario devuelve false.
	 * 
	 * @return boolean
	 */
	public boolean pokemonPropioKO() {

		if (pokemonEntrenador.getVitalidadActual() <= 0) {
			pokemonEntrenadorKO.add(pokemonEntrenador);
			logs(("El pokemon: " + pokemonEntrenador.getNombre() + " del jugador, fué debilitado."));
			return true;

		} else {
			return false;
		}

	}

	/**
	 * Comprueba si todos los pokemons de alguno de los entrenadores han sido
	 * debilitados. En cuyo caso, modifica el valor del ganador y concede la
	 * victoria al entrenador contrario. Devuelve true en caso de que alguno de los
	 * 2 entrenadores haya perdido a todos sus Pokemon. En caso contrario devuelve
	 * false.
	 * 
	 * @return boolean
	 */
	public boolean comprobarKO() {

		if (pokemonEntrenadorKO.size() >= entrenador.getEquipo().size()) {
			ganador = entrenadorRival;
			return true;
		} else if (pokemonEntrenadorRivalKO.size() >= entrenadorRival.getEquipo().size()) {
			ganador = entrenador;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Ejecuta el movimiento del entrenador Rival.
	 * 
	 */
	public void ataqueRival() {
		movimientoEntrenadorRival.calcularMovimiento(movimientoEntrenadorRival, pokemonEntrenadorRival,
				pokemonEntrenador);
		logs(("El pokemon rival: " + pokemonEntrenadorRival.getNombre() + " usó: "
				+ movimientoEntrenadorRival.getNombre()));
	}

	/**
	 * Ejecuta el movimiento del Jugador.
	 * 
	 */
	public void ataquePropio() {
		movimientoEntrenador.calcularMovimiento(movimientoEntrenador, pokemonEntrenador, pokemonEntrenadorRival);
		logs(("El pokemon: " + pokemonEntrenador.getNombre() + " del jugador usó: "
				+ movimientoEntrenador.getNombre()));
	}

	/**
	 * Comprueba si el Pokemon del Entrenador tiene estamina actual suficiente para
	 * realizar un movimiento. Devuelve true si tiene suficiente o false en caso
	 * contrario.
	 * 
	 * @return boolean
	 */
	public boolean comprobarEstamina() {
		if (pokemonEntrenador.getEstaminaActual() > movimientoEntrenador.calcularConsumo(movimientoEntrenador,
				pokemonEntrenador)) {
			return true;
		} else {
			logs(("El pokemon: " + pokemonEntrenador.getNombre()
					+ " del jugador no tiene suficiente estamina para usar: " + movimientoEntrenador.getNombre()));
			return false;
		}
	}

	/**
	 * Comprueba si el Pokemon del Entrenador rival tiene estamina actual suficiente
	 * para realizar un movimiento. Devuelve true si tiene suficiente o false en
	 * caso contrario.
	 * 
	 * @return
	 */
	public boolean comprobarEstaminaRival() {
		if (movimientoEntrenadorRival == null) {
			return true;
		} else if (pokemonEntrenadorRival.getEstaminaActual() > movimientoEntrenadorRival
				.calcularConsumo(movimientoEntrenadorRival, pokemonEntrenadorRival)) {
			return true;
		} else {
			logs(("El pokemon: " + pokemonEntrenadorRival.getNombre()
					+ " del rival no tiene suficiente estamina para usar: " + movimientoEntrenadorRival.getNombre()));
			return false;
		}
	}

	/**
	 * Comprueba la velocidad de ambos Pokemon en combate. Devuelve true si el valor
	 * de velocidad del Pokemon del Entrenador es mayor o igual a la del Rival. En
	 * caso contrario devuelve false.
	 * 
	 * @return boolean
	 */
	public boolean comprobarVelocidad() {
		if (pokemonEntrenador.getVelocidad() >= pokemonEntrenadorRival.getVelocidad()) {
			logs(("El pokemon del jugador es mas rapido y ataca primero."));
			return true;
		} else {
			logs(("El pokemon del jugador es mas lento y ataca en segundo lugar."));
			return false;
		}
	}

	/**
	 * Finaliza el turno. Modifica los valores de los movimientos utilizados en
	 * dicho turno a null y comprueba si alguno de los entrenadores ha perdido todos
	 * sus Pokemon.
	 * 
	 * @return boolean
	 */
	public boolean finalTurno() {
		logs(("Final del turno: " + turno.getNumTurno()));
		movimientoEntrenador = null;
		movimientoEntrenadorRival = null;

		return comprobarKO();
	}

	public static void logs(String write) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("logs/combateLogs.txt", true));

			bw.write(write);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
