package mundo;

public class Mina {
	
	private boolean estado;
	private int posicionX;
	private int posicionY;
	public Mina(int posicionX, int posicionY) {
		this.estado = false;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
}
