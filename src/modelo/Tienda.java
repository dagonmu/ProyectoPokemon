package modelo;

import java.util.LinkedList;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Tienda {

	LinkedList<Objeto> tienda = new LinkedList<Objeto>();

	/**
	 * Constructor completo
	 * 
	 * @param LinkedList<Objeto> tienda
	 */
	public Tienda(LinkedList<Objeto> tienda) {
		super();
		this.tienda = tienda;
	}

	public LinkedList<Objeto> getTienda() {
		return tienda;
	}

	public void setTienda(LinkedList<Objeto> tienda) {
		this.tienda = tienda;
	}

	/**
	 * Permite a un Entrenador comprar un Objeto. Le resta al Entrenador el valor
	 * del precio de compra del Objeto a sus pokedollars y añade dicho Objeto a su
	 * mochila.
	 *
	 * @param e
	 * @param o
	 */
	public static void comprar(Entrenador e, Objeto o) {

		e.setPokeDollar(e.getPokeDollar() - o.getPrecioCompra());
		e.getMochila().add(o);

	}

	/**
	 * Permite a un Entrenador vender un Objeto. Le suma al Entrenador el valor del
	 * precio de venta del Objeto a sus pokedollars y quita dicho Objeto a su
	 * mochila.
	 * 
	 * @param e
	 * @param o
	 */
	public static void vender(Entrenador e, Objeto o) {

		int index = e.getMochila().indexOf(o);

		e.setPokeDollar(e.getPokeDollar() + e.getMochila().get(index).getPrecioVenta());
		e.getMochila().remove(o);

	}

}
