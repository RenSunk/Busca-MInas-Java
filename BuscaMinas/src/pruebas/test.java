package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import interfaz.Principal;
import interfaz.Principal2;
import mundo.Jugador;
import mundo.Tablero;

class test {

	private Tablero miTablero;
	
	private Principal miPrincipal;
	
	public void esenario1() {
		miTablero = new Tablero(new Jugador("alguien"));
		miTablero.leer_datos();
	}
	
	public void esenario2() {
		esenario1();
		miPrincipal = new Principal();
	}
	
	@Test
	public void leerPuntajes(){
		esenario1();
		assertNotNull(miTablero.getpuntuacion());
	}
	
	@Test
	public void Minas() {
		esenario1();
		miTablero.jugar();
		assertNotNull(miTablero.getMimina());
	}
	@Test
	public void HayMina() {
		esenario2();
		assertEquals(false, miPrincipal.haymina(1, 1));
	}
	
}
