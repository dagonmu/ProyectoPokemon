package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import crud.ConexionSQL;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Entrenador {
	private LinkedList<Pokemon> equipo = new LinkedList<Pokemon>();
	private LinkedList<Pokemon> caja = new LinkedList<Pokemon>();
	private String nombre;
	private int pokeDollar;
	private LinkedList<Objeto> mochila = new LinkedList<Objeto>();

	/**
	 * Constructor con todos los parametros
	 * 
	 * @param equipo
	 * @param caja
	 * @param nombre
	 * @param pokeDollar
	 * @param mochila
	 */

	public Entrenador(LinkedList<Pokemon> equipo, LinkedList<Pokemon> caja, String nombre, int pokeDollar,
			LinkedList<Objeto> mochila) {
		super();
		this.equipo = equipo;
		this.caja = caja;
		this.nombre = nombre;
		this.pokeDollar = pokeDollar;
		this.mochila = mochila;
	}

	/**
	 * Constructor por defecto
	 */

	public Entrenador() {
		super();
		this.equipo = new LinkedList<Pokemon>();
		this.caja = new LinkedList<Pokemon>();
		this.nombre = "";
		this.pokeDollar = 0;
		this.mochila = new LinkedList<Objeto>();
	}

	public LinkedList<Pokemon> getEquipo() {
		return equipo;
	}

	public void setEquipo(LinkedList<Pokemon> equipo) {
		this.equipo = equipo;
	}

	public LinkedList<Pokemon> getCaja() {
		return caja;
	}

	public void setCaja(LinkedList<Pokemon> caja) {
		this.caja = caja;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPokeDollar() {
		return pokeDollar;
	}

	public void setPokeDollar(int pokeDollar) {
		this.pokeDollar = pokeDollar;
	}

	public LinkedList<Objeto> getMochila() {
		return mochila;
	}

	public void setMochila(LinkedList<Objeto> mochila) {
		this.mochila = mochila;
	}

	/**
	 * Calcula los pokedollar que cuesta el entrenamiento y los resta al entrenador
	 * y sube 5 puntos la defensa, defensa especial y la vitalidad del pokemon que
	 * le pasas
	 * 
	 * @param Pokemon p
	 */

	public void entrenamientoPesado(Pokemon p) {
		pokeDollar -= (20 * p.getNivel());
		p.setDefensa(p.getDefensa() + 5);
		p.setDefensaEspecial(p.getDefensaEspecial() + 5);
		p.setVitalidad(p.getVitalidad() + 5);
	}

	/**
	 * Calcula los pokedollar que cuesta el entrenamiento y los resta al entrenador
	 * y sube 5 puntos la ataque, ataque especial y la velocidad del pokemon que le
	 * pasas
	 * 
	 * @param Pokemon p
	 */

	public void entrenamientoFurioso(Pokemon p) {
		pokeDollar -= (30 * p.getNivel());
		p.setAtaque(p.getAtaque() + 5);
		p.setAtaqueEspecial(p.getAtaqueEspecial() + 5);
		p.setVelocidad(p.getVelocidad() + 5);
	}

	/**
	 * Calcula los pokedollar que cuesta el entrenamiento y los resta al entrenador
	 * y sube 5 puntos la defensa, ataque, la velocidad y la vitalidad del pokemon
	 * que le pasas
	 * 
	 * @param Pokemon p
	 */

	public void entrenamientoFuncional(Pokemon p) {
		pokeDollar -= (40 * p.getNivel());
		p.setDefensa(p.getDefensa() + 5);
		p.setAtaque(p.getAtaque() + 5);
		p.setVelocidad(p.getVelocidad() + 5);
		p.setVitalidad(p.getVitalidad() + 5);
	}

	/**
	 * Calcula los pokedollar que cuesta el entrenamiento y los resta al entrenador
	 * y sube 5 puntos el ataque especial, defensa especial, la velocidad y la
	 * vitalidad del pokemon que le pasas
	 * 
	 * @param Pokemon p
	 */

	public void entrenamientoOnirico(Pokemon p) {
		pokeDollar -= (40 * p.getNivel());
		p.setDefensaEspecial(p.getDefensaEspecial() + 5);
		p.setAtaqueEspecial(p.getAtaqueEspecial() + 5);
		p.setVelocidad(p.getVelocidad() + 5);
		p.setVitalidad(p.getVitalidad() + 5);
	}

	/**
	 * Le pasas 2 pokemon y crea un pokemon con el nombre, numPokedex,tiposy
	 * imagenes de uno de los padres y coge las mejores estadisticas de cada uno
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return Pokemon
	 */

	public Pokemon criar(Pokemon p, Pokemon a) {
		Pokemon hijo = new Pokemon();
		pokeDollar -= (((p.getNivel() + a.getNivel()) / 2) * 100);

		if ((int) Math.floor(Math.random() * (1 - 3) + 3) == 1) {
			hijo.setNumPokedex(p.getNumPokedex());
			hijo.setNombre(p.getNombre());
			hijo.setImgFrontal(p.getImgFrontal());
			hijo.setImgTrasera(p.getImgTrasera());
			hijo.setTipoPrimario(p.getTipoPrimario());
			hijo.setTipoSecundario(p.getTipoSecundario());
		} else {
			hijo.setNumPokedex(a.getNumPokedex());
			hijo.setNombre(a.getNombre());
			hijo.setImgFrontal(a.getImgFrontal());
			hijo.setImgTrasera(a.getImgTrasera());
			hijo.setTipoPrimario(a.getTipoPrimario());
			hijo.setTipoSecundario(a.getTipoSecundario());
		}

		hijo.setMote(crearMote(p, a));
		hijo.setMovimientos(crearMovimiento(p, a));
		hijo.setAtaque(crearAtaque(p, a));
		hijo.setAtaqueEspecial(crearAtaqueEspecial(p, a));
		hijo.setDefensa(crearDefensa(p, a));
		hijo.setDefensaEspecial(crearDefensaEspecial(p, a));
		hijo.setVelocidad(crearVelocidad(p, a));
		hijo.setEstamina(crearEstamina(p, a));
		hijo.setVitalidad(crearVitalidad(p, a));
		hijo.setFertilidad(5);
		hijo.setSexo(crearSexo());
		hijo.setNivel(1);
		p.setFertilidad(p.getFertilidad() - 1);
		a.setFertilidad(a.getFertilidad() - 1);
		return hijo;

	}

	/**
	 * Crea el mote en base a el mote de los motes de los pokemon que le pasas
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return String
	 */

	private String crearMote(Pokemon p, Pokemon a) {
		String mote = (p.getNombre().substring(0, p.getNombre().length() / 2))
				+ (a.getNombre().substring(0, a.getNombre().length() / 2));
		return mote;
	}

	/**
	 * Crea una LinkedList de Movimiento con los 2 primeros movimientos del padre y
	 * los 2 primeros de la madre
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return LinkedList<Movimiento>
	 */

	private LinkedList<Movimiento> crearMovimiento(Pokemon p, Pokemon a) {
		LinkedList<Movimiento> hijo = new LinkedList<Movimiento>();
		try {
			hijo.add(p.getMovimientos().get(0));
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			hijo.add(a.getMovimientos().get(1));
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			hijo.add(p.getMovimientos().get(2));
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			hijo.add(a.getMovimientos().get(3));
		} catch (IndexOutOfBoundsException e) {

		}

		return hijo;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas ataque y la devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearAtaque(Pokemon p, Pokemon a) {
		int ataque;
		if (p.getAtaque() >= a.getAtaque()) {
			ataque = p.getAtaque();
		} else {
			ataque = a.getAtaque();
		}
		return ataque;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas ataque especial y la
	 * devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearAtaqueEspecial(Pokemon p, Pokemon a) {
		int ataqueEspecial;
		if (p.getAtaqueEspecial() >= a.getAtaqueEspecial()) {
			ataqueEspecial = p.getAtaqueEspecial();
		} else {
			ataqueEspecial = a.getAtaqueEspecial();
		}
		return ataqueEspecial;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas defensa y la devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearDefensa(Pokemon p, Pokemon a) {
		int defensa;
		if (p.getDefensa() >= a.getDefensa()) {
			defensa = p.getDefensa();
		} else {
			defensa = a.getDefensa();
		}
		return defensa;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas defensa especial y la
	 * devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearDefensaEspecial(Pokemon p, Pokemon a) {
		int defensaEspecial;
		if (p.getDefensaEspecial() >= a.getDefensaEspecial()) {
			defensaEspecial = p.getDefensaEspecial();
		} else {
			defensaEspecial = a.getDefensaEspecial();
		}
		return defensaEspecial;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas velocidad y la devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearVelocidad(Pokemon p, Pokemon a) {
		int velocidad;
		if (p.getVelocidad() >= a.getVelocidad()) {
			velocidad = p.getVelocidad();
		} else {
			velocidad = a.getVelocidad();
		}
		return velocidad;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas estamina y la devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearEstamina(Pokemon p, Pokemon a) {
		int estamina;
		if (p.getEstamina() >= a.getEstamina()) {
			estamina = p.getEstamina();
		} else {
			estamina = a.getEstamina();
		}
		return estamina;
	}

	/**
	 * Coge las mejores estadisticas del Pokemon con mas vitalidad y la devuelve
	 * 
	 * @param Pokemon p
	 * @param Pokemon a
	 * @return int
	 */

	private int crearVitalidad(Pokemon p, Pokemon a) {
		int vitalidad;
		if (p.getVitalidad() >= a.getVitalidad()) {
			vitalidad = p.getVitalidad();
		} else {
			vitalidad = a.getVitalidad();
		}
		return vitalidad;
	}

	/**
	 * Crea aleatoriamente sexo M o H
	 * 
	 * @return char
	 */

	private char crearSexo() {
		int condicion = (int) (Math.random() * 2 + 1);
		char sexo;
		if (condicion == 1) {
			sexo = 'M';
		} else {
			sexo = 'H';
		}
		return sexo;
	}

	/**
	 * Calcula aleatoriamente la posibilidad de capturar un Pokemon, tiene 1/3 de
	 * posibilidades
	 * 
	 * @return boolean
	 */

	public boolean lanzarPokeball() {
		int numeroAleatorio = (int) (Math.random() * 3 + 1);
		if (numeroAleatorio == 1) {
			return true;
		} else {
			return false;
		}
	}

}
