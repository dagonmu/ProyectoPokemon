package modelo;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Movimiento {

	private String nombre;
	private String tipoMovimiento;
	private int potencia;
	private Tipo tipoAtaque;
	private String estado;
	private int probabilidad;
	private int numTurnos;
	private String mejora;
	private String categoria;

	/**
	 * Constructor con todos los parametros
	 * 
	 * @param nombre
	 * @param tipoMovimiento
	 * @param potencia
	 * @param tipoAtaque
	 * @param estado
	 * @param probabilidad
	 * @param numTurnos
	 * @param mejora
	 * @param categoria
	 */

	public Movimiento(String nombre, String tipoMovimiento, int potencia, Tipo tipoAtaque, String estado,
			int probabilidad, int numTurnos, String mejora, String categoria) {
		super();
		this.nombre = nombre;
		this.tipoMovimiento = tipoMovimiento;
		this.potencia = potencia;
		this.tipoAtaque = tipoAtaque;
		this.estado = estado;
		this.probabilidad = probabilidad;
		this.numTurnos = numTurnos;
		this.mejora = mejora;
		this.categoria = categoria;
	}

	/**
	 * Constructor por defecto
	 */

	public Movimiento() {
		super();
		this.nombre = "";
		this.tipoMovimiento = "";
		this.potencia = 0;
		this.tipoAtaque = null;
		this.estado = "";
		this.probabilidad = 0;
		this.numTurnos = 0;
		this.mejora = "";
		this.categoria = "";
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public Tipo getTipoAtaque() {
		return tipoAtaque;
	}

	public void setTipoAtaque(Tipo tipoAtaque) {
		this.tipoAtaque = tipoAtaque;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumTurnos() {
		return numTurnos;
	}

	public void setNumTurnos(int numTurnos) {
		this.numTurnos = numTurnos;
	}

	public String getMejora() {
		return mejora;
	}

	public void setMejora(String mejora) {
		this.mejora = mejora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	/**
	 * Calcula el tipo de Movimiento. Si es de ataque calcula el daño y lo aplica al
	 * Pokemon rival. Si es de estado le aplica el estado al Pokemon rival. Si es de
	 * mejora calcula que tipo de mejora es y la aplicaal Pokemon que realiza el
	 * Movimiento. Sino hay estamina suficiente no te deja hacer el Movimiento.
	 * 
	 * @param Movimiento a
	 * @param Pokemon    p
	 * @param Pokemon    p2
	 */

	public void calcularMovimiento(Movimiento a, Pokemon p, Pokemon p2) {
		if (p.getEstaminaActual() >= calcularConsumo(a, p)) {
			switch (a.tipoMovimiento) {
			case "ataque": {
				p.setEstaminaActual(p.getEstaminaActual() - calcularConsumo(a, p));
				int danyo = calcularDanyo(a, p, p2);
				p2.setVitalidadActual(p2.getVitalidadActual() - danyo);
				break;
			}
			case "estado": {
				p.setEstaminaActual(p.getEstaminaActual() - calcularConsumo(a, p));
				if (p2.getEstado() == null) {
					p2.setEstado(Estado.realizarAlteracion(a, a.getProbabilidad()));
				}
				break;
			}
			case "mejora": {
				p.setEstaminaActual(p.getEstaminaActual() - calcularConsumo(a, p));
				int ataque = p.getAtaque();// select del ataque del pokemon en la base de datos
				int defensa = p.getDefensa();// select de la defensa del pokemon en la base de datos
				if (a.mejora == "ataque") {
					if (p.getAtaque() < ataque * 3) {
						p.setAtaque((int) (p.getAtaque() * 1.5));
					} else {
						System.out.println("Ataque al maximo");
					}
				} else {
					if (p.getDefensa() < defensa * 3) {
						p.setDefensa((int) (p.getDefensa() * 1.5));
					} else {
						System.out.println("Defensa al maximo");
					}
				}

				break;
			}
			default:
				break;
			}
		} else {
			System.out.println("No hay stamina suficiente");
		}

	}

	/**
	 * Calcula el consumo de estamina de cada tipo de movimiento.
	 * 
	 * @param Movimiento a
	 * @param Pokemon    pp
	 * @return int
	 */

	public int calcularConsumo(Movimiento a, Pokemon p) {
		int stamina = 0;
		switch (a.tipoMovimiento) {
		case "ataque": {
			stamina = potencia / 2;
			break;
		}
		case "estado": {
			stamina = (p.getNivel() % 10) * 10;
			break;
		}
		case "mejora": {
			stamina = (p.getNivel() % 10) * 10;
		}
		default:
			break;
		}
		return stamina;
	}

	/**
	 * Calcula el daño de los Movimientos de ataque teniendo en cuenta la defensa y
	 * el ataque del Pokemon, ademasde los tipos y si es un ataque fisico o especial
	 * 
	 * @param Movimiento a
	 * @param Pokemon    p
	 * @param Pokemon    p2
	 * @return
	 */

	public int calcularDanyo(Movimiento a, Pokemon p, Pokemon p2) {
		double stab;
		if ((a.tipoAtaque == p.getTipoPrimario()) || (a.tipoAtaque == p.getTipoSecundario())) {
			stab = 1.5;
		} else {
			stab = 1;
		}
		int rnd = (int) Math.random() * (100 - 85) + 85;
		int calcularCh = (int) (Math.random() * 16 + 1);
		int ch;
		if (calcularCh == 1) {
			ch = 2;
		} else {
			ch = 1;
		}
		double danyo;
		if (a.categoria == "fisico") {
			danyo = (0.01 * stab
					* TablaTipos.calcularEfectividad(a.tipoAtaque, p2.getTipoPrimario(), p2.getTipoSecundario()) * rnd
					* ((((0.2 * p.getNivel() + 1) * p.getAtaque() * a.potencia) / (25 * p2.getDefensa())) + 2)) * ch;
		} else {
			danyo = (0.01 * stab
					* TablaTipos.calcularEfectividad(a.tipoAtaque, p2.getTipoPrimario(), p2.getTipoSecundario()) * rnd
					* ((((0.2 * p.getNivel() + 1) * p.getAtaqueEspecial() * a.potencia)
							/ (25 * p2.getDefensaEspecial())) + 2))
					* ch;
		}
		return (int) danyo;

	}

}
