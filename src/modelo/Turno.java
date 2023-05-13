package modelo;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Turno {

	private int numTurno;

	public int getNumTurno() {
		return numTurno;
	}

	public void setNumTurno(int numTurno) {
		this.numTurno = numTurno;
	}

	public Turno() {
		super();
		this.numTurno = 0;
	}

	public Turno(int numTurno) {
		super();
		this.numTurno = numTurno;
	}

	/**
	 * Aumenta el contador de un objeto Turno en 1.
	 * 
	 */
	public void contadorTurno() {
		this.numTurno++;
	}

}
