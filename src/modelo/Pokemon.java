package modelo;

import java.util.LinkedList;
import java.util.List;

import crud.EvolucionCRUD;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Pokemon {

	public final int EXPERIENCIA_NIVEL = 10;

	private int idPokemon;
	private int numPokedex;
	private String nombre;
	private String mote;
	private int nivel;
	private int experienciaObtenida;
	private int vitalidad;
	private int vitalidadActual;
	private int ataque;
	private int ataqueEspecial;
	private int defensa;
	private int defensaEspecial;
	private int velocidad;
	private int estamina;
	private int estaminaActual;
	private int fertilidad;
	private char sexo; // Podría ser boolean
	LinkedList<Movimiento> movimientos = new LinkedList<Movimiento>();
	private Tipo tipoPrimario;
	private Tipo tipoSecundario;
	private int nivelEvolucion;
	private String estado;
	private Objeto objeto;
	private String imgFrontal;
	private String imgTrasera;

	/**
	 * Constructor por defecto.
	 * 
	 */
	public Pokemon() {
		super();
		this.idPokemon = 0;
		this.numPokedex = 0;
		this.nombre = "";
		this.mote = "";
		this.nivel = 1;
		this.experienciaObtenida = 0;
		this.vitalidad = 1;
		this.vitalidadActual = 1;
		this.ataque = 1;
		this.ataqueEspecial = 1;
		this.defensa = 1;
		this.defensaEspecial = 1;
		this.velocidad = 1;
		this.estamina = 1;
		this.estaminaActual = 1;
		this.fertilidad = 5;
		this.sexo = ' ';
		this.movimientos = null;
		this.tipoPrimario = null;
		this.tipoSecundario = null;
		this.nivelEvolucion = 0;
		this.estado = "";
		this.objeto = null;
		this.imgFrontal = "";
		this.imgTrasera = "";
	}

	/**
	 * Constructor Pokedex
	 * 
	 * @param numPokedex
	 * @param nombre
	 * @param movimientos
	 * @param tipo1
	 * @param tipo2
	 * @param nivelEvolucion
	 * @param imgFrontal
	 * @param imgTrasera
	 */
	public Pokemon(int numPokedex, String nombre, LinkedList<Movimiento> movimientos, Tipo tipo1, Tipo tipo2,
			int nivelEvolucion, String imgFrontal, String imgTrasera) {
		this.numPokedex = numPokedex;
		this.nombre = nombre;
		this.movimientos = movimientos;
		this.tipoPrimario = tipo1;
		this.tipoSecundario = tipo2;
		this.nivelEvolucion = nivelEvolucion;
		this.imgFrontal = imgFrontal;
		this.imgTrasera = imgTrasera;
	}

	/**
	 * Constructor copia
	 * 
	 * @param p
	 */
	public Pokemon(Pokemon p) {
		this.idPokemon = p.idPokemon;
		this.numPokedex = p.numPokedex;
		this.nombre = p.nombre;
		this.mote = p.mote;
		this.nivel = p.nivel;
		this.experienciaObtenida = p.experienciaObtenida;
		this.vitalidad = p.vitalidad;
		this.vitalidadActual = p.vitalidadActual;
		this.ataque = p.ataque;
		this.ataqueEspecial = p.ataqueEspecial;
		this.defensa = p.defensa;
		this.defensaEspecial = p.defensaEspecial;
		this.velocidad = p.velocidad;
		this.estamina = p.estamina;
		this.estaminaActual = p.estaminaActual;
		this.fertilidad = p.fertilidad;
		this.sexo = p.sexo;
		this.movimientos = p.movimientos;
		this.tipoPrimario = p.tipoPrimario;
		this.tipoSecundario = p.tipoSecundario;
		this.nivelEvolucion = p.nivelEvolucion;
		this.estado = p.estado;
		this.objeto = p.objeto;
		this.imgFrontal = p.imgFrontal;
		this.imgTrasera = p.imgTrasera;
	}

	/**
	 * Constructor completo.
	 * 
	 * @param idPokemon
	 * @param numPokedex
	 * @param nombre
	 * @param mote
	 * @param nivel
	 * @param experienciaObtenida
	 * @param vitalidad
	 * @param vitalidadActual
	 * @param ataque
	 * @param ataqueEspecial
	 * @param defensa
	 * @param defensaEspecial
	 * @param velocidad
	 * @param estamina
	 * @param estaminaActual
	 * @param recuperacionEstamina
	 * @param fertilidad
	 * @param sexo
	 * @param movimientos
	 * @param tipoPrimario
	 * @param tipoSecundario
	 * @param nivelEvolucion
	 * @param estado
	 * @param objeto
	 * @param imgFrontal
	 * @param imgTrasera
	 */
	public Pokemon(int idPokemon, int numPokedex, String nombre, String mote, int nivel, int experienciaObtenida,
			int vitalidad, int vitalidadActual, int ataque, int ataqueEspecial, int defensa, int defensaEspecial,
			int velocidad, int estamina, int estaminaActual, int fertilidad, char sexo,
			LinkedList<Movimiento> movimientos, Tipo tipoPrimario, Tipo tipoSecundario, int nivelEvolucion,
			String estado, Objeto objeto, String imgFrontal, String imgTrasera) {
		super();
		this.idPokemon = idPokemon;
		this.numPokedex = numPokedex;
		this.nombre = nombre;
		this.mote = mote;
		this.nivel = nivel;
		this.experienciaObtenida = experienciaObtenida;
		this.vitalidad = vitalidad;
		this.vitalidadActual = vitalidadActual;
		this.ataque = ataque;
		this.ataqueEspecial = ataqueEspecial;
		this.defensa = defensa;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.estamina = estamina;
		this.estaminaActual = estaminaActual;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
		this.movimientos = movimientos;
		this.tipoPrimario = tipoPrimario;
		this.tipoSecundario = tipoSecundario;
		this.nivelEvolucion = nivelEvolucion;
		this.estado = estado;
		this.objeto = objeto;
		this.imgFrontal = imgFrontal;
		this.imgTrasera = imgTrasera;
	}

	public int getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMote() {
		return mote;
	}

	public void setMote(String mote) {
		this.mote = mote;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVitalidad() {
		return vitalidad;
	}

	public void setVitalidad(int vitalidad) {
		this.vitalidad = vitalidad;
	}

	public int getVitalidadActual() {
		return vitalidadActual;
	}

	public void setVitalidadActual(int vitalidadActual) {
		this.vitalidadActual = vitalidadActual;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getEstamina() {
		return estamina;
	}

	public void setEstamina(int estamina) {
		this.estamina = estamina;
	}

	public int getFertilidad() {
		return fertilidad;
	}

	public void setFertilidad(int fertilidad) {
		this.fertilidad = fertilidad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getEstaminaActual() {
		return estaminaActual;
	}

	public void setEstaminaActual(int estaminaActual) {
		this.estaminaActual = estaminaActual;
	}

	public LinkedList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Tipo getTipoPrimario() {
		return tipoPrimario;
	}

	public void setTipoPrimario(Tipo tipoPrimario) {
		this.tipoPrimario = tipoPrimario;
	}

	public Tipo getTipoSecundario() {
		return tipoSecundario;
	}

	public void setTipoSecundario(Tipo tipoSecundario) {
		this.tipoSecundario = tipoSecundario;
	}

	public int getNumPokedex() {
		return numPokedex;
	}

	public void setNumPokedex(int numPokedex) {
		this.numPokedex = numPokedex;
	}

	public String getImgFrontal() {
		return imgFrontal;
	}

	public void setImgFrontal(String imgFrontal) {
		this.imgFrontal = imgFrontal;
	}

	public String getImgTrasera() {
		return imgTrasera;
	}

	public void setImgTrasera(String imgTrasera) {
		this.imgTrasera = imgTrasera;
	}

	public int getExperienciaObtenida() {
		return experienciaObtenida;
	}

	public void setExperienciaObtenida(int experienciaObtenida) {
		this.experienciaObtenida = experienciaObtenida;
	}

	public int getNivelEvolucion() {
		return nivelEvolucion;
	}

	public void setNivelEvolucion(int nivelEvolucion) {
		this.nivelEvolucion = nivelEvolucion;
	}

	/**
	 * Comprueba si un Pokemon tiene la experiencia necesaria para subir de nivel.
	 * Si puede, aumenta su nivel, resta la experiencia usada para ello y aumenta
	 * sus estadisticas de forma aleatoria. Devuelve true si sube de nivel y false
	 * en el caso contrario.
	 * 
	 * @return boolean
	 */
	public boolean subirNivel() {

		while (this.experienciaObtenida >= (this.EXPERIENCIA_NIVEL * nivel)) {
			this.experienciaObtenida -= (this.EXPERIENCIA_NIVEL * nivel);
			this.nivel++;
			this.ataque += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.ataqueEspecial += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.defensa += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.defensaEspecial += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.velocidad += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.vitalidad += this.velocidad += (int) Math.floor(Math.random() * (1 - 4) + 4);
			this.estamina += (int) Math.floor(Math.random() * (5 - 10) + 10);

			return true;

		}
		return false;

	}

	/**
	 * Le da a un Pokemon las estadisticas que no forman parte de la Pokedex de
	 * forma aleatoria. Nivel. Vitalidad. Vitalidad actual. Ataque. Ataque especial.
	 * Defensa. Defensa especial. Velocidad. Estamina. Estamina actual. Fertilidad.
	 * Sexo.
	 * 
	 */
	public static Pokemon randomPokemon(LinkedList<Pokemon> pokedex) {
		Pokemon pkmnRandom = pokedex.get((int) Math.floor(Math.random() * (1 - 151) + 151));

		pkmnRandom.setNivel(1);
		pkmnRandom.setVitalidad((int) Math.floor(Math.random() * (15 - 25) + 25));
		pkmnRandom.setAtaque((int) Math.floor(Math.random() * (1 - 10) + 10));
		pkmnRandom.setAtaqueEspecial((int) Math.floor(Math.random() * (1 - 10) + 10));
		pkmnRandom.setDefensa((int) Math.floor(Math.random() * (1 - 10) + 10));
		pkmnRandom.setDefensaEspecial((int) Math.floor(Math.random() * (1 - 10) + 10));
		pkmnRandom.setVelocidad((int) Math.floor(Math.random() * (1 - 10) + 10));
		pkmnRandom.setEstamina((int) Math.floor(Math.random() * (60 - 100) + 100));
		pkmnRandom.setVitalidadActual(pkmnRandom.getVitalidad());
		pkmnRandom.setEstaminaActual(pkmnRandom.getEstamina());
		pkmnRandom.setFertilidad(5);

		if ((int) Math.floor(Math.random() * (1 - 3) + 3) == 2) {
			pkmnRandom.sexo = 'M';
		} else {
			pkmnRandom.sexo = 'H';
		}
		return pkmnRandom;
	}

	/**
	 * Comprueba si el nivel de un Pokemon es igual al nivel necesario para
	 * evolucionar. Si lo esta, devuelve true, si no, devuelve false.
	 * 
	 * @return boolean
	 */
	public boolean evolucionar() {
		if (this.nivel == this.nivelEvolucion) {
			EvolucionCRUD.evolucionPokemon(this);
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si un un Pokemon esta a un nivel en el que pueda aprender un
	 * movimiento. Si lo esta, devuelve true, si no, devuelve false.
	 * 
	 * @return
	 */
	public boolean aprenderMovimiento() {
		if (this.nivel % 3 == 0) {

			return true;
		}
		return false;
	}

	/**
	 * Aumenta un total de la mitad de su estamina, la estamina actual de un
	 * Pokemon. Si la estamina actual fuese a superar su valor de estamina, en ese
	 * caso se igualan.
	 * 
	 */
	public void descansar() {
		if ((estaminaActual + estamina / 2) > estamina) {
			this.estaminaActual = estamina;
		} else {
			this.estaminaActual += estamina / 2;
		}
	}

}
