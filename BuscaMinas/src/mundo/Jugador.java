package mundo;

public class Jugador {
	
	private String nombre;
	private int puntaje;
	private int tiempo;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.tiempo = 0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
}
