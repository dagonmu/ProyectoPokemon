package modelo;

import java.util.LinkedList;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Objeto {

	private String nombre;
	private int aumento;
	private int disminucion;
	private int precioCompra;
	private int precioVenta;
	private String descripcion;

	/**
	 * Constructor por defecto
	 * 
	 */
	public Objeto() {
		super();
		this.nombre = "";
		this.aumento = 0;
		this.disminucion = 0;
		this.precioCompra = 0;
		this.precioVenta = 0;
		this.descripcion = "";
	}

	/**
	 * Constructor copia
	 * 
	 * @param objeto
	 */
	public Objeto(Objeto objeto) {
		this.nombre = objeto.nombre;
		this.aumento = objeto.aumento;
		this.disminucion = objeto.disminucion;
		this.precioCompra = objeto.precioCompra;
		this.precioVenta = objeto.precioVenta;
		this.descripcion = objeto.descripcion;
	}

	/**
	 * Constructor completo.
	 * 
	 * @param nombre
	 * @param aumento
	 * @param disminucion
	 * @param precioCompra
	 * @param precioVenta
	 * @param aumentoGenerado
	 * @param disminucionGenerada
	 * @param descripcion
	 */
	public Objeto(String nombre, int aumento, int disminucion, int precioCompra, int precioVenta, String descripcion) {
		super();
		this.nombre = nombre;
		this.aumento = aumento;
		this.disminucion = disminucion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAumento() {
		return aumento;
	}

	public void setAumento(int aumento) {
		this.aumento = aumento;
	}

	public int getDisminucion() {
		return disminucion;
	}

	public void setDisminucion(int disminucion) {
		this.disminucion = disminucion;
	}

	public int getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}

	public int getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Aumenta las estadisticas de un Pokemon en base al Atributo Objeto de este.
	 * 
	 * @param p
	 */
	public void equipar(Pokemon p) {

		switch (this.nombre) {

		case "peso":
			p.setObjeto(this);

			p.setAtaque(p.getAtaque() + (p.getAtaque() * this.aumento / 100));
			p.setDefensa(p.getDefensa() + (p.getDefensa() * this.aumento / 100));
			p.setVelocidad(p.getVelocidad() - (p.getVelocidad() * this.disminucion / 100));

			break;
		case "pluma":
			p.setObjeto(this);

			p.setVelocidad(p.getVelocidad() + (p.getVelocidad() * this.aumento / 100));
			p.setDefensa(p.getDefensa() - (p.getDefensa() * this.aumento / 100));
			p.setDefensaEspecial(p.getDefensaEspecial() - (p.getDefensaEspecial() * this.aumento / 100));

			break;
		case "chaleco":
			p.setObjeto(this);

			p.setDefensa(p.getDefensa() + (p.getDefensa() * this.aumento / 100));
			p.setDefensaEspecial(p.getDefensaEspecial() + (p.getDefensaEspecial() * this.aumento / 100));
			p.setVelocidad(p.getVelocidad() - (p.getVelocidad() * this.disminucion / 100));
			p.setAtaque(p.getAtaque() - (p.getAtaque() * this.disminucion / 100));

			break;
		case "baston":
			p.setObjeto(this);

			p.setEstamina(p.getEstamina() + (p.getEstamina() * this.aumento / 100));
			p.setVelocidad(p.getVelocidad() - (p.getVelocidad() * this.disminucion / 100));

			break;
		case "pilas":
			p.setObjeto(this);

			p.setVelocidad(p.getVelocidad() + (p.getVelocidad() * this.aumento / 100));
			p.setDefensaEspecial(p.getDefensaEspecial() - (p.getDefensaEspecial() * this.disminucion / 100));

			break;
		default:
			break;
		}

	}

	/**
	 * Disminuye las estadisticas de un Pokemon en base al Atributo Objeto de este
	 * teniendo en cuenta el valor aumentado/disminudo anteriormente por el Objeto.
	 * 
	 * @param p
	 */
	public void quitar(Pokemon p) {

		switch (this.nombre) {

		case "peso":

			p.setAtaque(p.getAtaque() * 100 / (this.aumento + 100));
			p.setDefensa(p.getDefensa() * 100 / (this.aumento + 100));
			p.setVelocidad(p.getVelocidad() * 100 / (100 - this.disminucion));

			p.setObjeto(null);
			break;
		case "pluma":

			p.setVelocidad(p.getVelocidad() * 100 / (this.aumento + 100));
			p.setDefensa(p.getDefensa() * 100 / (100 - this.disminucion));
			p.setDefensaEspecial(p.getDefensaEspecial() * 100 / (100 - this.disminucion));

			p.setObjeto(null);
			break;
		case "chaleco":

			p.setDefensa(p.getDefensa() * 100 / (this.aumento + 100));
			p.setDefensaEspecial(p.getDefensaEspecial() * 100 / (this.aumento + 100));
			p.setVelocidad(p.getVelocidad() * 100 / (100 - this.disminucion));
			p.setAtaque(p.getAtaque() * 100 / (100 - this.disminucion));

			p.setObjeto(null);
			break;
		case "baston":

			p.setEstamina(p.getEstamina() * 100 / (this.aumento + 100));
			p.setVelocidad(p.getVelocidad() * 100 / (100 - this.disminucion));

			p.setObjeto(null);
			break;
		case "pilas":

			p.setVelocidad(p.getVelocidad() * 100 / (this.aumento + 100));
			p.setDefensaEspecial(p.getDefensaEspecial() * 100 / (100 - this.disminucion));

			p.setObjeto(null);
			break;
		default:
			break;
		}

	}

}
