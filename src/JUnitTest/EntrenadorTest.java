package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Pokemon;
import modelo.Tipo;
import modelo.Entrenador;
import modelo.Movimiento;
import java.util.LinkedList;

class EntrenadorTest {

	@Test
	void testEntrenamientoPesado() {
		Pokemon p= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
				40, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		e.entrenamientoPesado(p);
		assertTrue(e.getPokeDollar()==980);
		assertTrue(p.getDefensa()==45);
		assertTrue(p.getDefensaEspecial()==45);
		assertTrue(p.getVitalidad()==205);
		p.setNivel(2);
		e.entrenamientoPesado(p);
		assertTrue(e.getPokeDollar()==940);
	}

	@Test
	void testEntrenamientoFurioso() {
		Pokemon p= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
				40, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		e.entrenamientoFurioso(p);
		assertTrue(e.getPokeDollar()==970);
		assertTrue(p.getAtaque()==45);
		assertTrue(p.getAtaqueEspecial()==45);
		assertTrue(p.getVelocidad()==45);
		p.setNivel(2);
		e.entrenamientoFurioso(p);
		assertTrue(e.getPokeDollar()==910);
	}

	@Test
	void testEntrenamientoFuncional() {
		Pokemon p= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
				40, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		e.entrenamientoFuncional(p);
		assertTrue(e.getPokeDollar()==960);
		assertTrue(p.getAtaque()==45);
		assertTrue(p.getDefensa()==45);
		assertTrue(p.getVelocidad()==45);
		assertTrue(p.getVitalidad()==205);
		p.setNivel(2);
		e.entrenamientoFuncional(p);
		assertTrue(e.getPokeDollar()==880);
	}

	@Test
	void testEntrenamientoOnirico() {
		Pokemon p= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
				40, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		e.entrenamientoOnirico(p);
		assertTrue(e.getPokeDollar()==960);
		assertTrue(p.getAtaqueEspecial()==45);
		assertTrue(p.getDefensaEspecial()==45);
		assertTrue(p.getVelocidad()==45);
		assertTrue(p.getVitalidad()==205);
		p.setNivel(2);
		e.entrenamientoOnirico(p);
		assertTrue(e.getPokeDollar()==880);
	}

	@Test
	void testCriar() {
		Movimiento placaje = new Movimiento("placaje", "ataque", 40, Tipo.NORMAL, null, 0,
				0,"especial", null);
		Movimiento hipnosis = new Movimiento("hipnosis", "estado", 0, Tipo.NORMAL, "confundido", 100,
				0, "especial", null);
		Movimiento defensaFerrea = new Movimiento("defensa ferrea", "mejora", 0, Tipo.NORMAL, null, 0,
				0,"especial", "defensa");
		Movimiento veneno = new Movimiento("veneno", "estado", 0, Tipo.NORMAL, "envenenado", 100,
				0,"especial", null);
		LinkedList<Movimiento> hijo = new LinkedList<Movimiento>();
		hijo.add(placaje);
		hijo.add(hipnosis);
		hijo.add(defensaFerrea);
		hijo.add(veneno);
		Pokemon padre= new Pokemon(2, 1, "charmander",null, 1, 50, 250, 250,
				50, 50, 50, 50, 50, 350,
				350, 5, 'H', hijo,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Pokemon madre= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
				40, 40, 40, 40, 40, 300,
				300, 5, 'M', hijo,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		Pokemon criado=e.criar(padre, madre);
		assertTrue(criado.getAtaque()==50);
		assertTrue(criado.getDefensa()==50);
		assertTrue(padre.getFertilidad()==4);
		assertTrue(madre.getFertilidad()==4);
		assertTrue(criado.getMovimientos().get(0).getNombre()==padre.getMovimientos().get(0).getNombre());
		assertTrue(criado.getMovimientos().get(1).getNombre()==madre.getMovimientos().get(1).getNombre());
		assertTrue(e.getPokeDollar()==900);
		
	}

	@Test
	void testLanzarPokeball() {
		Entrenador e = new Entrenador(null, null, "Pablo", 1000, null);
		e.lanzarPokeball();
	}

}
