package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import crud.PokemonCRUD;
import modelo.Tipo;
import modelo.Movimiento;
import modelo.Objeto;
import modelo.Pokemon;

class PokemonTest {
	Pokemon p= new Pokemon(2, 1, "charmander",null, 1, 50, 200, 200,
			100, 40, 40, 40, 40, 300,
			300, 5, 'H', null,
			Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);

	@Test
	void testSubirNivel() {
		int nivelPrevio = p.getNivel();
		boolean result = p.subirNivel();
		int ataquePrevio = p.getAtaque();
		assertTrue(nivelPrevio + 1 == p.getNivel());
		assertTrue(result);
		p.subirNivel();
		assertTrue(ataquePrevio +  (int) Math.floor(Math.random() * (1 - 4) + 4) <=p.getAtaque());
	}

	@Test
	void testRandomPokemon() {
		LinkedList<Pokemon> pokedex = PokemonCRUD.selectPokedex();
		Pokemon p=Pokemon.randomPokemon(pokedex);
		assertTrue(p.getNivel()==1);
		assertTrue(p.getFertilidad()==5);
		assertTrue(p.getSexo()=='H'||p.getSexo()=='M');
	}

	@Test
	void testEvolucionar() {
		Pokemon p2= new Pokemon(2, 1, "Bulbasur",null, 1, 50, 200, 200,
				100, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		boolean result=p2.evolucionar();
		assertFalse(result);
		p2.setNivel(16);
		boolean result2=p2.evolucionar();
		assertTrue(result2);
	}

	@Test
	void testAprenderMovimiento() {
		Pokemon p3= new Pokemon(2, 1, "Bulbasur",null, 1, 50, 200, 200,
				100, 40, 40, 40, 40, 300,
				300, 5, 'H', null,
				Tipo.FUEGO, Tipo.NORMAL,16, null, null,null, null);
		boolean result=p3.aprenderMovimiento();
		assertFalse(result);
		p3.setNivel(3);
		boolean result2=p3.aprenderMovimiento();
		assertTrue(result2);
	}

	@Test
	void testDescansar() {
		p.descansar();
		assertEquals(300, p.getEstaminaActual());
		p.setEstaminaActual(0);
		p.descansar();
		assertEquals(150, p.getEstaminaActual());
	}

}
